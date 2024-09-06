#!/usr/bin/env python3

import os, sys, getopt, logging, configparser
from logging.handlers import RotatingFileHandler
import requests
from bs4 import BeautifulSoup
from PIL import Image
from io import BytesIO
from decimal import Decimal, ROUND_HALF_UP
from AddLinkItem import LinkItem
import mysql.connector

class LinkEngine:
    ogi = LinkItem()
    MAX_XY = 200
    def addURL(self):

        print('hello')
        print(f'config: {self.config}')
        print(f'loadData: {self.loadData}')
        title = 'PaseDB Service URL'
        self.ogi.set_url(self.url)
        try:
            try:
                response = requests.get(self.url)
                document = BeautifulSoup(response.text, 'html.parser')
                meta_og_title = document.select_one("meta[property='og:title']")
                if meta_og_title:
                    title = meta_og_title['content']
                if not title or len(title) < 3:
                    title = document.title or self.url

                meta_og_desc = document.select_one("meta[property='og:description']")
                desc = meta_og_desc['content'] if meta_og_desc else "No description available"

                meta_og_image = document.select_one("meta[property='og:image']")
                print(meta_og_image)
                if meta_og_image:
                    image_url = meta_og_image['content']
                    self.set_image_dimensions(image_url)

                self.ogi.set_title(title)
                self.ogi.set_description(desc)
                print(self.ogi.toString())

            except requests.exceptions.SSLError as e:
                self.logger.error("[#error] ---------- JSOUP FAILED TO CONNECT / TRYING OPENGRAPH TOOLSET ----------------")
                self.try_open_graph_toolset(self.url)


            #we made it this far, which means we should have all the data ready to put into the DB
            if self.loadData:
                self.persistURL(self.ogi)
        except Exception as e:
            self.logger.error("An error occurred in the parsing process ...")
            self.logger.error(e)

    def try_open_graph_toolset(self, pasedburl):
        response = requests.get(pasedburl)
        soup = BeautifulSoup(response.text, 'html.parser')
        self.ogi.set_title(soup.title.text)
        self.ogi.set_description(soup.meta['description'])
        image_url = soup.meta['image']
        if image_url:
            self.set_image_dimensions(image_url)

    def set_image_dimensions(self, image_url):
        response = requests.get(image_url, headers={'User-Agent': 'Mozilla/5.0'})
        image = Image.open(BytesIO(response.content))
        height = image.height
        width = image.width
        pct = self.set_pct(height, width)
        display_height = round(height * pct)
        display_width = round(width * pct)
        self.ogi.set_imgurl(image_url)
        self.ogi.set_display_height(display_height)
        self.ogi.set_display_width(display_width)

    def set_pct(self, height, width):
        max_dim = max(height, width)
        if max_dim <= self.MAX_XY:
            return 1
        val = self.MAX_XY / max_dim
        bd = Decimal(val).quantize(Decimal('.01'), rounding=ROUND_HALF_UP)
        return float(bd)

    def persistURL(self, link_item):
        print('**********************************************************')
        print('Configuration File: ', self.config)
        dbconfig = configparser.ConfigParser()
        dbconfig.read(self.config)
        print(dbconfig['tethys']['user'])
        print(dbconfig['tethys']['passwd'])
        print(dbconfig['tethys']['host'])
        print(dbconfig['tethys']['db'])
        print('**********************************************************')
        cnx = mysql.connector.connect(user=dbconfig['tethys']['user'],
                                      password=dbconfig['tethys']['passwd'],
                                      host=dbconfig['tethys']['host'],
                                      database=dbconfig['tethys']['db'])
        cursor = cnx.cursor()
        try:
            # Insert link item data
            insert_link = """
                INSERT INTO palink (title, url, description, imageurl, display_height, display_width, userid)
                VALUES (%s, %s, %s, %s, %s, %s, %s)
            """
            cursor.execute(insert_link, (link_item.title, link_item.url, link_item.description,
                                         link_item.img_url, link_item.display_height,
                                         link_item.display_width, link_item.user_id))
            cnx.commit()
            '''
            palink_id = cursor.lastrowid

            # Insert tags data, if any
            if link_item.tags:
                insert_tag = "INSERT INTO tag (tag, palinkid) VALUES (%s, %s)"
                for tag in link_item.tags:
                    cursor.execute(insert_tag, (tag, palink_id))
                    cnx.commit()
            '''
        except mysql.connector.Error as err:
            self.logger.error("Failed to insert record")
            self.logger.error(err)
        finally:
            cursor.close()
            cnx.close()

    def __init__(self):
        self.config = 'db.ini'
        self.loadData = True
        self.url = None
        self.logger = logging.getLogger(self.__class__.__name__)
        self.logger.setLevel(logging.DEBUG)

        log_file = 'addNewLink.log'
        max_file_size = 5 * 1024 * 1024  # 5 MB
        backup_count = 5
        if os.path.exists(log_file):
            os.remove(log_file)
        file_handler = RotatingFileHandler(filename=log_file, maxBytes=max_file_size, backupCount=backup_count)
        log_format = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
        file_handler.setFormatter(log_format)
        file_handler.setLevel(logging.DEBUG)

        console_handler = logging.StreamHandler()
        console_handler.setLevel(logging.DEBUG)

        self.logger.addHandler(file_handler)
        self.logger.addHandler(console_handler)

    def main(self, *argv):
        try:
            opts, args = getopt.getopt(argv, "u:hx")
        except getopt.GetoptError as e:
            print('>>>> ERROR: %s' % str(e))
            sys.exit(2)
        for opt, arg in opts:
            if opt == '-h':
                print('dataloader.py -h \nHelp Message')
                print('dataloader.py -u \nURL of the page to load')
                print('dataloader.py -x Skip data loading and only test the URL')
                sys.exit()
            elif opt in "-x":
                self.loadData = False
            elif opt in "-u":
                self.url = arg

        if self.url:
            self.addURL()
        else:
            print('You must provide a valid URL with the -u argument')



if __name__ == "__main__":
    addLink = LinkEngine()
    addLink.main(*sys.argv[1:])
FROM mongo:4.0.2

LABEL maintainer="Steve S Gee JR <ioexcept@gmail.com>"
LABEL description="PASEDB: Open Source Parental Alienation Support & Education DB schema loaders for mongodb 4.0.2"
LABEL url="http://pasedb.org"
LABEL version="2.7"

# Modify child mongo to use /data/db2 as dbpath (because /data/db wont persist the build)
RUN mkdir -p /data/db2 \
&& mkdir /files \
   && echo "dbpath = /data/db2" > /etc/mongodb.conf
ADD . /files

RUN chown -R mongodb:mongodb /data/db2 \
&& chown -R mongodb:mongodb /files

RUN mongod --fork --logpath /var/log/mongodb.log --dbpath /data/db2 --smallfiles && sleep 20 \
   && mongoimport --db pasedb --collection counter --type csv --headerline --file /files/counter.csv \
   && mongoimport --db pasedb --collection userlinks --type csv --headerline --file /files/userlinks.csv \
   && mongod --dbpath /data/db2 --shutdown \
   && chown -R mongodb /data/db2

# Make the new dir a VOLUME to persists it
VOLUME /data/db2

CMD ["mongod", "--config", "/etc/mongodb.conf", "--smallfiles"]

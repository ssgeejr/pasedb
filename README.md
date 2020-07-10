
# OFFICIAL RELEASE DATE 22 JUNE 2019

![pasedb.org](https://i.imgur.com/UMLrUKs.png)

# PASEDB
Parental Alienation Support &amp; Education Database

### Mission Statement
Provide a simple, centralized location to allow easy access to support and educational sites relating to Parental Alienation

### Site Location
The site resides at http://pasedb.org


### Comments
When America's court systems fail you, three options remain:  
1) Become a victim  
2) Quit and fade into oblivion   
3) Become a light and share what you have learned so other's do not have to carve the path on their own  
  
It should be obvious my choice. This site is dedicated to my three children whom I hope one day to see again. 


CREATE USER 'pasedb'@'%' IDENTIFIED BY 'alienation';
GRANT ALL PRIVILEGES ON pasedb.* TO 'pasedb'@'%';


counter
create table counter(
counterid integer NOT NULL AUTO_INCREMENT,
ip varchar(32),
page integer,
query varchar(128),
counter_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (counterid)
)


palink
create table palink(
palinkid integer NOT NULL AUTO_INCREMENT,
title varchar(100) not null,
url varchar(256) not null,
description varchar(200) default '',
imageUrl varchar(256) default '',
display_height	int default 0,
display_width int default 0,
userID integer default 0,
link_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (palinkid)
)

tags
create table tag(
tag integer not null,
palinkid integer not null
)










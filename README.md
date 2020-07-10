
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


https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.20/mysql-connector-java-8.0.20.jar

CREATE USER 'pasedb'@'%' IDENTIFIED BY 'alienation';
GRANT ALL PRIVILEGES ON pasedb.* TO 'pasedb'@'%';


#counter
drop table counter;
create table counter(
	counterid integer not null auto_increment,
	ip varchar(32),
	page varchar(16),
	query varchar(128),
	counter_date timestamp default current_timestamp,
	primary key (counterid)
);


#palink
drop table palink;
create table palink(
	palinkid integer not null auto_increment,
	title varchar(100) not null,
	url varchar(256) not null,
	description varchar(200) default '',
	imageurl varchar(256) default '',
	display_height	int default 0,
	display_width int default 0,
	userid integer default 0,
	link_date timestamp default current_timestamp,
	primary key (palinkid)
);

#tags
create table tag(
	tag integer not null,
	palinkid integer not null
)


Notes:

pasedbui | Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.








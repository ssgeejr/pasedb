FROM tomcat:7-jre8
# FROM tomcat:7.0.105-jdk8-openjdk-slim * Fails with some security error

LABEL maintainer="ioexcept@gmail.com"
LABEL version="1.1"

RUN rm -rf /usr/local/tomcat/webapps/*

ADD pasedb.war /usr/local/tomcat/webapps/
ADD https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.20/mysql-connector-java-8.0.20.jar /usr/local/tomcat/lib/
ADD https://repo1.maven.org/maven2/com/google/guava/guava/24.1.1-jre/guava-24.1.1-jre.jar /usr/local/tomcat/lib/
ADD https://repo1.maven.org/maven2/org/jsoup/jsoup/1.10.2/jsoup-1.10.2.jar /usr/local/tomcat/lib/
ADD https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar /usr/local/tomcat/lib/
ADD https://repo1.maven.org/maven2/net/sourceforge/htmlcleaner/htmlcleaner/2.16/htmlcleaner-2.16.jar /usr/local/tomcat/lib/

ADD server.xml /usr/local/tomcat/conf/server.xml

VOLUME /usr/local/tomcat/logs

RUN rm -rf /usr/local/tomcat/webapps/ROOT*

#COPY ROOT.war /usr/local/tomcat/webapps/

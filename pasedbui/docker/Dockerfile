FROM tomcat:7-jre8

LABEL maintainer="ioexcept@gmail.com"
LABEL version="1.1"

ADD pasedb.war /usr/local/tomcat/webapps/
ADD http://central.maven.org/maven2/org/mongodb/mongo-java-driver/3.8.2/mongo-java-driver-3.8.2.jar /usr/local/tomcat/lib/
ADD http://central.maven.org/maven2/com/google/guava/guava/19.0/guava-19.0.jar /usr/local/tomcat/lib/
ADD http://central.maven.org/maven2/org/jsoup/jsoup/1.10.2/jsoup-1.10.2.jar /usr/local/tomcat/lib/
ADD https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar /usr/local/tomcat/lib/
ADD https://repo1.maven.org/maven2/net/sourceforge/htmlcleaner/htmlcleaner/2.16/htmlcleaner-2.16.jar /usr/local/tomcat/lib/

ADD server.xml /usr/local/tomcat/conf/server.xml

VOLUME /usr/local/tomcat/logs

RUN rm -rf /usr/local/tomcat/webapps/ROOT*

#COPY ROOT.war /usr/local/tomcat/webapps/

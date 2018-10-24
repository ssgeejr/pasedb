FROM tomcat:7-jre8

LABEL maintainer="ioexcept@gmail.com"
LABEL version="1.1"

ADD pasedb.war /usr/local/tomcat/webapps/
ADD http://central.maven.org/maven2/org/mongodb/mongo-java-driver/3.8.2/mongo-java-driver-3.8.2.jar /usr/local/tomcat/lib/
ADD server.xml /usr/local/tomcat/conf/server.xml
RUN rm -rf /usr/local/tomcat/webapps/ROOT*
#COPY ROOT.war /usr/local/tomcat/webapps/

FROM tomcat:7-jre8

LABEL maintainer="ioexcept@gmail.com"
LABEL version="1.1"

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY ROOT.war /usr/local/tomcat/webapps/

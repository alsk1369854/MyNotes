# extends centos
FROM centos

# auther
MAINTAINER alsk1369854 <alsk1369854@gmail.com>

# defined variable
ENV BASEPATH /usr/local
# set terminal default work path and use variable
WORKDIR ${BASEPATH}

# copy file
COPY myText.txt /usr/local/tempText.txt
# add file
# install from https://www.oracle.com/tw/java/technologies/javase/javase8-archive-downloads.html
ADD jdk-8u202-linux-x64.tar.gz /usr/local/
# install from https://tomcat.apache.org/download-90.cgi
ADD apache-tomcat-9.0.74.tar.gz /usr/local/

# config location variable
ENV JAVA_HOME=/usr/local/jdk1.8.0_202
ENV CLASSPATE=${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar
ENV CATALINA_HOME=/usr/local/apache-tomcat-9.0.74
ENV CATALINA_BASE=/usr/local/apache-tomcat-9.0.74
ENV PATH=$PATH:${JAVA_HOME}/bin:${CATALINA_HOME}/bin:${CATALINA_HOME}/lib

# export port number
EXPOSE 8080

# default run terminal command

CMD /usr/local/apache-tomcat-9.0.74/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.74/bin/catalina.sh
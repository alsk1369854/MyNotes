# extends centos
FROM centos

# auther
MAINTAINER alsk1369854 <alsk1369854@gmail.com>

# defined variable
ENV BASEPATH /usr/local

# set terminal default work path and use variable
WORKDIR ${BASEPATH}

# init curl
RUN ynm install -y curl

# export port number
EXPOSE 80

# default run terminal command
ENTRYPOINT [ "curl", "-s", "http://ip.cn" ]
FROM ubuntu:latest

ENV VERSION 7.7.0
ENV DISTRO tomcat
ENV SERVER apache-tomcat-8.0.24
ENV LIB_DIR /camunda/lib/
ENV SERVER_CONFIG /camunda/conf/server.xml
ENV NEXUS https://app.camunda.com/nexus/service/local/artifact/maven/redirect
ENV LANG en_US.UTF-8

WORKDIR /camunda

# generate locale
RUN apt-get clean && apt-get update
RUN apt-get install locales
RUN locale-gen en_US.UTF-8

# install oracle java
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" > /etc/apt/sources.list.d/oracle-jdk.list && \
    apt-key adv --recv-keys --keyserver keyserver.ubuntu.com EEA14886 && \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get update && \
    apt-get -y install --no-install-recommends oracle-java8-installer xmlstarlet ca-certificates && \
    apt-get clean && \
    rm -rf /var/cache/* /var/lib/apt/lists/*

# add camunda distro
RUN wget -O - "${NEXUS}?r=public&g=org.camunda.bpm.${DISTRO}&a=camunda-bpm-${DISTRO}&v=${VERSION}&p=tar.gz" | \
    tar xzf - -C /camunda/ server/${SERVER} --strip 2

# add scripts
ADD bin/* /usr/local/bin/
RUN chmod +x /usr/local/bin/*

# add database drivers
RUN chmod +x /usr/local/bin/download-database-drivers.sh
RUN /usr/local/bin/download-database-drivers.sh "${NEXUS}?r=public&g=org.camunda.bpm&a=camunda-database-settings&v=${VERSION}&p=pom"

# add branding files
COPY docker/camunda/commons/assets/ webapps/camunda/app/welcome/assets
COPY docker/camunda/commons/styles/ webapps/camunda/app/welcome/styles
COPY docker/camunda/welcome/index.html webapps/camunda/app/welcome

COPY docker/camunda/commons/assets/ webapps/camunda/app/admin/assets
COPY docker/camunda/commons/styles/ webapps/camunda/app/admin/styles
COPY docker/camunda/admin/index.html webapps/camunda/app/admin

COPY docker/camunda/commons/assets/ webapps/camunda/app/cockpit/assets
COPY docker/camunda/commons/styles/ webapps/camunda/app/cockpit/styles
COPY docker/camunda/cockpit/index.html webapps/camunda/app/cockpit

COPY docker/camunda/commons/assets/ webapps/camunda/app/tasklist/assets
COPY docker/camunda/commons/styles/ webapps/camunda/app/tasklist/styles
COPY docker/camunda/tasklist/index.html webapps/camunda/app/tasklist

EXPOSE 8080

CMD ["/usr/local/bin/configure-and-run.sh"]

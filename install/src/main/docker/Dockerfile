FROM ubuntu:20.04

RUN apt-get update && apt-get dist-upgrade -y && apt-get install -y openjdk-11-jre-headless

RUN DEBIAN_FRONTEND=noninteractive apt-get install -y locales

RUN sed -i -e 's/# en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen && \
    dpkg-reconfigure --frontend=noninteractive locales && \
    update-locale LANG=en_US.UTF-8

RUN locale-gen

RUN echo "Adding soffid console ${pom.version} "
ADD target/Soffid-iamconsole.tar.gz /opt/soffid/
COPY src/main/docker/start.sh /opt/soffid/iam-console-3/start.sh
COPY src/main/docker/server.xml /opt/soffid/iam-console-3/conf/server.xml

RUN chmod a+rx /opt/soffid/iam-console-3/bin/*.sh
EXPOSE 8080

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

VOLUME  /opt/soffid/iam-console-3/logs
VOLUME  /opt/soffid/iam-console-3/docs
VOLUME  /opt/soffid/iam-console-3/conf
ENTRYPOINT /opt/soffid/iam-console-3/start.sh

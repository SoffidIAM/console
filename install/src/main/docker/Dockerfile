FROM ubuntu:20.04

RUN apt-get update && apt-get dist-upgrade -y && apt-get install -y openjdk-11-jre-headless

RUN DEBIAN_FRONTEND=noninteractive apt-get install -y locales

RUN sed -i -e 's/# en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen && \
    dpkg-reconfigure --frontend=noninteractive locales && \
    update-locale LANG=en_US.UTF-8

RUN locale-gen

RUN echo "Adding soffid console ${pom.version} "
RUN groupadd soffid --gid 10000
RUN useradd soffid --gid 10000 --uid 10000
ADD target/Soffid-iamconsole.tar.gz /opt/soffid/
COPY src/main/docker/start.sh /opt/soffid/iam-console-3/start.sh
COPY src/main/docker/server.xml /opt/soffid/iam-console-3/conf/server.xml

RUN chmod a+rx /opt/soffid/iam-console-3/bin/*.sh
RUN chown -R soffid /opt/soffid/iam-console-3/
EXPOSE 8080

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

ENTRYPOINT /opt/soffid/iam-console-3/start.sh


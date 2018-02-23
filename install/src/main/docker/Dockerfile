FROM ubuntu:16.04

RUN apt-get update && apt-get dist-upgrade -y && apt-get install -y openjdk-8-jre-headless

# RUN apt-get  install -y mysql-client

RUN echo "Adding soffid console ${pom.version} "
ADD target/Soffid-iamconsole.tar.gz /opt/soffid/
COPY src/main/docker/start.sh /opt/soffid/iam-console-2/start.sh
COPY src/main/docker/server.xml /opt/soffid/iam-console-2/conf/server.xml

EXPOSE 8080

VOLUME  /opt/soffid/iam-console-2/logs
VOLUME  /opt/soffid/iam-console-2/docs
ENTRYPOINT /opt/soffid/iam-console-2/start.sh
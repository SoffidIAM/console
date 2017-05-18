FROM ubuntu:16.04

RUN apt-get update && apt-get dist-upgrade -y && apt-get install -y openjdk-8-jre-headless

# RUN apt-get  install -y mysql-client

RUN echo "Adding soffid console ${pom.version} "
COPY target/Soffid-iamconsole.tar.gz Soffid-iamconsole.tar.gz
RUN mkdir -p /opt/soffid && cd /opt/soffid && tar -xzf /Soffid-iamconsole.tar.gz && rm /Soffid-iamconsole.tar.gz
COPY src/main/docker/start.sh /opt/soffid/iam-console/start.sh
COPY src/main/docker/server.xml /opt/soffid/iam-console/jboss/server/default/deploy/jbossweb-tomcat50.sar/server.xml
COPY src/main/docker/jboss-service.xml /opt/soffid/iam-console/jboss/server/default/deploy/seycon.sar/META-INF/jboss-service.xml

EXPOSE 8080

VOLUME  /opt/soffid/iam-console/docs
ENTRYPOINT /opt/soffid/iam-console/start.sh
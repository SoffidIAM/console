#!/bin/bash
docker run -it \
   --network=linotp \
   -e DB_URL=jdbc:mariadb://linotpdb/soffid \
   -e DB_USER=soffid \
   -e DB_PASSWORD=soffid \
   -e JAVA_OPT=-Xmx2048m \
   --publish 8085:8080 \
   --rm \
   soffid/iam-console:latest 

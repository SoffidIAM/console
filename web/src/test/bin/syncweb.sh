#!/bin/bash
dir=$(dirname $0)
for i in ~/SEU/jboss/server/default/deploycaib/iam-ear.ear/iam-web-*.war
do
	rsync -rvz --exclude CVS --exclude WEB-INF $dir/../../main/webapp/ $i
done

#!/bin/bash

dir=$(dirname $0)
for i in ~/soffid/test/iam-console-3/work/soffid/iam-ear.ear/iam-web-*
do
	rsync -rvz --exclude CVS --exclude WEB-INF $dir/../../main/webapp/ $i
	rsync -rvz --exclude CVS --exclude WEB-INF $dir/../../main/resources/ $i/WEB-INF/classes
done


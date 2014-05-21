#!/bin/bash
SRC=$(dirname $0)/../../../target
TARGET=$HOME/SEU/jboss/server/default/deploycaib/iam-ear.ear
if [ -r $TARGET ]
then
   rm -rf $TARGET
fi
mkdir $TARGET
for i in $SRC/iam-ear-*/*
do
   filename=$(basename $i)
   if [[ $i =~ .*.war ]]
   then
       mkdir $TARGET/$filename
       unzip -d $TARGET/$filename $i
   else
       cp -r $i $TARGET
   fi
done
touch $TARGET/META-INF/application.xml

      

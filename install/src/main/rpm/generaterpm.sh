#!/bin/bash

function dump {
   cd "$1"
   local f=""
   for f in *
   do
     if [ "$f" = "." -o "$f" = ".." ]
     then
       true # Ignore
     elif [ -d "$f" ]
     then
         if [[  ! "$2/$f" == /lib* && ! "$2/$f" == /opt && ! "$2/$f" == /opt/soffid ]] 
         then
           echo "%dir \"$2/$f\""
         fi
         dumpFiles "$f" "$2/$f"
         dump "$f" "$2/$f"
     fi
   done
   cd ..
}

function dumpFiles {
   cd "$1"
   local f=""
   for f in *
   do
     if  [[ -f "$f" && ! "$f" == *env.sh && ! "$f" == *tomee.xml && ! "$f" == *server.xml && ! "$f" == *.bat && ! "$f" == *.exe ]]
     then
       echo "\"$2/$f\""
     fi
   done
   cd ..
}

dir=$(dirname $0)
base=$(realpath "$dir/../../..")

cd $base/target/rpm/dist

dump "" "" >>../soffid-iamconsole.spec
cd $base

fakeroot rpmbuild --target=noarch --buildroot $base/target/rpm/dist -bb $base/target/rpm/soffid-iamconsole.spec


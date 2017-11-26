#!/bin/bash

# Test environment variables
function configure {
	if [[ "$MARIADB_HOST" == "" ]]
	then
	    echo "Missing \$MARIADB_HOST environment variable. Exiting"
	    exit 1
	fi
	MARIADB_PORT=${MARIADB_PORT:-3306}
	if [[ "$MARIADB_DB" == "" ]]
	then
	    echo "Missing \$MARIADB_DB environment variable. Exiting"
	    exit 1
	fi
	
	if [[ "$MARIADB_USER" == "" ]]
	then
	    echo "Missing \$MARIADB_USER environment variable. Exiting"
	    exit 1
	fi
	
	if [[ "$MARIADB_PASS" == "" ]]
	then
	    echo "Missing \$MARIADB_PASS environment variable. Exiting"
	    exit 1
	fi

	if [[ "$JAVA_OPT" == "" ]]
	then
	    echo "-XX:MaxRAMFraction=0.7" >/opt/soffid/iam-console-2/bin/run.vmoptions
	    echo "-Xms256m" >>/opt/soffid/iam-console-2/bin/run.vmoptions
	else
	    echo "$JAVA_OPT" >/opt/soffid/iam-console-2/bin/run.vmoptions
	fi
    
	(
		echo 'tomee.serialization.class.blacklist = *'
		echo "dbUser=$MARIADB_USER"
		echo "dbPassword=$MARIADB_PASS"
		echo "dbValidationQuery=select 1"
		echo "dbDriverString=mysql"
		echo "dbDriverUrl=jdbc\:mysql\://$MARIADB_HOST\:$MARIADB_PORT/$MARIADB_DB"
	) > /opt/soffid/iam-console-2/conf/system.properties		

	touch /opt/soffid/iam-console-2/conf/configured 
	
	true
}



if [[ ! -f /opt/soffid/iam-console-2/conf/configured ]]
then
   configure || exit 1
fi


 exec /opt/soffid/iam-console-2/bin/run

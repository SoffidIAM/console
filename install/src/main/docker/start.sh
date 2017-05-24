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

    
	(	echo "dbExceptionSorter=org.jboss.resource.adapter.jdbc.vendor.MySQLExceptionSorter"
		echo "dbSchemaPassword=$MARIADB_PASS"
		echo "dbConnectionChecker=org.jboss.resource.adapter.jdbc.vendor.MySQLValidConnectionChecker"
		echo "dbSanitySelect=SET GLOBAL max_allowed_packet = 128000000;"
		echo "dbDriverClass=com.mysql.jdbc.Driver"
		echo "dbHost=$MARIADB_HOST"
		echo "i.dbDriver=0"
		echo "dbDriverUrl=jdbc\:mysql\://$MARIADB_HOST\:$MARIADB_PORT/$MARIADB_DB"
		echo "dbSchema=$MARIADB_USER"
		echo "dbStatus=1"
		echo "l.dbPort=$MARIADB_PORT"
		echo "dbSid=$MARIADB_DB"
		echo "dbUser=$MARIADB_USER"
		echo "dbDriverString=mysql"
	) > /opt/soffid/iam-console/jboss/server/default/conf/seu.properties		
	true
}



if [[ ! -f /opt/soffid/iam-console/jboss/server/default/conf/seu.properties ]]
then
   configure || exit 1
fi


 exec /opt/soffid/iam-console/run
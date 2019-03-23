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

	if [[ "$SECURE" == "true" ]]
	then
	    echo "-Djava.security.manager" >>/opt/soffid/iam-console-2/bin/run.vmoptions
        echo "-Djava.security.policy=/opt/soffid/iam-console-2/conf/catalina.policy" >>/opt/soffid/iam-console-2/bin/run.vmoptions
    fi
    
	(
		echo 'tomee.serialization.class.blacklist = *'
		echo "dbUser=$MARIADB_USER"
		echo "dbPassword=$MARIADB_PASS"
		echo "dbPasswordCipher=PlainText"
		echo "dbValidationQuery=select 1"
		echo "dbDriverString=mariadb"
		echo "dbDriverUrl=jdbc\:mariadb\://$MARIADB_HOST\:$MARIADB_PORT/$MARIADB_DB"
	) > /opt/soffid/iam-console-2/conf/system.properties		

	touch /opt/soffid/iam-console-2/conf/configured 
	
	true
}



if [[ ! -f /opt/soffid/iam-console-2/conf/configured ]]
then
   configure || exit 1
fi

if [[ "$JAVA_OPT" == "" ]]
then
    INSTALL4J_ADD_VM_PARAMS="-Xmx2048m -Xms256m"
else
    INSTALL4J_ADD_VM_PARAMS="$JAVA_OPT"
fi
export INSTALL4J_ADD_VM_PARAMS

if [[ "$SECURE" == "true" ]]
then
    echo "Starting SECURE Soffid IAM Console"
else
    echo "Starting Soffid IAM Console"
fi

exec /opt/soffid/iam-console-2/bin/run 

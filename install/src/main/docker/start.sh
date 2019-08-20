#!/bin/bash

# Test environment variables
function configure {
	if [[ "$DB_URL" == "" ]]
	then
		if [[ "$MARIADB_HOST" == "" ]]
		then
			echo "Missing \$MARIADB_HOST or \$DB_URL environment variable. Exiting"
			exit 1
		fi
		MARIADB_PORT=${MARIADB_PORT:-3306}
		if [[ "$MARIADB_DB" == "" ]]
		then
			echo "Missing \$DB_URL environment variable. Exiting"
			exit 1
		fi
		DB_URL="jdbc:mysql://$MARIADB_HOST:$MARIADB_PORT/$MARIADB_DB"
		DB_DRIVER="com.mysql.jdbc.Driver"
	fi
		
	if [[ "$MARIADB_PASS" == "" && "$DB_PASSWORD" == "" ]]
	then
	    echo "Missing \$DB_PASSWORD environment variable. Exiting"
	    exit 1
	fi

	if [[ "$MARIADB_USER" == "" && "$DB_USER" == "" ]]
	then
	    echo "Missing \$DB_USER environment variable. Exiting"
	    exit 1
	fi
	
	if [[ "$SECURE" == "true" ]]
	then
	    echo "-Djava.security.manager" >>/opt/soffid/iam-console-2/bin/run.vmoptions
        echo "-Djava.security.policy=/opt/soffid/iam-console-2/conf/catalina.policy" >>/opt/soffid/iam-console-2/bin/run.vmoptions
    fi
    
	if [[ "$DB_URL" == *:oracle:* ]] 
	then
		DB_DRIVER=oracle.jdbc.driver.OracleDriver
	elif [[ "$DB_URL" == *:sqlserver:* ]] 
	then
		DB_DRIVER=com.microsoft.sqlserver.jdbc.SQLServerDriver
	elif [[ "$DB_URL" == *:mysql:* ]] 
	then
		DB_DRIVER=com.mysql.jdbc.Driver
	elif [[ "$DB_URL" == *:mariadb:* ]] 
	then
		DB_DRIVER=org.mariadb.jdbc.Driver
	elif [[ "$DB_URL" == *:postgresql:* ]] 
	then
		DB_DRIVER=org.postgresql.Driver
	else
		echo "Cannot guess database driver for url $DB_URL" 
		exit 1
	fi

	(
		echo 'tomee.serialization.class.blacklist = *'
		echo "dbUser=${DB_USER:-$MARIADB_USER}"
		echo "dbPassword=${DB_PASSWORD:-$MARIADB_PASS}"
		echo "dbPasswordCipher=PlainText"
		if [[ "$DB_URL" == *:oracle:* ]] 
		then
			echo "dbValidationQuery=select 1 from dual"
			echo "dbDriverString=oracle"
		elif [[ "$DB_URL" == *:sqlserver:* ]] 
		then
			echo "dbValidationQuery=select 1 from sysobjects"
			echo "dbDriverString=sqlserver"
		elif [[ "$DB_URL" == *:mysql:* ]] 
		then
			echo "dbValidationQuery=select 1"
			echo "dbDriverString=mysql"
		elif [[ "$DB_URL" == *:mariadb:* ]] 
		then
			echo "dbValidationQuery=select 1"
			echo "dbDriverString=mariadb"
		elif [[ "$DB_URL" == *:postgresql:* ]] 
		then
			echo "dbValidationQuery=select 1"
			echo "dbDriverString=postgresql"
		else
			echo "Cannot guess database driver for url $DB_URL"
			exit 1
		fi
		echo "dbDriverUrl=$DB_URL"
	) > /opt/soffid/iam-console-2/conf/system.properties		

	cp /opt/soffid/iam-console-2/conf/tomee.xml  /opt/soffid/iam-console-2/conf/tomee.xml.bak
	sed -e 's/jdbcDriver.*/jdbcDriver = '"$DB_DRIVER"'/g'  </opt/soffid/iam-console-2/conf/tomee.xml.bak >/opt/soffid/iam-console-2/conf/tomee.xml

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

for trustedcert in /opt/soffid/iam-console-2/trustedcerts/*
do   
   if [[ -r "$trustedcert" ]]
   then
     echo "Loading $trustedcert"   
     keytool -import -keystore /etc/ssl/certs/java/cacerts -storepass changeit -noprompt -alias $(basename $trustedcert) -file "$trustedcert" -trustcacerts
   fi
done


if [[ "$SECURE" == "true" ]]
then
    echo "Starting SECURE Soffid IAM Console"
else
    echo "Starting Soffid IAM Console"
fi

exec /opt/soffid/iam-console-2/bin/run 

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
		echo "dbDriverClass=$DB_DRIVER"
		echo "dbDriverUrl=$DB_URL"
		echo "dbStatus=1"
		if [[ "$HIDE_MENU" != "" ]] 
		then
			echo "soffid.menu.hidden=$HIDE_MENU"
		fi
		if [[ "$AUTH_METHODS" != "" ]] 
		then
			echo "soffid.auth.methods=$AUTH_METHODS"
		fi 
		if [[ "$EXTERNAL_URL" != "" ]] 
		then
			echo "soffid.externalURL=$EXTERNAL_URL"
		fi
	) > /opt/soffid/iam-console-3/conf/system.properties		

    cp /opt/soffid/iam-console-3/conf/tomee.xml /opt/soffid/iam-console-3/conf/tomee.xml.template
	if [[ "$DB_URL" == *:oracle:* ]] 
	then
       cat /opt/soffid/iam-console-3/conf/tomee.xml.template | sed -e 's/validationQuery.*/validationQuery = select 1 from dual/'> /opt/soffid/iam-console-3/conf/tomee.xml 
	elif [[ "$DB_URL" == *:sqlserver:* ]] 
	then
       cat /opt/soffid/iam-console-3/conf/tomee.xml.template | sed -e 's/validationQuery.*/validationQuery = select 1 from sysobjects/'> /opt/soffid/iam-console-3/conf/tomee.xml 
	fi

	touch /opt/soffid/iam-console-3/conf/configured 
	
	true
}



if [[ ! -f /opt/soffid/iam-console-3/conf/configured ]]
then
   configure || exit 1
fi

if [[ "$JAVA_OPT" == "" ]]
then
    CATALINA_OPTS="-Xmx2048m -Xms256m"
else
    CATALINA_OPTS="$JAVA_OPT"
fi
export CATALINA_OPTS

for trustedcert in /opt/soffid/iam-console-3/trustedcerts/*
do   
   if [[ -r "$trustedcert" ]]
   then
     echo "Loading $trustedcert"   
     keytool -import -keystore /etc/ssl/certs/java/cacerts -storepass changeit -noprompt -alias $(basename $trustedcert) -file "$trustedcert" -trustcacerts
   fi
done

if [[ "$PROXY_HOST" != "" ]] 
then
   cp /opt/soffid/iam-console-3/conf/server.xml /opt/soffid/iam-console-3/conf/server.xml.template
   cat /opt/soffid/iam-console-3/conf/server.xml.template |
     sed -e "s/port=\"8080\"/port=\"8080\" proxyName=\"$PROXY_HOST\"/g"  \
     >opt/soffid/iam-console-3/conf/server.xml
fi

if [[ "$PROXY_PORT" != "" ]] 
then
   cp /opt/soffid/iam-console-3/conf/server.xml /opt/soffid/iam-console-3/conf/server.xml.template
   cat /opt/soffid/iam-console-3/conf/server.xml.template |
     sed -e "s/port=\"8080\"/port=\"8080\" proxyPort=\"$PROXY_PORT\"/g"  \
     >opt/soffid/iam-console-3/conf/server.xml
fi

if [[ "$PROXY_SCHEME" != "" ]] 
then
   cp /opt/soffid/iam-console-3/conf/server.xml /opt/soffid/iam-console-3/conf/server.xml.template
   cat /opt/soffid/iam-console-3/conf/server.xml.template |
     sed -e "s/port=\"8080\"/port=\"8080\" scheme=\"$PROXY_SCHEME\"/g" \
     >opt/soffid/iam-console-3/conf/server.xml
fi

if [[ "$DBPOOL_MIN_IDLE" != "" ]]
then
  sed -i -e "s/.*minIdle.*/minIdle = $DBPOOL_MIN_IDLE/" /opt/soffid/iam-console-3/conf/tomee.xml
fi

if [[ "$DBPOOL_MAX_IDLE" != "" ]]
then
  sed -i -e "s/.*maxIdle.*/maxIdle = $DBPOOL_MAX_IDLE/" /opt/soffid/iam-console-3/conf/tomee.xml
fi

if [[ "$DBPOOL_MAX" != "" ]]
then
  sed -i -e "s/.*maxActive.*/maxActive = $DBPOOL_MAX/" /opt/soffid/iam-console-3/conf/tomee.xml
fi

if [[ "$DBPOOL_INITIAL" != "" ]]
then
  sed -i -e "s/.*initialSize.*/initialSize = $DBPOOL_INITIAL/" /opt/soffid/iam-console-3/conf/tomee.xml
fi


if [[ "$SECURE" != "true" ]]
then
    echo "Starting SECURE Soffid IAM Console"
	exec /opt/soffid/iam-console-3/bin/catalina.sh run 
else
    echo "Starting Soffid IAM Console"
	exec /opt/soffid/iam-console-3/bin/catalina.sh run -security
fi


#!/bin/sh
#
# JBoss Control Script
#
# description: JBoss Applicaton Server / EJB Container
#
# To use this script
# run it as root - it will switch to the specified user
# It loses all console output - use the log.
#

##################################################################
# The following variables should be defined in /etc/rc.config.d/jboss.
# If they are not, the variables are defaulted below:
#
# INSTANCE - server instance name.  Normally "all", "default", or
#   "minimal". Maps to server instance at $JBOSS_HOME/server/$INSTANCE
#
# JBOSS_CONSOLE - file where jboss console logging will be written
#   Use "/dev/null" if console logging is not desired.
#
# JBOSS_START - set to "0" to force JBOSS to not start when this script
#   is run. Usefull when this script is run automatically at system
#   startup time. Default is "1" to startup.
#
# JBOSS_USER - it is username by which the jboss application server
#   is started. The default user is jboss.

# read config variables from files in /etc/rc.config.d
# by convention the config values should be in /etc/rc.config
# if this file is not present, we will attempt to deal with it be
# setting defaults in the program

if [ -f /etc/rc.config ] 
then
        . /etc/rc.config
else
	 print -u2 "ERROR: /etc/rc.config default file MISSING"

fi

INSTANCE=${INSTANCE:-"all"}
JBOSS_CONSOLE=${JBOSS_CONSOLE:-"/var/opt/jboss/jboss_${INSTANCE}.log"}
JBOSS_HOME=${JBOSS_HOME:-"/opt/jboss"}
JBOSS_USER=${JBOSS_USER:-"jboss"} 
PATH=/sbin:/usr/sbin:/usr/bin
export PATH

if [ ! -d ${JBOSS_CONSOLE%/*} ]
then
        mkdir -p ${JBOSS_CONSOLE%/*}
	chmod 0755 ${JBOSS_CONSOLE%/*} 
	chown $JBOSS_USER ${JBOSS_CONSOLE%/*}
fi


#define the scripts to use to start and stop jboss
CMD_START="export JAVA_HOME=$JAVA_HOME;$JBOSS_HOME/bin/run.sh -c $INSTANCE"
CMD_STOP="export JAVA_HOME=$JAVA_HOME;$JBOSS_HOME/bin/shutdown.sh --shutdown"

# Always run Jboss as user $JBOSS_USER
SUBIT="su - $JBOSS_USER -c"

if [ ! -d "$JBOSS_HOME" ]; then
  print -u2 "ERROR: JBOSS_HOME does not exist as a valid directory : $JBOSS_HOME"
  exit 1
fi

ps -eax|grep "org\.jboss\.Main"|grep -v "grep" 2>/dev/null 1>/dev/null
NOT_RUNNING=$?

case "$1" in
start_msg)
    echo "Starting JBoss if needed"
    ;;
start)
    [ "$JBOSS_START" -ne "1" ] && exit 0
    if [ $NOT_RUNNING -eq 1 ]
    then
   	        nohup sh "$SUBIT \"$CMD_START\" >${JBOSS_CONSOLE} 2>&1 &" >/dev/null
  		sleep 5
    		ps -eax|grep "org\.jboss\.Main"|grep -v "grep" 2>/dev/null 1>/dev/null
	        if [ $? -eq 0 ]
		then
			echo "JBoss Server Started"
	 		rval=0
		else
			print -u2 "ERROR :Unable to Start JBoss Server"
			print -u2 "       See ${JBOSS_CONSOLE} for details"
			rval=1
		fi
    else
    	print -u2  "ERROR: Another Instance of JBoss is running"
        rval=2
    fi
    ;;
stop_msg)
    echo "Stopping JBoss"
    ;;
stop)
    nohup sh "$SUBIT \"$CMD_STOP\" >>${JBOSS_CONSOLE} 2>&1" >/dev/null
    sleep 10
    ps -eax|grep "org\.jboss\.Main"|grep -v "grep" 2>/dev/null 1>/dev/null
    if [ $? -eq 0 ]
    then
	 ps -eax|grep "org\.jboss\.Main"|grep -v "grep"|awk '{print $1}'|xargs kill -9 2>/dev/null 1>/dev/null
         if [ $? -eq 0 ]
	 then
    		sleep 5
		echo "JBoss Server Stopped"
         	rval=0
         else
		print -u2 "ERROR: Unable to Stop JBoss Server"
		rval=1
	 fi
    else
	 echo "JBoss Server Stopped"
	 rval=0
    fi
    ;;

restart)
    $0 stop
    $0 start
    ;;
*)
    echo "usage: $0 (start|stop|restart|help)"
esac

exit $rval

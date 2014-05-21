#!/bin/sh
#
# JBoss Control Script
#
# chkconfig: 3 80 20
# description: JBoss EJB Container
# 
# To use this script
# run it as root - it will switch to the specified user
# It loses all console output - use the log.
#
# Here is a little (and extremely primitive) 
# startup/shutdown script for RedHat systems. It assumes 
# that JBoss lives in /usr/local/jboss, it's run by user 
# 'jboss' and JDK binaries are in /usr/local/jdk/bin. All 
# this can be changed in the script itself. 
# Bojan 
#
# Either amend this script for your requirements
# or just ensure that the following variables are set correctly 
# before calling the script

# [ #420297 ] JBoss startup/shutdown for RedHat

JBOSS_HOME=${JBOSS_HOME:-"/opt/SEU/jboss"}
JAVA_HOME=${JAVA_HOME:-"/usr/lib/jvm/java"}
JBOSSSH=${JBOSSSH:-"$JBOSS_HOME/bin/run.sh -c default"}
JBOSS_CONSOLE=${JBOSSSH:-"/var/log/setstartup.log"}

case "$1" in
start)
    cd $JBOSS_HOME/bin
    if [ -z "$SUBIT" ]; then
        eval $CMD_START >${JBOSS_CONSOLE} 2>&1 &
    else
        $SUBIT "$CMD_START >${JBOSS_CONSOLE} 2>&1 &" 
    fi
    ;;
stop)
    if [ -z "$SUBIT" ]; then
        $CMD_STOP
    else
        $SUBIT "$CMD_STOP"
    fi 
    ;;
restart)
    $0 stop
    $0 start
    ;;
*)
    echo "usage: $0 (start|stop|restart|help)"
esac



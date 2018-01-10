#!/usr/bin/env bash

JVM_OPTIONS="-server -Xms200m -Xmx200m -XX:MaxPermSize=20M"

usage(){
  echo "Usage: ./startup.sh [options] 
	:
    --start start app
    --stop  stop app"
}

start() {
  JVM_OPTIONS=$JVM_OPTIONS" -Duser.dir=$CF_HOME"
  echo "jvm options:$JVM_OPTIONS"
  java $JVM_OPTIONS -jar $FOUR_HOME/target/connectfour.jar start  
}

stop() {
  java -jar $FOUR_HOME/connectfour.jar stop
}

casage() {
  case $1 in
        --help)
            usage
        ;;
        --start)
          start
        ;;
        --stop)
          stop
        ;;
        *)
        error_info "Error, can't find option. Please try again."
        ;;
    esac
}


if [ -z $FOUR_HOME  ]
 then
  CF_BIN=`pwd`
  FOUR_HOME=$(dirname $CF_BIN)
fi

if [ $# == 0  ] || [ $# -gt 1  ]
 then
    usage
else
    command=$1
    casage $command
fi
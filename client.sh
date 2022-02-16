#!/bin/sh
if [ -z "$3" ]
  then
    java -jar Msg-Client-1.0-SNAPSHOT-jar-with-dependencies.jar -client_id $1 -port $2 
  else
    java -jar Msg-Client-1.0-SNAPSHOT-jar-with-dependencies.jar -client_id $1 -port $2 -group_id $3 
fi
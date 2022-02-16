#!/bin/sh
  
# Start the server process and put it in the background
/server.sh 7000 &
sleep 5
  
# Start the clients process
(echo "hello"; echo "Alice is online"; sleep 5 && echo "-quit" )| /client.sh Alice 7000 1
sleep 2 & echo "wait Bob and Chad ......"
sleep 5 # sleep 5 seconds wait Bob and Chad
(sleep 5 && echo "hello" && echo "Bob is online"; sleep 2; echo "-quit") | /client.sh Bob 7000 1
# (sleep 5 && echo "-quit" )| /client.sh Alice 7000 1
(sleep 5 && echo "hello" &&echo "Chad is online"; sleep 2; echo "-quit") | /client.sh Chad 7000 1
(sleep 5 && echo "hello" &&echo "Doug is online"; sleep 2; echo "-quit") | /client.sh Doug 7000
#! /bin/sh

sleep 5
REQ=0
while [ $REQ -lt $NUMBER_REQUESTS ]
do
  RESP=$(curl -s -H "Accept: application/json" ${HOST_NAME})
  echo $RESP
  REQ=`expr $REQ + 1`
done
#!/bin/bash

BASE_URL=https://127.0.0.1:443

curl --cert-type P12 --cert keystore.p12:abcdef -kvX POST  $BASE_URL/api/v1/products \
    -H 'content-type: application/json'\
  -d ' { "name" : "loan", "price" : "23424442.32"}' | jq .

#curl -kvX POST  $BASE_URL/api/v1/products \
    #-H 'content-type: application/json'\
  #-d ' { "price" : "234242"}' | jq .

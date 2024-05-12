#!/bin/bash

curl -kvX POST  localhost:8080/api/v1/products \
    -H 'content-type: application/json'\
  -d ' { "price" : "234242"}' | jq .

#curl -kvX POST  localhost:8080/api/v1/products \
    #-H 'content-type: application/json'\
  #-d ' { "name" : "loan", "price" : "234242"}' | jq .

#!/bin/bash

curl -kvX PUT  localhost:8080/api/v1/products \
    -H 'content-type: application/json'\
  -d ' {"id": "2", "name" : "newloan", "price" : "234242"}' | jq .

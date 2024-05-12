#!/bin/bash

BASE_URL=https://127.0.0.1:443

curl --cert-type P12 --cert keystore.p12:abcdef -kvX GET $BASE_URL/api/v1/products | jq .

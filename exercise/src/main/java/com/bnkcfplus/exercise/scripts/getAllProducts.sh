#!/bin/bash

curl -kvX GET localhost:8080/api/v1/products | jq .

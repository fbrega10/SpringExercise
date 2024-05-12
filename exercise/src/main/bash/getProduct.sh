#!/bin/bash

curl --cacert keystore.p12  -kvX GET  https://127.0.0.1:443/api/v1/products/2 | jq .

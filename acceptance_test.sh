#!/bin/bash

set -x

NODE_IP=$(kubectl get nodes -n docker-test -o jsonpath='{ $.item[0].status.addresses[?(@.type=="INTERNAL-IP")].address }')
NODE_PORT=$(kubectl get svc calculator-service -n docker-test -o=jsonpath='{.spec.ports[0].nodePort}')
./gradlew acceptanceTest -Dcalculator.url=http://${NODE_IP}:${NODE_PORT}

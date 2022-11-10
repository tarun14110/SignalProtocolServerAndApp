#!/bin/bash

mvn clean package
cp coniks_test_client/target/coniks_test_client-0.4.0.jar ../signal-cli-modified/lib/

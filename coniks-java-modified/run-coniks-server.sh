#!/bin/bash

cd coniks_server
chmod ug+rwxs logs
rm -rf logs/*
./coniks_server.sh test

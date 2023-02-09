#!/bin/bash

mkdir -p /data/log/tor/
tor 1> /data/log/tor/tor.log 2> /data/log/tor/tor-err.log &
cp /data/tor/hostname /data/tor-hostname
chmod 644 /data/tor-hostname

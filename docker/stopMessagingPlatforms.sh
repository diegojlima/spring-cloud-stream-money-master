#!/bin/sh
clear
echo ===== STOP MESSAGING PLATFORMS =====
echo
docker stop kafka
docker stop zookeeper
echo
docker ps

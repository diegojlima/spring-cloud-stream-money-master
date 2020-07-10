#!/bin/sh
clear
echo ===== START MESSAGING PLATFORMS =====
echo
docker start zookeeper
docker start kafka
echo
docker ps

#!/usr/bin/env bash
echo "running applicationStart script"
nohup ./executeJar.sh 1>/home/ec2-user/serverJar/springOut.txt 2>/home/ec2-user/serverJar/springErr.txt &
echo "completed applicationStart script"
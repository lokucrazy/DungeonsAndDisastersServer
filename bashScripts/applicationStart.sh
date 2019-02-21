#!/usr/bin/env bash
echo "running applicationStart script"
nohup source /etc/environment && java -jar /home/ec2-user/serverJar/server-0.0.1-SNAPSHOT.jar 1>/home/ec2-user/serverJar/springOut.txt 2>/home/ec2-user/serverJar/springErr.txt &
echo "completed applicationStart script"
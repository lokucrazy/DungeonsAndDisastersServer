#!/usr/bin/env bash
source /etc/environment
pidNumber=$(lsof -t -i :80)
if [ "$pidNumber" != "" ]
then
    kill $pidNumber
fi
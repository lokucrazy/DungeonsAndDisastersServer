#!/usr/bin/env bash
pidNumber=$(lsof -t -i :80)
if [ "$pidNumber" != "" ]
then
    kill $pidNumber
fi
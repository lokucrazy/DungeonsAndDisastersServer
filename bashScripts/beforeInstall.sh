source /etc/environment
pidNumber=$(lsof -t -i :8080)
if [ "$pidNumber" != "" ]
then
    kill $pidNumber
fi
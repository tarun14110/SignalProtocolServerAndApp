#!/bin/bash

NUM1="+15555555555"
NUM2="+16666666666"

./signal-cli -u $NUM1 unregister
./signal-cli -u $NUM2 unregister
./clean.sh

##########################
echo "Single user test..."
sleep 1
./signal-cli -u $NUM1 register
#read -p "First verification code: " FIRST
#./signal-cli -u $NUM1 verify $FIRST
./signal-cli -u $NUM1 verify 1

echo Waiting one minute for epoch...
sleep 60
while ! ./signal-cli -u $NUM1 epoch; do read; done
echo "Single user test passed"
echo
echo
echo



#######################
echo "STR History Test"
sleep 1
echo Waiting one minute for another epoch...
sleep 60
while ! ./signal-cli -u $NUM1 epoch; do read; done
echo "STR history test passed"
echo
echo
echo


##########################
echo "Second user test..."
sleep 1
./signal-cli -u $NUM2 register
#read -p "Second verification code: " SECOND
#./signal-cli -u $NUM2 verify $SECOND
./signal-cli -u $NUM2 verify 1

echo Waiting one minute for epoch...
sleep 60
while ! ./signal-cli -u $NUM2 epoch;
do read; done;
echo "Second user test passed"
echo
echo
echo


#########################
echo "Contact testing..."
sleep 1
./signal-cli -u $NUM1 updateContact -n Num2 $NUM2
./signal-cli -u $NUM2 updateContact -n Num1 $NUM1
./signal-cli -u $NUM1 send -m "Hey" $NUM2
./signal-cli -u $NUM2 receive
./signal-cli -u $NUM2 send -m "Wassup" $NUM1
./signal-cli -u $NUM1 receive

while ! (./signal-cli -u $NUM1 epoch && ./signal-cli -u $NUM2 epoch);
do read; done;
echo "Contact test passed"
echo
echo
echo


###############################
echo "STR Receiving Testing..."
sleep 1
./signal-cli -u $NUM1 receive
./signal-cli -u $NUM2 receive

#!/bin/bash

if [ "$1" = "-p" ]
then
    mvn clean install
	if [ "$2" == "" ]
	then
		java -jar target/com-0.0.1-SNAPSHOT.jar
	else
		java -jar target/com-0.0.1-SNAPSHOT.jar $2
	fi	
elif [ "$1" = "-h" ]
then
    echo " 1. This project is divided into 3 parts
    a. Controller
        It is the main contoller will call all other methods.<br />
    b. Model 
      This contains bean classes
        i. Clicks
        ii. Imps
    c. Operations 
        In this package we have Classes which perform operations on data 
        Like 
            i. Reading data from files.
            ii. Storing them in the form in memory.
            iii. Then writing the data into json files."
else
    mvn clean install
	java -jar target/com-0.0.1-SNAPSHOT.jar
	
fi	
#Commands to run the project<br />
	1. mvn clean install
	2. Default parallelization
		a. java -jar target\com-0.0.1-SNAPSHOT.jar
	   Parametrized parallelization
		b. java -jar target\com-0.0.1-SNAPSHOT.jar <count>
	   
#Help <br />
##Project structure
####	1. This project is divided into 3 parts
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
				iii. Then writing the data into json files.
#Running build.sh <br />
	1. build.sh -h
		a. Gives the help regarding the project.
	2. build.sh 
		a. Runs with default parallelization of 5
	3. build.sh -p
		a. Runs with default parallelization of 5
	4. build.sh -p <value>
		a. Runs with default parallelization of equal to value.
#Sample screenshots <br />
	
![alt tag](https://github.com/ManoranjanSriramagiri/XAd/blob/master/ScreenShots/FolderStructure.PNG,"Folder Structure")
	2. Sample Output
		![alt tag](https://github.com/ManoranjanSriramagiri/XAd/blob/master/ScreenShots/SampleOutPut.PNG)
	3. ETL logs
		![alt tag](https://github.com/ManoranjanSriramagiri/XAd/blob/master/ScreenShots/ETLlogs.PNG)

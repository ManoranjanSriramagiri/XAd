#Commands to run the project<br />
	1. mvn clean install
	2. Default parallelization
		a. java -jar target\com-0.0.1-SNAPSHOT.jar
	   Parametrized parallelization
		b. java -jar target\com-0.0.1-SNAPSHOT.jar <count>
	   
#Help <br />
##Project structure<br />
###	1. This project is divided into 3 parts<br />
		####a. Controller<br />
			It is the main contoller will call all other methods.<br />
	####	b. Model <br />
		This contains bean classes<br />
			i. Clicks<br />
			ii. Imps<br />
	####	c. Operations <br />
			In this package we have Classes which perform operations on data <br />
			Like <br />
				i. Reading data from files.<br />
				ii. Storing them in the form in memory.<br />
				iii. Then writing the data into json files.<br />

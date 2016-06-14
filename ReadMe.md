Commands to run the project<br />
	1. mvn clean install
	2. Default parallelization
		a. java -jar target\com-0.0.1-SNAPSHOT.jar
	   Parametrized parallelization
		b. java -jar target\com-0.0.1-SNAPSHOT.jar <count>
	   
Help 
Project structure
1. This project is divided into 3 parts
	a. Controller
		It is the main contoller will call all other methods.
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

## Book Broker App
### Description :
```
   For this Application we'll be adding a new feature to Book Club application,
   the Book Broker dashboard and we'll be Testing application using JUnit5 and mockito
   In fact, i'll need to use the last assignment to build upon. However,
   we will be altering the relationships and models to add this functionality
```


### Objectives :
```
  -> Implement full CRUD, adding update and delete to your application
  -> Implement multiple one-to-many relationships in an application
  -> Alter, assign, and re-assign many-to-one relationships
  -> Use a nullable field in a relationship
  -> Testing all models of our application : we write out an individual test case for each field.
  -> Testing Repositories using JUnit5 and some Assertions methods
  -> Use mockito by creating mock objects and testing our Services
```

### Writing Tests: We will use JUnit5 and Mockito for unit testing application
```
  Create test classes and methods using annotations (e.g., @Test).
  Use JUnit assertions and Mockito for mocking external dependencies.
 ```

### Installation and Usage:
  ### clone/fork this repo, then git pull to get the latest changes


### Dependencies : add dependencies to pom.xml file (make sure you have the necessary dependencies for testing)
```
  <!-- DEPENDENCIES FOR STARTING SPRING PROJECTS-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<!-- DEPENDENCY FOR USING H2 DATAASE & junit  & assertj  -->
		<dependency>
		    <groupId>com.h2database</groupId>
		    <artifactId>h2</artifactId>
		    <scope>test</scope>
		</dependency>
		<dependency>
		    <groupId>junit</groupId>
		    <artifactId>junit</artifactId>
		    <scope>test</scope>
		</dependency>
		<dependency>
		    <groupId>org.assertj</groupId>
		    <artifactId>assertj-core</artifactId>
		    <scope>test</scope>
		</dependency>
		<!-- DEPENDENCIES FOR DISPLAYING JSPS AND USING JSTL TAGS -->
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
		<dependency>
			<groupId>jakarta.servlet.jsp.jstl</groupId>
			<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
		</dependency>
		<dependency>
			<groupId>org.glassfish.web</groupId>
			<artifactId>jakarta.servlet.jsp.jstl</artifactId>
		</dependency>
		<!-- DEPENDENCIES FOR INTEGRATING SQL DATABASE AND USING JPA -->
		<!-- Note: Project will not run until a schema has been created and the
    		proper settings in application properties are present for
    		connecting to a database. -->
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- DEPENDENCY FOR USING VALIDATION ANNOTATIONS -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<!-- DEPENDENCY FOR USING BCRYPT  -->
		<dependency>
			<groupId>org.mindrot</groupId>
			<artifactId>jbcrypt</artifactId>
			<version>0.4</version>
		</dependency>
		<!-- DEPENDENCIES FOR BOOTSTRAP -->
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>webjars-locator</artifactId>
			<version>0.46</version>
		</dependency>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>5.2.3</version>
		</dependency>
		
```
### Initialize the database for application (we create the schema) and connect to views :
#### applications.proerties file
```
# Where are jsp files? HERE!
spring.mvc.view.prefix=/WEB-INF/
# Data Persistence
spring.datasource.url=jdbc:mysql://localhost:3306/book_club
spring.datasource.username=root
spring.datasource.password=root1
spring.jpa.hibernate.ddl-auto=update
# For Update and Delete method hidden inputs
spring.mvc.hiddenmethod.filter.enabled=true
server.port=8082
```
### Initialize the database for testing application using H2 db
#### app.properties file :
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=tn
spring.datasource.password=password
# refresh database every time we run tests 
spring.jpa.hibernate.ddl-auto=create-drop
# display sql queries in the console
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
#### Run the app using Eclipse:
```
  Choose your Spring Boot project.
  Select Run As ➡️ Java Application.
  Select it and hit OK.
  Open browser [http://localhost:8082]
```

#### Run the tests :
```
  ➡️ Choose your Spring Boot project
  --> Select Run As ➡️ JUnit Test
  --> Select it and hit OK
  --> JUnit will execute the test methods and report the results.
```



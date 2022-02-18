# Apache Camel

## Apache camel is a ETL framework for enterprise integration pattern that is used for routing and mediation rules.
## Provides 100+ components for databases, message queues, APIs, cloud integration etc
## Supports 200+ protocols, transports and data formats (& 300+ converters) 

## It’s most suited for situations where you want to fetch data from files or applications, process and combine it with other data, and then move it to another application.

### It has two main use cases 
### transform - changes the body of the message 
### processing - does not change the body of the message

### The main concept is to have our component class extend the RouteBuilder which overrides the configure() method where we can configure our routes and our bussiness logic.  

## Important terminologyies
```xml
Camel Context - (0..n) Routes + Components + ..
	Endpoint - Reference to a queue, database or a file
	Route - Endpoints + Processor(s) + Transformer(s)
	Components - Extensions (Kafka, JSON, JMS etc)
	Transformation:
		Data format transformation - XML to JSON
		Data type transformation - String to CurrencyConversionBean
Message - Body + Headers + Attachments
Exchange - Request + Response
	Exchange ID
	Message Exchange Pattern (MEP) - InOnly/InOut
	Input Message and (Optional) Output Message
```

### 1) MyFirstTimerRouter - General sample that shows the ability of camel transformation and processing

### 2) MyFileRouter - This example is used for moving files instantly between folders 

### For the below example to work, start active mq in a docker container using the command:  
### docker pull rmohr/activemq
### docker run -p 61616:61616 -p 8161:8161 rmohr/activemq 

### 3) ActiveMQProducer - This is used for producing messages on the ActiveMQ server

### 4) ActiveMQConsumer - This is used for consuming messages from the ActiveMQ server

### Please check the pom.xml file for dependencies needed and the application.properties files for the activemq broker url


************************** Rearranged the above examples with more comments below *********************************************

## Create two microservices microservice-a and microservice-b

## microservice-a

```xml
As a general principal to use any of the below files just comment/uncomment the line containing @Component and run the application 

1. SimpleTimerRoute01 
This will create a timer called in-timer and write a constant message to the output log out-log

2. SimpleTimerRoute02
This will instead of writing a constant message, will create a dynamic message from a bean that will be written to the output log

3. SimpleTimerRoute03
This will show how the body of the message is processed/transformed at different stages from the time it is read to the time it is written to the log

4. SimpleTimerRoute04
Here we call our own class which will have the process logic after which we write it to the body of the message before sending it to the log

5. FileRouter
This will move files from a source folder to a destination folder the moment it is placed on the source folder. 
In turn it will read the content of the file which can also be processed/transformed if needed 

6. ActiveMQSenderRouter (related - ActiveMQReceiverRouter)
This will put a message into the ActiveMQ message queue in a specified duration set by the timer (queue must be created first on activemq)
For this to work add the below dependency to the pom.xml file
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-activemq-starter</artifactId>
	<version>3.14.1</version> -> Same version as camel-spring-boot-starter that is already added
</dependency>
Also add the following to the application.properties file: 
spring.activemq.broker-url=tcp://localhost:61616

7. JsonFileToApacheMQSender (related - ActiveMQToJsonConverter)
This will put a message into the ActiveMQ message queue once a file is added into the directory called json inside the data folder. 

8. XMLFileToApacheMQSender
This will put a message into the ActiveMQ message queue once a file is added into the directory called xml inside the data folder. 


```

## launch activemq on docker 
```xml
docker pull rmohr/activemq
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
Open the URL in the browser -> http://0.0.0.0:8161/ (MAC) or http://localhost:8161/ (Windows)
Click 'Manage ActiveMQ broker'
Default user id and pwd: admin & admin

```

## microservice-b
```xml
1. ActiveMQReceiverRouter (related - ActiveMQSenderRouter)
This will get a message from the ActiveMQ message queue as soon as it has been put by the producer 
For this to work add the below dependency to the pom.xml file
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-activemq-starter</artifactId>
	<version>3.14.1</version> -> Same version as camel-spring-boot-starter that is already added
</dependency>
Also add the following to the application.properties file: 
server.port=8000
spring.activemq.broker-url=tcp://localhost:61616


2. ActiveMQToJsonConverter (related - JsonFileToApacheMQSender)
This will read the activemq message and convert the message body that is received in a json format to the associated class file 
First add the following dependency into pom.xml which will help us to map the Json format that is received and convert it into a class 
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-jackson-starter</artifactId>
	<version>3.14.1</version>
</dependency>

Next add the mapped class called CurrencyConverter into the utils package. This class is used for mapping the json body tha is received. 


3. 


```




Reference:
https://www.udemy.com/course/apache-camel-framework-with-spring-boot
https://camel.apache.org/components/3.14.x/

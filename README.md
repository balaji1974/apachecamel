# apachecamel

## Apache camel is a ETL framework for enterprise integration pattern that is used for routing and mediation rules.

### It has two main use cases 
### transform - changes the body of the message 
### processing - does not change the body of the message

### The main concept is to have our component class extend the RouteBuilder which overrides the configure() method where we can configure our routes and our bussiness logic.  

### 1) MyFirstTimerRouter - General sample that shows the ability of camel transformation and processing

### 2) MyFileRouter - This example is used for moving files instantly between folders 

### For the below example to work, start active mq in a docker container using the command:  docker run -p 61616:61616 -p 8161:8161 rmohr/activemq 

### 3) ActiveMQProducer - This is used for producing messages on the ActiveMQ server

### 4) ActiveMQConsumer - This is used for consuming messages from the ActiveMQ server

### Please check the pom.xml file for dependencies needed and the application.properties files for the activemq broker url


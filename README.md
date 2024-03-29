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

DSL - Camel uses Domain Specification Language 
```

## Important Note: To run any of the below programs, please remove the @Component tag inside the router package of that program 

### 1) MyFirstTimerRouter - General sample that shows the ability of camel transformation and processing

### 2) MyFileRouter - This example is used for moving files instantly between folders 

### For the below example to work, start active mq in a docker container using the command:  
### docker pull rmohr/activemq
### docker run -p 61616:61616 -p 8161:8161 rmohr/activemq 

### 3) ActiveMQProducer - This is used for producing messages on the ActiveMQ server

### 4) ActiveMQConsumer - This is used for consuming messages from the ActiveMQ server

### Please check the pom.xml file for dependencies needed and the application.properties files for the activemq broker url


## Minimum dependencies needed for Apache Camel Spring boot applications to run 
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-spring-boot-starter</artifactId>
	<version>3.15.0</version>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<scope>runtime</scope>
	<optional>true</optional>
</dependency>
```

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

9. Kafka Producer: (KafkaSenderRouter)
For installing and starting Kafka I have detail instruction in my Kakfa repo.
Add the following Kafka dependency:
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-kafka-starter</artifactId>
	<version>3.14.1</version>
</dependency>

Add the following in the properties file
camel.component.kafka.brokers=localhost:9092

Next configure the router. Refer KafkaSenderRouter.java

10. Rest API Consumer (RestAPIConsumerRouter.java)
We need to add the following dependency for making REST API calls using camel: 
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-http-starter</artifactId>
	<version>3.14.1</version>
</dependency>

This example makes a rest call every 10 seconds 

11. Choice / Decision making in routing (ChoiceRouter.java)
This will check the file extension and based on the file type, it will perform action

Check the camel simple language options that can be used from the following url:
https://camel.apache.org/components/3.15.x/languages/simple-language.html

12. Create a reusable camel component that can be used in the entire camel context (ReusableLogRoutes.java)
// this will create a reusable component block that can be used in the entire camel context 
from("direct:log-data")
.log("${messageHistory} ${file:absolute.path}")
.log("${file:name} ${file:ext} ")
; 

This can be used in our routing in the following way: 
.to("direct://log-data")

13. Handling complex decision making choices with a Decider Bean (ComplexDeciderRoutes.java)
Complex decision making can be delgated to its own bean and this example shows this scenario. 

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


3. ApacheMQToXMLProcessorTransfomer
This will read a message from the ActiveMQ message queue and convert it to xml format and send it to log file. 

Dependency needed for this is 
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-jacksonxml-starter</artifactId>
	<version>3.14.1</version>
</dependency>

4. Kafka Consumer: (KafkaReceiverRouter.java)
For installing and starting Kafka I have detail instruction in my Kakfa repo.
Add the following Kafka dependency:
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-kafka-starter</artifactId>
	<version>3.14.1</version>
</dependency>

Add the following in the properties file
camel.component.kafka.brokers=localhost:9092

Next configure the router. Refer KafkaReceiverRouter.java

5. Creating a rest end point that will send data (SimpleController.java)

6. ActiveMQExcelReceiver 
(this will read messages that are sent from the SplitterPattern.java program under the enterprise Integration Patterns )
Also add the following the application.properties file: 
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.packages.trust-all=true 

7. ActiveMQStringSeperatorReceiver
(this will read messages that are sent from the SplitBySeperator.java program under the enterprise Integration Patterns )

```


## Enterprise Integration Patterns 
```xml
1. Pipeline Pattern (PipelinePattern.java) 
Here data flow is through a pipeline 

2. Multicast Pattern (MutlicastPattern.java)
Here data can be channelled to mulitple end points

3. Content Based Routing Pattern (ContentBasedRoutingPattern.java)
We can route based on the content - Eg. using choice()

4 a. Splitter Routing Pattern (SplitterPattern.java)
Add the following dependency 
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-csv-starter</artifactId>
	<version>3.15.0</version>
</dependency>
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-activemq-starter</artifactId>
	<version>3.15.0</version>
</dependency>

Add the following to the application.properties file:
spring.activemq.broker-url=tcp://localhost:61616

Here each line in the excel will be spilt as a seperate message and processed and sent to activemq

For the receiving part of this message please refer to ActiveMQExcelReceiver.java in the microservice-b project 

4 b. Split messages based on a seperator (SplitBySeperator.java)
This will split the file by a seperator that is specified and send each item from the split as a message 

5. Aggregator Pattern (AggregatorPattern.java)
Please add the following dependency 
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-jackson-starter</artifactId>
	<version>3.15.0</version>
</dependency>

This will look for the body of the json to contain 'to' string and then add 3 such messages into a single message - aggregate everything based on the to tag and send it to the defined output 

This follows an AggregationStrategy defined in ArrayListAggregationStrategy.java 

6. Routing Slip Pattern (RoutingSlipPattern.java)
Here we can dynamically route to any endpoints based on a routing slip that we configure 

6. Dynamic Routing Pattern (DynamicRoutingPattern.java)
Route to different endpoints upon completing of different parts of the program (steps)


```


## Best Practises 
```xml
1. We can define property variables and use it in our camel configuations.
Eg. in application.properties
end-point-for-logging=log:endpoint1

and we can use this like:
from("direct:endpoint1")
.to({{end-point-for-logging}});

2. To keep the camel context JVM to run continously even when not installing the with spring-boot-starter-web, 
typically when using the spring boot in standalone mode is to set the below in your application.properties file: 
camel.springboot.main-run-controller=true 

To shut down a Camel application after a fixed period of time:
camel.springboot.duration-max-seconds=60

3. Set the logging levels appropriately. Eg. 
logging.level.org.apache.camel.spring.boot=INFO 
logging.level.org.apache.camel.impl=DEBUG

4. Enable tracing to check detail information of the routing from the class that implements the RouteBuilder 
getContext().setTracing(true); 

5. Configure a dead letter queue (to ensure that no message is lost) on the class that implements the RouteBuilder 
errorHandler(deadLetterChannel("activemq:dead-letter-queue")); 

6. Wire Tap from the EIP patterns allows you to route messages to a separate location while they are being forwarded to the ultimate destination. Eg. 
from("direct:endpoint1")
	.wireTap("log:wiretap-log")
	.to("log:endpoint1");

7. Secure messages with encryption
a. Add the dependency in pom.xml 
<dependency>
	<groupId>org.apache.camel.springboot</groupId>
	<artifactId>camel-crypto-starter</artifactId>
	<version>3.15.0</version>
</dependency>

b. Create keys
keytool -genseckey -alias myDesKey -keypass someKeyPassword -keystore myDesKey.jceks -storepass someKeystorePassword -v -storetype JCEKS -keyalg DES

c. Put the generated key file in the classpath of the both the sender and receiver projects 

d. Method to read from Key Store, add this in the RouteBuilder class of both the sender and receiver java files. 
private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException,
		CertificateException, UnrecoverableKeyException {
	KeyStore keyStore = KeyStore.getInstance("JCEKS");
	ClassLoader classLoader = getClass().getClassLoader();
	keyStore.load(classLoader.getResourceAsStream("myDesKey.jceks"), "someKeystorePassword".toCharArray());
	Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

	CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);
	return sharedKeyCrypto;
}

e. Finally use this encryptor function:
from("timer:active-mq-timer?period=10000") // A timer that gets triggered every 10 seconds - 10000 milliseconds
		.transform().constant("My ActiveMQ constant message") // Sends a constant message 
		.log("${body}")
		.marshall(createEncryptor()) // Encryption is done here before sending the message 
		.to("activemq:my-activemq-queue"); // Sends it to the ActiveMQ queue my-activemq-queue - This must be created first in activemq


f. And on the receiving side we need to do the following for decrypting the message:
from("activemq:my-activemq-queue") // Read the messages fom an activemq queue  my-activemq-queue
		.unmarshall(createEncryptor()) // Decrypt is done here after receving the message 
		.to("log:received-msg-from-activemq"); // Write it to the output log

```

## Useful links 

```xml
Why Camel? - https://camel.apache.org/manual/latest/faq/why-the-name-camel.html
Camel Examples - https://github.com/apache/camel-examples/tree/master/examples
Camel Spring Boot Configuration - https://camel.apache.org/camel-spring-boot/latest/spring-boot.html
Complete Spring Boot Starters List - https://camel.apache.org/camel-spring-boot/latest/list.html 
Camel Spring Boot Examples - https://github.com/apache/camel-spring-boot-examples
Enterprise Integration Patterns - https://camel.apache.org/components/latest/eips/enterprise-integrationpatterns.html

``` 


Reference:
https://www.udemy.com/course/apache-camel-framework-with-spring-boot
https://camel.apache.org/components/3.14.x/
https://camel.apache.org/components/3.15.x/languages/simple-language.html

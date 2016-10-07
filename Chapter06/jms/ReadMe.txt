
A JMS client web applications send message to a queue 
and an MDB listens for the message and prints it.

Building
--------
1. ant build
This will create JMSClient-1.0.war and JMSClient-1.0.jar

Deploying
---------

1. Deploy the RAR
deploy.bat --user system --password  manager deploy activemq-ra-3.2.1.rar my-JMS-resources-plan.xml

2. Deploy the JMS client WAR

deploy.bat --user system --password  manager deploy JMSClient-1.0.war

3. Deploy the JMS MDB Client JAR
deploy.bat --user system --password  manager deploy JMSClient-1.0.jar


UnDeploying
------------
1. To undeploy Web application 

deploy.bat --user system --password  manager undeploy JMSClient

2. To undeploy MDB
deploy.bat --user system --password  manager undeploy JMSClientMDB

3. To undeploy RAR
deploy.bat --user system --password  manager undeploy SampleJMSResources


Using the application
----------------------
http://localhost:8080/JMSClient/

You can send message from the web application and the MDb will receive the message and prints it.


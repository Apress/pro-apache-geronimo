
A webservice and a standalone webservice client using Apache AXIS

Building
--------
1. ant build
This will create calculatorWS-1.0.war in the dist directory

Deploying
---------

1. Deploy the webservice
deploy.bat --user system --password  manager deploy calculatorWS-1.0.war


UnDeploying
------------
1. To undeploy Web application 

deploy.bat --user system --password  manager undeploy sample.sampleWS


Using the application
----------------------
To view the WSDL
http://localhost:8080/sampleWS/calculatorService?wsdl

Run the web service client by using this command given below:

ant runClient
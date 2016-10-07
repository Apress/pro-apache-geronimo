
A web client for the sample connector - uses outbound connections

Requires sampleConnectorClient.jar (available from the connector project)in the lib folder 

Building
--------
1. ant build
This will create sampleConnectorWebClient-1.0.war the dist directory

Deploying
---------

1. Deploy the Connectpr
deploy.bat --user system --password  manager deploy sampleConnectorWebClient-1.0.war


UnDeploying
------------
1. To undeploy Web application 

deploy.bat --user system --password  manager undeploy ConnectorClient


Using the application
----------------------
http://localhost:8080/connectorClient
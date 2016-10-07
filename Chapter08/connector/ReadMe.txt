
A sample connector that supports both inbound and outbound connections

Building
--------
1. ant build
This will create sampleRAR.rar and sampleConnectorClient.jar the dist directory

Deploying
---------

1. Deploy the Connectpr
deploy.bat --user system --password  manager deploy sampleRAR.rar


UnDeploying
------------
1. To undeploy Web application 

deploy.bat --user system --password  manager undeploy SampleResourceAdapter


Using the application
----------------------
Two client applications ( WAR and an MDB client) are provided.

An MDB client for the sample connector - uses inbound connections from the
resource adapter
and prints messages from it to the standard out.

Requires sampleConnectorClient.jar ( available from the connector project)

Building
--------
1. ant build
This will create connectorMDBClient-1.0.jar the dist directory

Deploying
---------

1. Deploy the Connectpr
deploy.bat --user system --password  manager deploy
connectorMDBClient-1.0.jar


UnDeploying
------------
1. To undeploy Web application

deploy.bat --user system --password  manager undeploy ConnectorMDBClient


Using the application
----------------------
The MDB prints messages to the standard out. These messages are generated
by the sample connector and sent to the MDB.

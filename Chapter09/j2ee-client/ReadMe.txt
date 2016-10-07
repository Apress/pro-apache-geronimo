
A J2EE application client for accessing the CMP Student entity bean (from chapter 5)

Requires the StudentCMP bean to be deployed and running in the server.

Building
--------
1. ant build
This will create studentJ2EEClient-1.0.jar in the dist folder

Deploying
---------
1. Deploy the client JAR with the server ( server should be running)
deploy.bat --user system --password manager deploy studentJ2EEClient-1.0.jar


2. To undeploy client JAR use 
deploy.bat  --user system --password manager undeploy sample/myclient/serverConfigID


Running the client JAR using the geronimo client container
----------------------------------------------------------


java -jar client.jar sample/myclient/clientConfigId
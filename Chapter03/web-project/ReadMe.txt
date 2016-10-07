

Building
--------
1. ant build
This will create welcome-1.0.jar in the folder dist.

Security  setup
---------------
Add the line metro=metro in the file GERONIMO-HOME/var/security/users.properties
Add the line it=system in the file GERONIMO-HOME/var/security/groups.properties

Deploying
---------
1. Deploy the WAR file 
deploy.bat --user system --password manager deploy welcome-1.0.war


This deploys the web application accessible at http://localhost:8080/welcome.
Login using username metro and password metro.

2. To undeploy the WAR use 
deploy.bat --user system --password manager undeploy welcome




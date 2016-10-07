

Building
--------
1. ant build
This will create userMgmtStateful-1.0.jar and userMgmtStateful-1.0.war in the dist folder

Deploying
---------
1. Deploy the Stateful Session EJB 
deploy.bat --user system --password manager deploy userMgmtStateful-1.0.jar

2. Deploy the EJB client Web application
deploy.bat --user system --password manager deploy userMgmtStateful-1.0.war

This deploys the web application accesible at http://localhost:8080/StatefulUserMgmtClient

3. To undeploy EJB use 
deploy --user system --password manager undeploy StatefulUserManagement

4. To undeploy Web application use
deploy --user system --password manager undeploy StatefulUserMgmtClient




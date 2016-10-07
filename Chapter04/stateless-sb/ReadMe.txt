Building
--------
1. ant
This will create userMgmt.jar and userMgmt-1.0.war in the dist folder

Deploying
---------
1. Deploy the Stateful Session EJB 
deploy.bat --user system --password manager deploy userMgmt-1.0.jar

2. Deploy the EJB client Web application
deploy.bat --user system --password manager deploy userMgmt-1.0.war

This deploys the web application accessible at http://localhost:8080/UserMgmtClient

3. To undeploy EJB use 
deploy --user system --password manager undeploy UserManagement

4. To undeploy Web application us
deploy --user system --password manager undeploy UserMgmtClient
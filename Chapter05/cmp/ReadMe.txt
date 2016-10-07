

Setup Database
-------------
Read section 'Setup database for executing examples' section in chapter5
and follow the steps mentioned there.

Building
--------
1. ant build
This will create studentCMP-1.0.jar and studentCMP-1.0.war in the dist folder

Deploying
---------
1. Deploy the EJB
deploy.bat --user system --password manager deploy studentCMP-1.0.jar

2. Deploy the EJB client Web application
deploy.bat --user system --password manager deploy studentCMP-1.0.war

This deploys the web application accesible at
http://localhost:8080/StudentCMPClient

3. To undeploy EJB use
deploy --user system --password manager undeploy StudentCMP

4. To undeploy Web application us
deploy --user system --password manager undeploy StudentCMPClient

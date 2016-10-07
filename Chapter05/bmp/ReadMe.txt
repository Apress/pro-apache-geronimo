
Setup Database
-------------
Read section 'Setup database for executing examples' in chapter5
and follow the steps mentioned there.

Building
--------
1. ant build
This will create studentBMP-1.0.jar and studentBMP-1.0.war in the dist
folder

Deploying
---------
1. Deploy the EJB
deploy.bat --user system --password manager deploy studentBMP-1.0.jar

2. Deploy the EJB client Web application
deploy.bat --user system --password manager deploy studentBMP-1.0.war

This deploys the web application accesible at
http://localhost:8080/StudentBMPClient

3. To undeploy EJB use
deploy --user system --password manager undeploy StudentBMP

4. To undeploy Web application use
deploy --user system --password manager undeploy StudentBMPClient




An ear application containing a web application and an EJB application.

Building
--------
1. ant build
This will create userMgmtStateful-1.0.ear, userMgmtStateful-1.0.jar and userMgmtStateful-1.0.war

Deploying
---------

1. Deploy the EAR
deploy.bat --user system --password  manager deploy userMgmtStateful-1.0.ear


UnDeploying
------------
1. To undeploy Web application 

deploy.bat --user system --password  manager undeploy MyApp


Using the application
----------------------
http://localhost:8080/webapp/
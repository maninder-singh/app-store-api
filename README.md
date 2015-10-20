# App Store Api 

Restful web service using scala and play 2.4 . Swagger api is used for creating documentation through source code.  Mysql is used for data persistence ( can be change to any database by updating configuration).

# Steps to run application

1. run export-user_registration.sql file to any dataabse.
2. open conf/application.conf file and change db.default.driver,db.default.url,db.default.user,db.default.password accordingly.
2. Open cmd/terminal
3. activator compile
2. activator run
3. Open browser and go to url : localhost:9000
4. For api documentation go to url : http://localhost:9000/webjars/api_doc/1.0/swagger-ui/dist/index.html?url=/api/v1/api-docs

Note : api-operation-list.txt file contains sample method for fetching data from web service. 

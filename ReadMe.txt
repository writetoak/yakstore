Instructions to run this assignment

On your system you need java, maven and web container.


Open command window and change to yakstore directory.

1. Firstly run command : mvn clean compile package
2. XmlParser

XmlParse is a batch file to run the application in standard java mode for story1.You can change the passing arguments in that file.

3. From yakstor/target dir copy the yak-shop.war into any web container.
   I tested yak-shop.war with the apache-tomcat-7.0.50, by putting the yak-shop.war in the webapps dir of tomcat. Run tomcat.
   
4. You can test story2 using the browser and its address bar.

5. For Post method testing, I used chrome extension Postman.


Further efforts could be made to automate the system like using maven in buit tomcat plugin or gradle so that the person running this
does not need to have maven and web container on his/her machine.

Moreover I would not say this is the complete solution. My main purpose was to show how it can be build and how we can incorporate testing
for maintenance purpose.

I have included Integration Test, Unit Test and Functional test in the application. But these are not comprehensive tests, more tests could
be added.

   
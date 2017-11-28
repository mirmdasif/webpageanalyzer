webpageanalyzer
[![Build Status](https://api.travis-ci.org/mirmdasif/webpageanalyzer.svg?branch=master)](https://travis-ci.org/mirmdasif/webpageanalyzer)
===============
A webpage analyzer using spring boot and jsoup

Tehcnology Stack
----------------
 - Java 8
 - gradle (For running test suits, installing dependencies and packaging the project)
 - Spring Boot (Easy application configuration)
 - HttpClient (For connection pooling http requests)
 - Jsoup (For Html Parsing)
 - Thymeleaf (Template Engine)
 - Bootstrap-Web-Jar (For easily writing css)

Installation
------------
 - Install Java 8
 - Intall Gradle (Optional if you want to use gradle wrapper)
 - Run ```gradle build``` or Run ```./gradlew build```(Linux) or Run  ```./gradlew.bat build```(Windows) 

Testing
--------
  - Use the below command to run automated test suite
      ````./gradlew test````
      
Running
--------
  - From the project directory run the following command to run the project locally.
     ```./gradlew bootRun``` 
     
Test Url
--------
 http://localhost:8080
 
Assumptions
-----------
 - User will enter one fully qualified url at a time.
 - hpermedia links are are those which satsify css query a\[href\]
 - When calculating internal links subdomains were not considered. That means blog.github.com and api.github.com both are internal to www.github.com  
 - Links are unreachable if http(s) status code is different then 2XX or 3XX 
 
Design Decision
----------------
 - Login Page
   - Login page must have one password field
   - Login page must have one text or email field
   
Enhancement
-----------
  - Used connection pooling feature of HTTP Template, and Parallal Stream for testing the links availabily.
  
Constraints
------------
  - Since synchronus checking is done it took a lot of time to analyze if the page contains a lot of links.

Thoughts on Efficient Implementation
------------------------------------
  - Assync checking will be better to analyze the html. If we can design a publisher subscribe architecture based on jms and a message broker then the links could be analyzed more efficiently.

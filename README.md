[![Build Status](https://travis-ci.org/Brest-Java-Course-2019/Boyko-Pavel.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2019/Boyko-Pavel)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2019/Boyko-Pavel/badge.svg?branch=master)](https://coveralls.io/github/Brest-Java-Course-2019/Boyko-Pavel?branch=master)


# Project Title

Test Java project to study technologies such as git, maven, continuous integration, continuous delivery and others.

## Getting Started

### Prerequisites

install jdk8+ 
install git 
install maven3+

### Check environment configuration
```
 sudo update-java-alternatives --list

 export JAVA_HOME = ...
```
### Installing

Choose directory for project, download project from github:

```
git clone https://github.com/Brest-Java-Course-2019/DeliveryCost.git  

```

## Build project
Run terminal command in project directory:

```
mvn clean install
```
### Preparing reports
```
  mvn site
  mvn site:site
```
  check reports: 
  
  ../<project>/target/site/index.html

## Run WEB RESTful application  
Run terminal command in project directory:

```
mvn -pl web-app/ tomcat7:run
  
mvn -pl rest-app/ tomcat7:run
``` 
the web application should be available at:
        
http://localhost:8080/

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## License

This project developed by Boyko Pavel <br> Brest Java Courses  2019



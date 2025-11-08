FROM openjdk:18-jdk-alpine
MAINTAINER Mahesh

# Default value is "development", could be changed by passing other env 
ENV MY_ENV=development 

COPY tradechamp-0.0.1-SNAPSHOT.jar tradechamp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/tradechamp-0.0.1-SNAPSHOT.jar"]


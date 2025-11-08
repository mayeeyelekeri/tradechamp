FROM openjdk:18-jdk-alpine
MAINTAINER Mahesh

# Default value is "development", could be changed by passing other env 
ENV MY_ENV=development 

COPY tradechamp-1.0.0.jar tradechamp-1.0.0.jar

ENTRYPOINT ["java","-jar","/tradechamp-1.0.0.jar"]


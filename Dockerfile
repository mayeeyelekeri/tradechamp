# Using AWS public image repository which seems better
FROM public.ecr.aws/amazoncorretto/amazoncorretto:24-headful

#### FROM openjdk:18-jdk-alpine
MAINTAINER MM

# Variables, which would be used here 
ARG JAR="tradechamp-1.0.0.jar"

# Default value is "development", could be changed by passing other env 
ENV MY_ENV=development 

CMD echo msg is $JAR
COPY target/tradechamp-1.0.0.jar tradechamp-1.0.0.jar 
#COPY "target/${JAR}"  $JAR 

ENV WELCOME_MESSAGE="message from dockerfile on April 12th 2025" 

ENTRYPOINT ["java","-jar","tradechamp-1.0.0.jar"]
#ENTRYPOINT ["java","-jar","/$JAR"]


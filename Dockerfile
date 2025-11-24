# Using AWS public image repository which seems better
FROM public.ecr.aws/amazoncorretto/amazoncorretto:24-headful

#### FROM openjdk:18-jdk-alpine

# Variables, which would be used here 
ARG JAR="tradechamp-1.0.0.jar"

# Default value is "development", could be changed by passing other env 
ENV MY_ENV=development 
ENV ACTIVE_PROFILE=default

CMD echo msg is $JAR
COPY target/tradechamp-1.0.0.jar tradechamp-1.0.0.jar 
#COPY "target/${JAR}"  $JAR 

ENV WELCOME_MESSAGE="message from dockerfile on April 12th 2025" 

ENTRYPOINT ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-jar","tradechamp-1.0.0.jar"]
#ENTRYPOINT ["java","-jar","/$JAR"]


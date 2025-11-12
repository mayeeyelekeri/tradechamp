 mvn clean package
 docker build -t tradechamp .
 docker tag tradechamp 371483153301.dkr.ecr.us-east-1.amazonaws.com/tradechamp:latest
 docker push 371483153301.dkr.ecr.us-east-1.amazonaws.com/tradechamp:latest

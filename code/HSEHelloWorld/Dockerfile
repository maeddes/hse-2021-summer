FROM ubuntu:20.10
RUN apt update && apt install openjdk-11-jre-headless -y
COPY target/HSEHelloWorld-0.0.1-SNAPSHOT.jar /opt/app.jar
CMD ["java","-jar","/opt/app.jar"]
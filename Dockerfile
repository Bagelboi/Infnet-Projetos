FROM openjdk:17-jdk-slim

WORKDIR /devops-tp2

COPY target/devops_tp1_client-0.0.1-SNAPSHOT.jar devops-tp2.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "devops-tp2.jar"]

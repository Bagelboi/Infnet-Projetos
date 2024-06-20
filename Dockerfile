FROM openjdk:17-jdk-slim

COPY target/servicoat-0.0.1-SNAPSHOT.jar servicoat-dock-v1.jar

EXPOSE 6379
EXPOSE 8080
EXPOSE 27017

ENTRYPOINT ["java", "-jar", "servicoat-dock-v1.jar"]
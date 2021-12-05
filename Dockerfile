FROM openjdk:11-jdk-oracle
ADD target/vesseltracker-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar vesseltracker-0.0.1-SNAPSHOT.jar
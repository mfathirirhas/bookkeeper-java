FROM openjdk:11-jdk

VOLUME /tmp

COPY journal/target/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar",""]
FROM openjdk:11-jdk

VOLUME /tmp

COPY ledger/target/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar",""]
FROM openjdk:8
COPY target/luizalabs-server-rest-0.0.2-SNAPSHOT.jar /app.jar
VOLUME /tmp
#EXPOSE 3000/tcp
ENTRYPOINT ["java", "-jar", "/app.jar"]
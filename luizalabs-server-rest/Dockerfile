FROM openjdk:8
COPY target/luizalabs-server-rest-0.0.4.jar /app.jar
VOLUME /tmp
#EXPOSE 3000/tcp
ENTRYPOINT ["java", "-jar", "/app.jar"]
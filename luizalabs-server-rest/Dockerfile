FROM openjdk:8
COPY target/luizalabs-server-rest-0.0.3-RELEASE.jar /app.jar
VOLUME /tmp
#EXPOSE 3000/tcp
ENTRYPOINT ["java", "-jar", "/app.jar"]
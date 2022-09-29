FROM java:8

EXPOSE 9090


ADD notes_web.jar app.jar
RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java", "-jar", "/app.jar"]
FROM oracle/graalvm-ce:1.0.0-rc14
VOLUME /tmp
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

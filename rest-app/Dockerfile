FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD rest-app-1.0-SNAPSHOT.jar rest-app.jar
RUN sh -c 'touch /rest-app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /rest-app.jar" ]
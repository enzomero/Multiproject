FROM java:8
ADD /target/rest-app-1.0-SNAPSHOT.war rest-app.war
RUN sh -c 'touch /rest-app.war'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /rest-app.war" ]

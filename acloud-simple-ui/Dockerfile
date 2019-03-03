FROM java:8-jre-alpine
#USER root

#ADD  ./ /tmp/build/
#RUN mkdir /app
WORKDIR /app

COPY acloud-book-ui.jar /app

VOLUME /tmp
EXPOSE 8701
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-book-ui.jar" ]
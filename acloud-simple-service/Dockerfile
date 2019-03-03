FROM java:8-jre-alpine
MAINTAINER masen <masen.miao@gmail.com>

RUN mkdir /app
WORKDIR /app

COPY acloud-book-service.jar /app
#VOLUME /app
EXPOSE 8601

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-book-service.jar" ]
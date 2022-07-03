FROM  openjdk:11-jre-buster as runner

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get -qq update

WORKDIR /root/
USER root

EXPOSE 8089

COPY hr-worker-feing-gatway-0.0.1-SNAPSHOT.jar /root/hr-worker-feing-gatway-0.0.1-SNAPSHOT.jar
CMD java -jar /root/hr-worker-feing-gatway-0.0.1-SNAPSHOT.jar

FROM  openjdk:11-jre-buster as runner

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get -qq update

WORKDIR /root/
USER root

EXPOSE 8081

COPY hr-user-0.0.1-SNAPSHOT.jar /root/hr-user-0.0.1-SNAPSHOT.jar
CMD java -jar /root/hr-user-0.0.1-SNAPSHOT.jar

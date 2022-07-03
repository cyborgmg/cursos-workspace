FROM  openjdk:11-jre-buster as runner

ENV DEBIAN_FRONTEND=noninteractive

ENV GITHUB_USER=cyborgmg@gmail.com
ENV GITHUB_PASS=049m3537s

RUN apt-get -qq update

WORKDIR /root/
USER root

EXPOSE 8888

COPY hr-config-server-0.0.1-SNAPSHOT.jar /root/hr-config-server-0.0.1-SNAPSHOT.jar
CMD java -jar /root/hr-config-server-0.0.1-SNAPSHOT.jar

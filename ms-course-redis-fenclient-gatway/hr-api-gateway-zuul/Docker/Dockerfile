FROM  openjdk:11-jre-buster as runner

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get -qq update

WORKDIR /root/
USER root

EXPOSE $PORT

COPY hr-api-gateway-zuul-0.0.1.jar /root/hr-api-gateway-zuul-0.0.1.jar
CMD java -jar /root/hr-api-gateway-zuul-0.0.1.jar

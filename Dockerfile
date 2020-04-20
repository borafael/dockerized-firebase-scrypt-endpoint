FROM alpine:3.9 AS build-scrypt

RUN apk add --no-cache \
    git \
    gcc \
    g++ \
    openssl-dev \
    make \
    automake \
    autoconf \
    maven

RUN git clone https://github.com/firebase/scrypt

RUN cd scrypt && \
    autoreconf -i && \
    ./configure --disable-dependency-tracking && \
    make
    
# -------------------------------------------------- #
    
FROM openjdk:8-jdk-alpine AS build-app

RUN apk add --no-cache \
	git \
	maven

RUN git clone https://github.com/borafael/firebase-scrypt-web-wrapper

RUN cd firebase-scrypt-web-wrapper && mvn package

# -------------------------------------------------- #

FROM openjdk:8-jdk-alpine

RUN apk add --no-cache bash

COPY --from=build-scrypt scrypt/scrypt /usr/local/bin/scrypt

COPY --from=build-app firebase-scrypt-web-wrapper/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

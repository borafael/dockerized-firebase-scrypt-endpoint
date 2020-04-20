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
    
FROM openjdk:8-jre-alpine

RUN apk add --no-cache \
	git \
	maven

RUN git clone https://github.com/borafael/firebase-scrypt-web-wrapper

RUN cd firebase-scrypt-web-wrapper && ls

# -------------------------------------------------- #

FROM openjdk:8-jdk-alpine

RUN apk add --no-cache git

COPY --from=build-app firebase-scrypt-web-wrapper/target/*.jar /opt/app

COPY --from=build-scrypt scrypt/scrypt /usr/local/bin/scrypt

ENTRYPOINT ["java","-jar","app.jar"]






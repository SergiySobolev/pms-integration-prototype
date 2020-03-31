ARG VERSION=8u151
ARG SERVER__PORT=1000
ARG PMSINT_ENV=dev

FROM openjdk:${VERSION}-jdk as BUILD

ENV server__port=${SERVER__PORT}
ENV pmsint_env=${PMSINT_ENV}

RUN mkdir app

ADD build/libs/pms-integration-prototype-all.jar app/pms-integration-prototype-all.jar

WORKDIR .

EXPOSE ${SERVER_PORT}

CMD ["java", "-jar", "app/pms-integration-prototype-all.jar"]


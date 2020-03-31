ARG VERSION=8u151
FROM openjdk:${VERSION}-jdk as BUILD

ARG SERVER__PORT=10000
ARG PMSINT_ENV=dev

ENV server__port=${SERVER__PORT}
ENV pmsint_env=${PMSINT_ENV}

RUN mkdir app

ADD build/libs/pms-integration-prototype-all.jar app/pms-integration-prototype-all.jar

WORKDIR .

EXPOSE ${SERVER__PORT}

CMD ["java", "-jar", "app/pms-integration-prototype-all.jar"]


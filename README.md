<a href="https://github.com/crazy-max/docker-jetbrains-license-server/actions?workflow=build"><img src="https://github.com/crazy-max/docker-jetbrains-license-server/workflows/build/badge.svg" alt="Build Status"></a>
<a href="https://app.codacy.com/manual/SergiySobolev/pms-integration-prototype/dashboard"><img src="https://img.shields.io/codacy/grade/eb420bc3e6ed49ff97cc261602228efa.svg?style=flat-square" alt="Code Quality"></a>

Prototype of integration with different HealthCare Practice management systems.

**Backend.**

To build project execute _./gradlew clean_ build from project root

To run application: 
_java -jar "${SYSTEM_PROPERTY_1}" "${SYSTEM_PROPERTY_2}" build/libs/pms-integration-prototype-all.jar_


Example: _java -jar "-Denv=gcp" "-Dconfig.override.server.port=7000" build/libs/pms-integration-prototype-all.jar_ 



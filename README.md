Prototype of integration with different HealthCare Practice management systems.

**Backend.**

To build project execute _./gradlew clean_ build from project root

To run application: 
_java -jar "${SYSTEM_PROPERTY_1}" "${SYSTEM_PROPERTY_2}" build/libs/pms-integration-prototype-all.jar_


Example: _java -jar "-Denv=gcp" "-Dconfig.override.server.port=7000" build/libs/pms-integration-prototype-all.jar_ 



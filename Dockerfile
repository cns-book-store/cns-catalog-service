FROM eclipse-temurin:17
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} cns-catalog-service.jar
ENTRYPOINT ["java", "-jar", "cns-catalog-service.jar"]

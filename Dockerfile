FROM eclipse-temurin:17
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service-02.jar
ENTRYPOINT ["java", "-jar", "catalog-service-02.jar"]
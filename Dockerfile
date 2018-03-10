FROM openjdk:8-jre-alpine
COPY ./build/libs/SpringMvcJpaSample-0.0.1-SNAPSHOT.jar ./build/libs/
WORKDIR ./build/libs
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE local
CMD ["java", "-jar", "SpringMvcJpaSample-0.0.1-SNAPSHOT.jar"]


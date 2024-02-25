FROM eclipse-temurin:11-jdk
VOLUME /tmp
COPY /target/*.jar app.jar
ENTRYPOINT ["java","-jar", "-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC","/app.jar"]
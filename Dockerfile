# cache all the dependencies
FROM gradle:8.4-jdk17 AS cache
WORKDIR /app
COPY settings.gradle.kts build.gradle.kts ./

RUN gradle dependencies --no-daemon

# once the dependencies are cached, build the application
FROM gradle:8.4-jdk17 AS builder

COPY --from=cache /app /app
COPY --from=cache /home/gradle/.gradle /home/gradle/.gradle
WORKDIR /app
COPY . .

RUN gradle build --no-daemon -x test

# extract the layers from the jar and create a new image
FROM azul/zulu-openjdk-alpine:17 AS extractor

COPY --from=builder /app/build/libs/*.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract

# create the final image using the extracted layers
FROM azul/zulu-openjdk-alpine:17

WORKDIR /app

COPY --from=extractor dependencies/ ./
COPY --from=extractor snapshot-dependencies/ ./
COPY --from=extractor spring-boot-loader/ ./
COPY --from=extractor application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "org.springframework.boot.loader.JarLauncher"]

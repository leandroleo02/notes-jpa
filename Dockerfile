ARG BUILD_CACHE_PATH

# cache all the dependencies
FROM gradle:8.4-jdk17 AS cache

ARG BUILD_CACHE_PATH
ENV GRADLE_USER_HOME=${BUILD_CACHE_PATH}

WORKDIR /app
COPY settings.gradle.kts build.gradle.kts ./

RUN gradle dependencies --no-daemon --info

# once the dependencies are cached, build the application
FROM gradle:8.4-jdk17 AS builder

ARG BUILD_CACHE_PATH
ENV GRADLE_USER_HOME=${BUILD_CACHE_PATH}

COPY --from=cache /app /app
WORKDIR /app
COPY . .

RUN gradle build --no-daemon --info -x test

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

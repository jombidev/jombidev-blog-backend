FROM amazoncorretto:17 AS base

FROM base AS build

COPY . .
RUN ./gradlew build

FROM base

COPY --from=build build/libs/jombidev-blog-server-0.0.1-SNAPSHOT.jar blog.jar

ENTRYPOINT ["java", "-jar", "blog.jar"]

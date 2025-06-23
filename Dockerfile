FROM amazoncorretto:21

COPY build/libs/jombidev-blog-server-0.0.1-SNAPSHOT.jar blog.jar

ENTRYPOINT ["java", "-jar", "blog.jar"]

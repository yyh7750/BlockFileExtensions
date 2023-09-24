# openjdk:11 을 설치(빌더버전으로)
FROM openjdk:11 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY ./src src
RUN chmod =x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:11
COPY --from=builder build/libs/BlockFileExtentions-0.0.1-SNAPSHOT.jar BlockFileExtentions.jar

EXPOSE 8080

CMD ["sh", "-c", "java ${JAVA_OPTS} -jar /BlockFileExtentions.jar"]

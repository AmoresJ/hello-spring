FROM openjdk:11 as base
WORKDIR /opt/hello-spring
COPY ./ ./
RUN ./gradlew assemble

FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
WORKDIR /opt/hello-spring
COPY --from=base /opt/hello-spring/build/libs/hello-spring-0.0.1-SNAPSHOT.jar ./
CMD java -jar hello-spring-0.0.1-SNAPSHOT.jar
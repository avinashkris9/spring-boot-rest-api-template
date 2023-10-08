FROM maven:3.2-jdk-8 as build
# Some version information
LABEL description="Sample Java Rest API Project" \
      name="spring-boot-api-template"
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install

FROM openjdk:8-jre-alpine
ARG NAME="UNAVAILBLE"
LABEL description="Java Project" \
      name=$NAME

# ARG version
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN mkdir /opt/app  && chown appuser /opt/app
USER appuser
ARG JAVA_APP_JAR="*.jar"
EXPOSE 8080
WORKDIR /opt/app/
ENV APP_NAME=$NAME
COPY  --chown=appuser:appgroup ./scripts/run-java.sh .
COPY --chown=appuser:appgroup --from=build /usr/src/app/target/${JAVA_APP_JAR} ${JAVA_APP_JAR}
RUN chmod 755 ${JAVA_APP_JAR}
ENTRYPOINT [ "/opt/app/run-java.sh"]
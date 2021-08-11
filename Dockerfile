FROM adoptopenjdk/openjdk8

WORKDIR /app

COPY target/drools-0.0.1-SNAPSHOT.jar /app/drools.jar

ENTRYPOINT [ "java", "-jar", "drools.jar" ]
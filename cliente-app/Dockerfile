FROM openjdk:17-jdk-slim

ARG NAME_APP="spark-client"

# copiar archivo jar y librerías
COPY ./build/install/$NAME_APP/lib/$NAME_APP-1.0-SNAPSHOT.jar ./
COPY ./build/install/$NAME_APP/lib ./lib

CMD ["java", "-cp", "$NAME_APP-1.0-SNAPSHOT.jar:./lib/*", "Main"]

EXPOSE 4567

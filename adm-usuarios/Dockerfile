FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN apk upgrade

COPY ./target/*.jar .

CMD ["java", "-jar", "adm-usuarios.jar"]

EXPOSE 8080

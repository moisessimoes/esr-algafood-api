FROM openjdk:17-jdk-slim
#eclipse-temurin:17-jre-alpine

WORKDIR /app

ARG JAR_FILE 

COPY target/${JAR_FILE} /app/algafood-api.jar

#24.14. Controlando a ordem de inicialização com wait-for-it.sh
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh 

EXPOSE 8080

CMD ["java", "-jar", "algafood-api.jar"]
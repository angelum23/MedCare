# Dockerfile
FROM openjdk:17

# Copiar o JAR da aplicação
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Copiar o script de inicialização
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
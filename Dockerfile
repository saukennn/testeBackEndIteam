# Baixando imagem Maven para construir a aplicação
FROM maven:3.8.3-openjdk-17 AS builder

# Criando diretório de trabalho
WORKDIR /build

# Copiando o conteúdo da aplicação para dentro do container
COPY . .

# Rodando o build da aplicação (gera o arquivo JAR)
RUN mvn clean package -DskipTests

# Baixar imagem OpenJDK para rodar a aplicação
FROM openjdk:17.0.1-jdk-slim

# Criando diretório para o JAR
WORKDIR /app

# Copiando o JAR gerado pelo Maven da fase de build para a nova imagem
COPY --from=builder /build/target/*.jar /app/produtos-api.jar

# Definir comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "/app/produtos-api.jar"]

# Expor as portas que serão usadas pela aplicação
EXPOSE 8080 8082
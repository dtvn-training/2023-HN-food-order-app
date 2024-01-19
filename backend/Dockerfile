FROM openjdk:17-oracle
RUN mkdir -p food_order/backend
WORKDIR /food_order/backend

EXPOSE 8080
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY src ./src

CMD ["./mvnw","spring-boot:run"]

LABEL authors="NMCuong"
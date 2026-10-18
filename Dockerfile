FROM eclipse-temurin:25-jre-alpine

WORKDIR /inventory

ARG JAR_FILES=target/*.jar
COPY ${JAR_FILES} ./inventory.jar

ENV PG_HOST=172.17.0.3
ENV PG_PORT=5432
ENV PG_DB=inventorydb
ENV PG_USER=postgres
ENV PG_PASS=pg@1234
ENV REDIS_PORT=6379
ENV RABBIT_HOST=172.17.0.4
ENV RABBIT_PORT=5672
ENV RABBIT_Q="order-queue"
ENV RABBIT_EXCHANGE="order-exchange"
ENV RABBIT_ROUTE_KEY="ordering"
ENV RABBIT_USER=guest
ENV RABBIT_PASS=guest

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "inventory.jar"]
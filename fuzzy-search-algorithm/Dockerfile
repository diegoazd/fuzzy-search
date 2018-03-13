FROM java:8-alpine
EXPOSE 8080


WORKDIR /app
ADD build/libs/fuzzy-search-0.1.0.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]

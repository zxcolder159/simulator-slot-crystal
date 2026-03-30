# Используем легковесный образ Java 21
FROM eclipse-temurin:21-jdk-alpine

# Указываем рабочую папку внутри контейнера
WORKDIR /app

# Копируем скомпилированный jar-файл в контейнер
# (Сначала нужно запустить ./gradlew bootJar)
COPY build/libs/*.jar app.jar

# Открываем порт 8080
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
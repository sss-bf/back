# 베이스 이미지 설정 (Java 21)
FROM eclipse-temurin:21-jdk

# 작업 디렉토리 생성
WORKDIR /app

# JAR 파일을 컨테이너에 복사
COPY build/libs/*.jar app.jar

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
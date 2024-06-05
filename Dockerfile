# OpenJDK 기반 이미지를 사용
FROM openjdk:17-jdk-slim

# JAR 파일을 지정
ARG JAR_FILE=build/libs/*.jar

# 컨테이너 내에서 애플리케이션 디렉토리 생성
WORKDIR /app

# 로컬 JAR 파일을 컨테이너의 /app 디렉토리로 복사
COPY ${JAR_FILE} app.jar

# 애플리케이션 포트 설정 (예: 8080)
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
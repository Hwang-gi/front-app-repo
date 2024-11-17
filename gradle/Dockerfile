# 1. Build Stage: Gradle 빌드 이미지
FROM gradle:8.0-jdk17-alpine AS build

# 작업 디렉토리 설정
WORKDIR /app

# 루트 프로젝트의 build.gradle과 settings.gradle 파일을 복사하여 Gradle 캐시 활용
COPY settings.gradle settings.gradle
COPY build.gradle build.gradle

# 서브프로젝트들의 소스 코드 복사
COPY modules/app/ modules/app/
COPY modules/api/ modules/api/
COPY modules/web/ modules/web/

# application.properties 파일 복사
COPY modules/web/src/main/resources/application.properties modules/web/src/main/resources/application.properties

# HomeController.java 복사 (필요한 경우 컨트롤러 파일 경로가 다를 수 있음)
COPY modules/web/src/main/java/com/example/web/HomeController.java modules/web/src/main/java/com/example/web/HomeController.java

# Gradle 의존성 다운로드 및 빌드
RUN gradle --no-daemon build -x test

# 2. Run Stage: 경량화된 실행 이미지를 위한 JRE 기반 이미지
FROM openjdk:17-jre-slim

# 최종 빌드된 JAR 파일을 복사
COPY --from=build /app/modules/web/build/libs/web.jar /app/web.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/web.jar"]

# 노출할 포트 설정
EXPOSE 8080

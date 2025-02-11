# 1. 베이스 이미지 선택: OpenJDK 17이 포함된 가벼운 이미지 (예: Alpine 기반)
FROM openjdk:17-jdk-alpine

# 2. 작업 디렉터리 설정 (컨테이너 내부에서 사용할 디렉터리)
WORKDIR /app

# 3. 빌드된 JAR 파일을 컨테이너로 복사
# 'target/demo-0.0.1-SNAPSHOT.jar'는 Maven 빌드 후 생성된 JAR 파일의 경로입니다.
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 4. 컨테이너 시작 시 실행할 명령어를 설정
ENTRYPOINT ["java", "-jar", "app.jar"]
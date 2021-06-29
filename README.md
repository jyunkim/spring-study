# Spring Study
## 프로젝트 생성
start.spring.io 
### Project
빌드: 소스코드를 실행할 수 있는 가공물로 변환하는 과정   
여기서는 자바 소스코드를 JVM이나 tomcat같은 WAS가
인식할 수 있는 구조로 패키징하는 과정을 의미

빌드 도구: 프로젝트 생성, 테스트 빌드, 배포 등의
프로젝트 라이프사이클 및 라이브러리 dependency 관리
- Maven   
  XML 기반   
  필요한 라이브러리를 pom.xml에 작성   
  중앙 저장소를 통한 자동 의존성 관리
- Gradle   
  Groovy 기반(자바 문법과 유사)   
  Gradle Wrapper를 이용하여 gradle이 설치되지 않은
  시스템에서도 빌드 가능   
  Maven의 중앙 저장소도 지원   
  빌드 스크립트인 build.gradle에 빌드 관련 설정 작성   
  의존 관계가 있는 라이브러리 모두 다운

### Spring Boot
SNAPSHOT - 아직 만들고 있는 버전   
M1 - 정식 릴리즈 되지 않은 버전

### Dependencies
사용할 라이브러리

Spring Web: 스프링 웹 MVC, Tomcat
Thymeleaf: HTML 템플릿 엔진

### Web Server
웹 서버 소프트웨어와 웹 사이트 구성 요소 파일을 저장하는
서버   
소프트웨어 관점에서는 HTTP 서버를 의미하며, 
URL(웹 주소)과 HTTP(프로토콜 주소)를 이해   
브라우저에서 HTTP를 통해 파일을 웹 서버에 요청하면 
HTTP 서버가 요청을 수락하고 해당 파일을 찾은 뒤 HTTP를 
통해 응답을 보냄   
정적인 컨텐츠 제공   
ex) Apache server, Nginx(80 포트)
  
### WAS(Web Application Server)
WAS = Web Server + Web Container   
DB 조회와 같은 동적인 컨텐츠를 제공하는 미들웨어 엔진   
JSP와 servlet을 구동하기 위한 서블릿 컨테이너 역할을 수행   
\* Servlet: WAS 상에서 클라이언트의 요청을 처리하는 자바 
인터페이스   
ex) Tomcat, JBoss(8080 포트)

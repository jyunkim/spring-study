# Spring Study
## 프로젝트 생성
https://start.spring.io 
### Project
빌드: 소스코드를 실행할 수 있는 가공물로 변환하는 과정   
> 여기서는 자바 소스코드를 JVM이나 tomcat같은 WAS가
인식할 수 있는 구조로 패키징하는 과정을 의미

빌드 도구: 프로젝트 생성, 테스트 빌드, 배포 등의
프로젝트 라이프사이클 및 라이브러리 dependency 관리
- **Maven**   
  XML 기반   
  필요한 라이브러리를 pom.xml에 작성   
  중앙 저장소를 통한 자동 의존성 관리
- **Gradle**   
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
ex) Tomcat, JBoss(8080 포트)

\* JSP: HTML안에 자바 코드를 삽입하여 동적인 페이지를 생성하는 언어   
\* Servlet: 자바 소스코드안에 HTML 코드가 들어가는 형태의 자바 
인터페이스

## 빌드
### 빌드 명령어
1. ./gradlew build
2. cd build/libs
3. java -jar demo-0.0.1-SNAPSHOT.jar

### 빌드 파일 삭제   
./gradlew clean

## 웹 개발 방식
### 정적 컨텐츠
Controller 없이 정적인 html 파일 전송

### MVC와 템플릿 엔진
Model(데이터, 비즈니스 로직), View(레이아웃, 화면), Controller(라우팅) 분리   
템플릿 엔진을 이용하여 controller에서 view로 데이터 전달   
HTML파일 변환 후 브라우저에 넘겨줌   
viewResolver가 view를 찾고 템플릿 엔진 연결

### API
View 없이 데이터를 그대로 전송   
@ResponseBody 어노테이션 사용 -> 
viewResolver 대신 HTTPMessageConverter가 동작   
Jackson 라이브러리를 통해 객체를 JSON으로 변환

## 웹 어플리케이션 계층 구조
![structure](https://user-images.githubusercontent.com/68456385/123925792-36bc6380-d9c6-11eb-8e90-908afbe0c97f.png)
- 컨트롤러: 웹 MVC의 controller 역할
- 서비스: 핵심 비즈니스 로직 구현
- 리포지토리: DB에 접근, 도메인 객체를 DB에 저장하고 관리
- 도메인: 비즈니스 도메인 객체 ex) 회원, 주문..
  
아직 사용할 DB가 결정되지 않았을 때에는,
우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계

## 테스트
### main 메서드, controller
준비하고 실행하는데 오래 걸리고, 반복 실행이 어려우며, 여러 테스트를 한번에 실행하기 어려움

### JUnit 프레임워크
테스트 상세 내용 확인 가능   
한 번에 여러 테스트 가능   
테스트 순서 의존관계가 생길 수 있음

**@AfterEach**   
한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있는데, 이렇게
되면 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있음   
@AfterEach를 사용하면 각 테스트가 종료될 때마다 메서드가 실행되는데, 
이를 통해 clear 메서드를 구현하여 메모리 DB에 저장된 데이터를 삭제할 수 있음

### TDD(Test Driven Development)
**테스트 주도 개발**   
테스트 코드를 먼저 작성한 후, 이에 맞춰 기능 구현

### DI(Dependecy Injection)
**의존성 주입**   
하나의 객체가 다른 객체의 의존성을 제공   
필요한 객체를 직접 생성하는 것이 아닌 외부로부터 객체를 받아서 사용   
-> 객체간의 결합도를 줄이고 코드의 유연성, 재활용성을 높여줌

필드 주입, setter 주입, 생성자 주입이 있는데, 의존관계가 실행 중에 동적으로
변하는 경우는 거의 없으므로 생성자 주입 권장

```java
public class MemberService {

  private MemberRepository memberRepository;

  // 의존성 주입
  // 객체를 직접 생성하지 않고 외부에서 주입
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
    
    ...
```

## 스프링 빈과 의존관계
### 의존관계
컨트롤러는 서비스, 서비스는 레포지토리를 의존함      
@Autowired - 스프링이 의존성 주입

### 스프링 빈 등록
1. 컴포넌트 스캔   
   @Component 어노테이션이 있으면 스프링 빈으로 자동 등록됨   
   -> 스프링이 실행될 때 스프링 컨테이너에 해당 객체가 스프링 빈으로 생성되어 관리됨   
   여기에 @Controller, @Service, @Repository 어노테이션이 포함   
   스프링 컨테이너에 등록된 스프링 빈은 모두 싱글톤(하나의 인스턴스)으로 등록됨   
   Application 클래스가 포함된 패키지 내부의 클래스만 등록 가능
   

2. 자바 코드   
   SpringConfig 파일을 통해 등록   
   정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 때는 이 방식 사용(ex. MemoryMemberRepository)   
   -> 컴포넌트 스캔을 사용하면 여러 코드를 변경해야함

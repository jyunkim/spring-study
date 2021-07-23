# Spring Study
스프링을 왜 쓰는가?

1. **객체지향적 설계**   
   다형성을 활용하여 수정해야되는 코드를 줄일 수 있음   
   -> 인터페이스를 만들고 구현체만 바꿔끼움   
   개방 폐쇄 원칙(OCP, Open-Closed Principle)을 지킬 수 있음   
   "확장에는 열려있고, 수정에는 닫혀있다."
   

2. **의존성 주입**   
   객체지향적 설계를 위해선 의존성 주입이 필요   
   스프링에서 간편하게 의존성 주입 지원   
   기존 코드를 수정하지 않고 설정만 바꾸면 구현 클래스를 바꿀 수 있음

## 프로젝트 생성
https://start.spring.io 
### Project
빌드: 소스코드를 실행할 수 있는 가공물로 변환하는 과정   
> 여기서는 자바 소스코드를 JVM이나 Tomcat같은 WAS가
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
- SNAPSHOT - 아직 만들고 있는 버전   
- M1 - 정식 릴리즈 되지 않은 버전

### Dependencies
사용할 라이브러리
- Spring Web: 스프링 웹 MVC, Tomcat   
- Thymeleaf: HTML 템플릿 엔진

### Web Server
- 웹 서버 소프트웨어와 웹 사이트 구성 요소 파일을 저장하는
서버   
- 소프트웨어 관점에서는 HTTP 서버를 의미하며, 
URL(웹 주소)과 HTTP(프로토콜 주소)를 이해   
- 브라우저에서 HTTP를 통해 파일을 웹 서버에 요청하면, 
HTTP 서버가 요청을 수락하고 해당 파일을 찾은 뒤 HTTP를 
통해 응답을 보냄   
- 정적인 컨텐츠 제공   
ex) Apache server, Nginx(80 포트)
  
### WAS(Web Application Server)
WAS = Web Server + Web Container   
- DB 조회와 같은 동적인 컨텐츠를 제공하는 미들웨어 엔진   
- JSP와 Servlet을 구동하기 위한 서블릿 컨테이너 역할을 수행   
ex) Tomcat, JBoss(8080 포트)
 
\* Servlet: 자바 소스코드안에 HTML 코드가 들어가는 형태의 자바
인터페이스   
\* JSP: HTML안에 자바 코드를 삽입하여 동적인 페이지를 생성하는 언어,
서블릿의 단점을 보완한 서블릿 기반의 스크립트 기술

### MVC 패턴의 JSP, Servlet
- JSP와 Servlet을 동시에 사용하는 MVC모델   
- View는 JSP, controller는 Servlet을 사용

## 빌드
### 빌드 명령어
```shell
./gradlew build
cd build/libs
java -jar demo-0.0.1-SNAPSHOT.jar
```

### 빌드 파일 삭제
```shell
./gradlew clean
```

## 웹 개발 방식
### 정적 컨텐츠
스프링 컨테이너에서 일치하는 controller를 먼저 찾은 후,
controller가 없으면 정적인 HTML 파일 전송

### MVC와 템플릿 엔진
- Model(데이터, 비즈니스 로직), View(레이아웃, 화면), Controller(라우팅) 분리   
- 템플릿 엔진을 이용하여 controller에서 view로 데이터 전달
- viewResolver가 view를 찾고 템플릿 엔진 연결
- HTML파일 변환 후 브라우저에 넘겨줌   

### API
- View 없이 데이터를 그대로 전송   
- @ResponseBody 어노테이션 사용 -> 
viewResolver 대신 HTTPMessageConverter가 동작   
- Jackson 라이브러리를 통해 객체를 JSON으로 변환

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
- 테스트 상세 내용 확인 가능   
- 한 번에 여러 테스트 가능   
- 테스트 순서 의존관계가 생길 수 있음

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
}
```

## 스프링 빈과 의존관계
### 의존관계
컨트롤러는 서비스, 서비스는 레포지토리를 의존함

@Autowired - 스프링이 의존성 주입

### 스프링 빈 등록
1. **컴포넌트 스캔**   
   @Component 어노테이션이 있으면 스프링 빈으로 자동 등록됨   
   -> 스프링이 실행될 때 스프링 컨테이너에 해당 객체가 스프링 빈으로 생성되어 관리됨   
   여기에 @Controller, @Service, @Repository 어노테이션이 포함   
   스프링 컨테이너에 등록된 스프링 빈은 모두 싱글톤(하나의 인스턴스)으로 등록됨   
   Application 클래스가 포함된 패키지 내부의 클래스만 등록 가능
   

2. **자바 코드**   
   SpringConfig 파일을 통해 등록   
   정형화되지 않거나(ex. AOP), 상황에 따라 구현 클래스를 변경해야 할 때는 이 방식 사용(ex. MemoryMemberRepository)   
   -> 컴포넌트 스캔을 사용하면 여러 코드를 변경해야함   
   -> 다형성을 활용하여 객체지향적 설계를 할 수 있음

## DB 접근 기술
build.gradle 파일에 관련 라이브러리 추가   
resources/application.properties 파일에 데이터베이스 연결 설정 추가

### H2
개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공

### JDBC(Java Database Connectivity)
- 자바에서 데이터베이스에 접속할 수 있도록 하는 자바 API   
- 데이터베이스에서 자료를 쿼리하거나 업데이트하는 방법을 제공

### 통합 테스트
- 스프링 컨테이너와 DB까지 연결한 통합 테스트

**단위 테스트**   
기존의 순수 자바 코드의 테스트   
실행 속도가 훨씬 빠르고, 더 좋은 테스트

@SpringBootTest - 스프링 컨테이너를 띄워 테스트를 함께 실행   
@Transactional - 각 테스트의 트랜잭션 수행 이후 테스트가 끝나면 롤백
-> DB에 데이터가 남지 않음   
@Commit - 트랙잭션 수행 이후 커밋

### 스프링 JdbcTemplate
JDBC API의 반복 코드를 대부분 제거해주는 라이브러리

### MyBatis
- JdbcTemplate보다 코드를 더 단순화   
- JdbcTemplate에서 조금 더 분할화하여 SQL쿼리문은 XML을 이용하도록 구현되어 있고, 해당 쿼리문을 객체화하여 관리   
- SQL문을 완전히 분리해서 처리하기 때문에 향후 SQL문의 변경이 일어날 때 대처가 수월

### JPA(Java Persistence API)
- JPA는 자바 ORM 기술 표준 인터페이스이고, 여러 업체에서 JPA를 구현   
- 기본적으로 JPA를 구현한 대표적인 오픈소스 ORM 프레임워크인 Hibernate 사용 
- JPA는 어플리케이션과 JDBC 사이에서 동작   
- 기존의 반복 코드는 물론이고, SQL도 직접 만들어서 실행해줌   
- SQL과 데이터 중심의 설계에서 객체 중심의 설계로 전환 가능 -> 생산성 향상 및 유지보수 용이   

**ORM(Object-Relational mappping)**   
Class와 relation을 매핑해주는 기술   
SQL이 아닌 메서드로 데이터를 조작 -> 객체지향적인 코드로 더 직관적, 비즈니스 로직에 집중 가능

```
resources/application.properties

spring.jpa.show-sql=true  // JPA가 생성하는 SQL 출력
spring.jpa.hibernate.ddl-auto=none  // 테이블을 자동으로 생성하는 기능 - none: 사용 x, create: 사용
```

@Entity - JPA가 관리하는 entity   
@ID - Primary key   
@GeneratedValue(strategy = GenerationType.IDENTITY) - DB에 값을 넣으면 id를 자동으로 생성   
@Column(name = ~) - DB에 있는 column 이름과 매핑

EntityManager - DB 연결 정보, 설정 등 DataSource를 내부적으로 가지고 있음. SpringBoot가 자동으로 생성

**JPQL(Java Persistence Query Language)**   
객체지향 쿼리 언어   
객체(entity)를 대상으로 쿼리문 수행

JPA를 통해 데이터를 저장하거나 변경할 때 항상 트랜잭션 안에서 실행되어야 함 ex) join
```java
import org.springframework.transaction.annotation.Transactional;
        
@Transactional
public class MemberService {}
```
- 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋함   
- 만약 런타임 예외가 발생하면 롤백

### 스프링 데이터 JPA
- JPA를 편리하게 사용하도록 도와주는 프레임워크   
- 레포지토리에 구현 클래스 없이 인터페이스만으로 개발 가능   
- 기본 CRUD 기능 제공   
- 페이징 기능 제공   
- 레포지토리 인터페이스에서 JpaRepository 인터페이스를 상속하면,
  스프링 데이터 JPA가 구현체를 만들고 자동으로 스프링 빈으로 등록해줌

## AOP
**메서드 실행 시간을 측정할 때(AOP x)**   
```java
public class MemberService {
    
    public Long join(Member member) {

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("timeMs = " + timeMs + "ms");
        }
    }
}
```
- 시간을 측정하는 로직은 공통 관심 사항
- 공통 관심 사항과 핵심 비즈니스의 로직(핵심 관심 사항)이 섞이면 유지보수가 어려움
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어려움
- 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 함

### AOP(Aspect Oriented Programming)
- 공통 관심 사항과 핵심 관심 사항을 분리   
- 공통 관심 사항을 별도의 공통 로직으로 만듬

![캡처](https://user-images.githubusercontent.com/68456385/126752069-69f56e41-3a38-416c-a5dd-205544bf993c.PNG)

프록시라는 가짜 스프링 빈을 생성
![캡처](https://user-images.githubusercontent.com/68456385/126754321-066807b4-b4b1-4394-8afc-fa2f2726f185.PNG)


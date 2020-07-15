# With-Us-API
쇼핑몰 웹 서버

### With-Us Server with Spring Boot

### 주요기능
```
* 회원관련 기능
    - 회원가입시 이메일 인증기능 구현
    - jwt token을 이용한 회원 통신관리
    - 회원 패스워드 암호화(Bcrypt)

* 상품관련 기능
    - 상품 조회 / 수정 / 삭제기능 구현
    - 장바구니 기능 구현(user정보, 상품정보) ---> 상품 <-> 장바구니 (다대일)

* 리뷰 및 Q&A 관련 기능
    - 리뷰 / Q&A <-> 상품 (다대일)

* Excel Export 기능
    - 구매내역 엑셀 다운로드 기능(추후 구현예정)

* 구매정보 관리기능
    - 구매정보 관리
```

### Development Environment
```
 * Spring version 5.2.5
 * MyBatis version 3.4.6
 * JUnit 5 / mockito version 3.1.0
 * JWT(Json Web Token) version 0.10.7
 * lombok version 1.18.12
 * spring-boot-starter-security
 * spring-boot-starter-mail
 * commons-io:commons-io:2.6
 * ommons-fileupload:commons-fileupload:1.3.1
 ```

### References
 * [JWT(Json Web Token)](https://jwt.io/)
 * [Spring Email Service](https://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/mail.html)
 * [Spring Boot + MyBatis + Mapper](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
 * [Password Encryption](https://mia-dahae.tistory.com/120)
 * [Database Table Modeling - 파워 오브 데이터베이스 도서]
 * [스프링 부트로 배우는 자바 웹 개발 도서]

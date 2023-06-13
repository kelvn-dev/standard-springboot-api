# standard-springboot-api

[![](https://img.shields.io/badge/framework-springboot-success.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![](https://img.shields.io/badge/database-mysql-9cf.svg?logo=mysql)](https://www.mysql.com/)
[![](https://img.shields.io/badge/orm-hibernate-yellowgreen.svg?logo=hibernate)](https://hibernate.org/)
[![](https://img.shields.io/badge/server-tomcat-yellow.svg?logo=apachetomcat)](https://tomcat.apache.org/)
[![](https://img.shields.io/badge/management-maven-red.svg?logo=apachemaven)](https://docs.spring.io/spring-security/reference/)
[![](https://img.shields.io/badge/documentation-swagger-brightgreen.svg?logo=swagger)](https://swagger.io/)
[![](https://img.shields.io/badge/version--control-github-critical.svg?logo=git)](https://git-scm.com/)
[![](https://img.shields.io/badge/auth-spring%20security-success.svg?logo=springsecurity)](https://docs.spring.io/spring-security/reference/)
[![](https://img.shields.io/badge/integrity-JWT-inactive.svg?logo=jsonwebtokens)](https://jwt.io/)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

A modern boilerplate or project structure for **Springboot API** with common toolkit 🛠 

## Table of contents

- [springboot-standard-api](#springboot-standard-api)
    - [Table of contents](#table-of-contents)
    - [Project structure](#project-structure)
    - [Prerequisites](#prerequisites)
    - [Usage](#usage)
    - [References](#references)

## Project structure

```shell
.
├── scripts/
├── src
    ├── main
        ├── java/
            ├── com.kelvn/
                ├── config/
                ├── controller/
                ├── dto/
                ├── enums/
                ├── exception/
                ├── model/
                ├── repository/
                ├── security/
                ├── service/
                ├── utils/
        ├── resources/
├── target/
├── .gitignore
├── pom.xml
├── README.md
```

## Prerequisites

- [JDK](https://www.oracle.com/fr/java/technologies/javase/jdk11-archive-downloads.html)

  > **Warning** **Version**: `11`

- [Apache Maven](https://maven.apache.org/download.cgi)

- [Git](https://git-scm.com/)

## Usage

- **Generating Qmodel by QueryDsl**

    ```shell
    mvn clean install
    ```
  > **Note**: Model classes will be generated into folder target/generated-sources/**/model, copy it to src for using purpose
  
- **💅 Formatting**

    - To implement automated code formatting

      ```shell
      mvn spotless:apply
      ```
      
    - To validate if code was formatted

      ```shell
      mvn spotless:check
      ```
      
- **Githooks**

  - pre-commit

    ```shell
    cp scripts/pre-commit .git/hooks/pre-commit && chmod +x .git/hooks/pre-commit
    ```
    > **Note**: To not run pre-commit
    ```shell
    git commit -m ${message} --no-verify
    ```
> 
  

## References

- Visit [here](https://medium.com/thefreshwrites/best-practices-in-spring-boot-project-structure-2986adb290) to understand Best Practices in Spring Boot Project Structure
- Visit [here](https://medium.com/@miguelangelperezdiaz444/dependency-injection-in-spring-constructor-property-or-setter-which-one-should-i-choose-d38be824c8c1) to understand 💉Dependency Injection
- Visit [here](https://medium.com/@kouomeukevin/create-a-base-entity-with-jpa-8adb35d2b7a3) to understand Base Entity or [AbstractPersistable](https://www.springbyexample.org/examples/spring-data-jpa-auditing-code-example.html) for alternatives
- Visit [here](https://medium.com/javarevisited/what-is-database-audit-and-how-to-audit-a-database-using-a-spring-boot-application-11a08170e687) to understand Database Audit
- Visit [here](https://levelup.gitconnected.com/spring-boot-soft-delete-functionality-with-hibernate-f5ee8c24c99f) to understand Soft Delete functionality
- Visit [here](https://medium.com/javarevisited/dynamic-query-predicate-generation-using-springboot-and-querydsl-fdb0d5d3555b) to understand production-grade dynamic filters using QueryDSL
- Visit [here](https://thomasandolf.medium.com/spring-security-jwts-getting-started-ebdb4e4f1dd1) to understand Spring Security + JWTs
- Visit [here](https://medium.com/@ankithahjpgowda/log-request-and-responses-of-rest-apis-in-springboot-c13f9bc7903f) to understand Log request and responses of REST APIs
- Visit [here](https://www.toptal.com/java/spring-boot-rest-api-error-handling) to understand Spring Boot REST API Error Handling
- Visit [here](https://ultimate.systems/web/blog/how-to-use-modelmapper-with-more-complex-objects-in-spring-boot-java) to understand ModelMapper with more complex objects
- Visit [here](https://medium.com/@mmessell/apply-spotless-formatting-with-git-pre-commit-hook-1c484ea68c34) to understand Spotless formatting with Git pre-commit hook
- Visit [Google](https://developers.google.com/identity/sign-in/web/backend-auth), [Spring blog](https://spring.io/blog/2018/03/06/using-spring-security-5-to-integrate-with-oauth-2-secured-services-such-as-facebook-and-github) to understand OAuth 2.0 for Client-side and [OAuth 2.0 Playground](https://developers.google.com/oauthplayground/), [Graph API Explorer](https://developers.facebook.com/tools/explorer) for testing purpose

## License

(C)2023
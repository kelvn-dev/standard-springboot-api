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
├── bin/
├── docker/
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

- **Generating Qmodel (QueryDsl)**

    ```shell
    mvn clean install
    ```
  > **Note**: Model classes will be generated into folder target/generated-sources/**/model, copy it to src for using purpose

- **Generating cryptographic keys**

  > **Warning**: The openssl extension is required
  ```shell
  openssl version
  ```

  - To generates a new RSA private key with a length of 2048 bits

    ```shell
    openssl genrsa -out keypair.pem 2048
    ```

  - To extracts the public key
    ```shell
    openssl rsa -in keypair.pem -pubout -out public.pem
    ```

  - To converts the private key into the PKCS#8 format
    ```shell
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
    ```

  > **Note**: Or simply
   ```shell
    sh bin/setup-jwt-key.sh
  ```
  
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

  - To setup pre-commit

    ```shell
    cp scripts/pre-commit .git/hooks/pre-commit && chmod +x .git/hooks/pre-commit
    ```
    
    > **Note**: Or simply
    ```shell
    sh bin/setup-pre-commit.sh
    ```
    
    > **Note**: To not run pre-commit
    ```shell
    git commit -m "${message}" --no-verify
    ```
> 
  

## References

- Visit [here](https://medium.com/thefreshwrites/best-practices-in-spring-boot-project-structure-2986adb290) to learn more about Best Practices in Spring Boot Project Structure
- Visit [here](https://medium.com/@miguelangelperezdiaz444/dependency-injection-in-spring-constructor-property-or-setter-which-one-should-i-choose-d38be824c8c1) to learn more about 💉Dependency Injection
- Visit [here](https://medium.com/@kouomeukevin/create-a-base-entity-with-jpa-8adb35d2b7a3) to learn more about Base Entity or [AbstractPersistable](https://www.springbyexample.org/examples/spring-data-jpa-auditing-code-example.html) for alternatives
- Visit [here](https://medium.com/javarevisited/what-is-database-audit-and-how-to-audit-a-database-using-a-spring-boot-application-11a08170e687) to learn more about Database Audit
- Visit [here](https://levelup.gitconnected.com/spring-boot-soft-delete-functionality-with-hibernate-f5ee8c24c99f) to learn more about Soft Delete functionality
- Visit [here](https://medium.com/javarevisited/dynamic-query-predicate-generation-using-springboot-and-querydsl-fdb0d5d3555b) to learn more about production-grade dynamic filters using QueryDSL
- Visit [here](https://thomasandolf.medium.com/spring-security-jwts-getting-started-ebdb4e4f1dd1) to learn more about Spring Security + JWTs
- Visit [here](https://medium.com/@ankithahjpgowda/log-request-and-responses-of-rest-apis-in-springboot-c13f9bc7903f) to learn more about Log request and responses of REST APIs
- Visit [here](https://www.toptal.com/java/spring-boot-rest-api-error-handling) to learn more about Spring Boot REST API Error Handling
- Visit [here](https://ultimate.systems/web/blog/how-to-use-modelmapper-with-more-complex-objects-in-spring-boot-java) to learn more about ModelMapper with more complex objects
- Visit [here](https://medium.com/@mmessell/apply-spotless-formatting-with-git-pre-commit-hook-1c484ea68c34) to learn more about Spotless formatting with Git pre-commit hook
- Visit [Google](https://developers.google.com/identity/sign-in/web/backend-auth), [Spring blog](https://spring.io/blog/2018/03/06/using-spring-security-5-to-integrate-with-oauth-2-secured-services-such-as-facebook-and-github) to learn more about OAuth 2.0 for Client-side and [OAuth 2.0 Playground](https://developers.google.com/oauthplayground/), [Graph API Explorer](https://developers.facebook.com/tools/explorer) for testing purpose

## License

(C)2023
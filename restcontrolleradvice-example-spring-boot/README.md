https://loizenai.com/restcontrolleradvice-springboot-example/

# @RestControllerAdvice Example SpringBoot

![@RestControllerAdvice Example SpringBoot](https://loizenai.com/wp-content/uploads/2020/12/@RestControllerAdvice-Example-SpringBoot.png)

Tutorial: “@RestControllerAdvice Example SpringBoot – Error Handling for REST with Spring”.

In the article, I introduce about @RestControllerAdvice annotation of SpringBoot to handle RestAPI exception with a Github running sourcecode and details explanation example steps.

* Technologies we use in the article:
– Java 1.8
– Maven
– Spring Boot

## Annotation Type @RestControllerAdvice

@RestControllerAdvice is a new feature of Spring Framework 4.3, an annotation with combined @ControllerAdvice + @ResponseBody. So @RestControllerAdvice can help us to handle Exception with RestfulApi by a cross-cutting concern solution: @ExceptionHandler.

@Target(value=TYPE)
 @Retention(value=RUNTIME)
 @Documented
 @ControllerAdvice
 @ResponseBody
public @interface RestControllerAdvice

@RestControllerAdvice is processed if an appropriate HandlerMapping-HandlerAdapter pair is configured such as the RequestMappingHandlerMapping-RequestMappingHandlerAdapter pair which are the default in the MVC Java config and the MVC namespace.

## Related post

- [Angular 8 SpringBoot MySQL CRUD Example](https://loizenai.com/angular-8-springboot-mysql-crud-example/)
- [Angular 11 SpringBoot CRUD MySQL Example](https://loizenai.com/angular-11-springboot-crud-mysql-example/)
- [SpringBoot MongoDB Angular CRUD Example](https://loizenai.com/spring-boot-mongodb-angular-crud-example-github/)
- [How to Integrate Angular 9 with SpringBoot Tutorial](https://loizenai.com/how-to-integrate-angular-9-with-springboot-tutorial/)
- [SpringBoot Angular 10 PostgreSQL CRUD RestAPI Example](https://loizenai.com/springboot-angular-10-postgresql-crud-restapi-example/)

package br.ce.wcaquino.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApiTest {

    @BeforeClass
    public static void beforeClass(){
        RestAssured.baseURI = "http://192.168.0.8:8001/tasks-backend";
    }

    @Test
    public void deve_retornar_tarefas(){
        RestAssured.given()
                .when().get("/todo")
                .then()
                .statusCode(200);
    }

    @Test
    public void deve_adicionar_tarefa_com_sucesso(){
        RestAssured.given()
                .body("{ \"task\": \"Teste api\", \"dueDate\": \"2021-02-05\" }").contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(201)
        ;
    }

    @Test
    public void nao_deve_adicionar_tarefa_invalida(){
        RestAssured.given()
                .body("{ \"task\": \"Teste api\", \"dueDate\": \"2010-02-05\" }").contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(400)
                .body("message", CoreMatchers.is("Due date must not be in past"))
        ;
    }

}

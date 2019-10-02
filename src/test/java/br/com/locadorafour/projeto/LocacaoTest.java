package br.com.locadorafour.projeto;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class LocacaoTest {

    public LocacaoTest() {
        baseURI = "http://172.22.6.1:8080/api";
    }

    @Test
    public void alugarFilme() {
        given()
            .contentType("application/json")
            .header("token","965163")
        .when()
            .get("/aluga/10")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("mensagem", containsString("Sucesso"))
            .body("id", containsString("10"))
            .body("filme", containsString("Guardians of the Galaxy"));
    }

    @Test
    public void devolveFilmeQueAlugado() {
        given()
            .contentType("application/json")
            .header("token","593389")
        .when()
            .get("/devolve/6")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("mensagem", containsString("Filme devolvido"));
    }

    @Test
    public void devolveFilmeQueNaoAluguei() {
        given()
            .contentType("application/json")
            .header("token","593389")
        .when()
             .get("/devolve/10")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("mensagem", containsString("Você não alugou esse filme"));
    }

}

package br.com.locadorafour.projeto;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class UsuariosTest {
    public UsuariosTest() {
        baseURI = "http://172.22.6.1:8080/api";
    }

    @Test
    public void login() {
        String myJson = "{\"email\": \"fernando@daurte.com.br\", \"senha\": \"111111\"}";
        given()
            .contentType("application/json")
            .body(myJson)
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("token_acesso", notNullValue());
    }

    @Test
    public void criaUsuario() {
        String myJson = "{\"nome\":\"Maier\",\"email\": \"marier@verde.com.br\",\"senha\": \"159753\"}";
        given()
                .contentType("application/json")
                .body(myJson)
         .when()
                .post("/usuario/criarUsuario")
        .then()
                .statusCode(200)
                .body("token_acesso", notNullValue());// retornando plain
    }

    @Test
    public void alteraUsuario() {
        String myJson = "{\"nome\":\"Baruffi\",\"email\": \"emaessio@verde.com.br\",\"senha\": \"159753\"}";
        given().header("token","882102") //ARRUMAR PARA COLOCAR O METODO DO TOKEN
                .contentType("application/json")
                .body(myJson)
         .when()
                .put("/usuario/alterarUsuario")
         .then()
                .statusCode(200)
                .body("mensagem", containsString("Usuario atualizado"))
                .body("novo_token_acesso", notNullValue());
    }
    @Test
    public void sair() {
        given().header("token","882102") //ARRUMAR PARA COLOCAR O METODO DO TOKEN
            .contentType("application/json")
        .when()
            .get("/sair")
        .then()
            .statusCode(200)
            .body(anything());
    }
}

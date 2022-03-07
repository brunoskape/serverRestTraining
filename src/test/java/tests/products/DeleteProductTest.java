package tests.products;

import factory.Product;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.LoginUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DeleteProductTest extends BaseTest {

    LoginUtil login = new LoginUtil();

    @Test
    public void deleteProduct(){

        Product product = new Product("mouse pad delete6", "120", "mouse pad gamer com rgb","3");

   String productId =
           given()
                .headers("Authorization", login.getToken())
                .body(product)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .extract().path("_id");

   given()
           .contentType(ContentType.JSON)
           .headers("Authorization", login.getToken())
           .body(product)
           .when()
           .delete("/produtos/" + productId)
           .then()
           .statusCode(200)
           .body("message", equalTo("Registro excluído com sucesso"));

    }

    @Test
    public void deleteProductWithoutToken(){

        Product product = new Product("mouse pad delete7", "120", "mouse pad gamer com rgb","3");

        String productId =
                given()
                        .headers("Authorization", login.getToken())
                        .body(product)
                        .when()
                        .post("/produtos")
                        .then()
                        .statusCode(201)
                        .body("message", is("Cadastro realizado com sucesso"))
                        .extract().path("_id");

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .delete("/produtos/" + productId)
                .then()
                .statusCode(401)
                .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));

    }



}

package tests.integration.products;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.utils.BaseTest;
import tests.utils.LoginUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetProductTest extends BaseTest {
    LoginUtil login = new LoginUtil();


    @Test
    public void listAllProductsTest(){

    given()
            .contentType(ContentType.JSON)
            .when()
            .get("/produtos")
            .then()
            .statusCode(200);

    }

    @Test
    public void listParamProductTest(){

        given()
                .contentType(ContentType.JSON)
                .queryParam("nome", "mouse pad gamer")
                .queryParam("preco", "120")
                .get("/produtos")
                .then()
                .statusCode(200)
                .body("produtos.preco[0]", equalTo(120))
                .body("produtos.quantidade[0]", equalTo(3));

    }

    @Test
    public void listParamInvalidtProductTest(){

        given()
                .contentType(ContentType.JSON)
                .queryParam("teste", "mouse pad gamer")
                .queryParam("teste2", "120")
                .get("/produtos")
                .then()
                .statusCode(400)
                .body("teste", equalTo("teste não é permitido"))
                .body("teste2", equalTo("teste2 não é permitido"));

    }
}

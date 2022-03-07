package tests.products;

import factory.Product;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.LoginUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateProductTest extends BaseTest {

    LoginUtil login = new LoginUtil();

    @Test
    public void insertProduct(){

        Product product = new Product("mouse pad gamer199", "120", "mouse pad gamer com rgb","3");

          given()
                               .headers("Authorization", login.getToken())
                               .body(product)
                               .when()
                                .post("/produtos")
                                .then()
                                .statusCode(201)
                                .body("message", is("Cadastro realizado com sucesso"));

    }

    @Test
    public void insertProductAndCheckIfProductWasInserted(){

        Product product = new Product("mouse pad gamer41", "120", "mouse pad gamer com rgb","3");

        String productInput = given()
                .headers("Authorization", login.getToken())
                .body(product)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"))
                .extract().path("_id");

        given()
               .when()
               .get("/produtos?_id=" + productInput)
               .then()
               .statusCode(200)
               .body("produtos.nome", hasItem("mouse pad gamer41"));

    }
    @Test
    public void insertProductWithSameName(){

        Product product = new Product("mouse pad gamer5126", "120", "mouse pad gamer com rgb","3");

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
                .headers("Authorization", login.getToken())
                .when()
                .body(product)
                .post("/produtos")
                .then()
                .statusCode(400)
                .body("message", is("Já existe produto com esse nome"));

    }


    @Test
    public void insertProductWithoutToken(){

        Product product = new Product("mouse pad gamer", "120", "mouse pad gamer com rgb","3");

        given()
                .body(product)
                .when()
                .post("/produtos")
                .then()
                .statusCode(401)
                .body("message", is("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));

    }

}

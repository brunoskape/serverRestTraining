package tests.integration.products;

import com.github.javafaker.Faker;
import tests.factory.Product;
import org.testng.annotations.Test;
import tests.utils.BaseTest;
import tests.utils.LoginUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostProductTest extends BaseTest {

    LoginUtil login = new LoginUtil();

    Faker faker = new Faker();
    String name = faker.commerce().productName();
    int price = faker.number().numberBetween(1, 100);
    String description = faker.commerce().material();
    int quantity = faker.number().randomDigitNotZero();

    @Test
    public void insertProduct() {

        Product product = new Product(name, price, description, quantity);

        given()
                .headers("Authorization", login.getToken())
                .body(product)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso")).log().all();

    }

    @Test
    public void insertProductAndCheckIfProductWasInserted(){

        Product product = new Product(name, price, description, quantity);

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
               .body("produtos._id", hasItem(productInput)).log();

    }
    @Test
    public void insertProductWithSameName(){

        Product product = new Product("mouse pad gamer12", 120, "mouse pad gamer com rgb",3);

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

        Product product = new Product(name, price, description, quantity);

        given()
                .body(product)
                .when()
                .post("/produtos")
                .then()
                .statusCode(401)
                .body("message", is("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));

    }

}

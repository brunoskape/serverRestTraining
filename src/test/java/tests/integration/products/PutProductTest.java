package tests.integration.products;

import com.github.javafaker.Faker;
import tests.factory.Product;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.utils.BaseTest;
import tests.utils.LoginUtil;
import tests.utils.ProductUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

//implementar
public class PutProductTest extends BaseTest {

    LoginUtil login = new LoginUtil();
    ProductUtil product = new ProductUtil();


//melhorar
     @Test
    public void PutProduct(){

         Product product = new Product("mouse pad altput", 120, "mouse pad gamer com rgb",3);

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
                 .put("/produtos/" + productId)
                 .then()
                 .statusCode(200)
                 .body("message", equalTo("Registro alterado com sucesso"));


     }


}



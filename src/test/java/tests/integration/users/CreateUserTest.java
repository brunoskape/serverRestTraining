package tests.integration.users;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.factory.Product;
import tests.factory.User;
import tests.utils.BaseTest;

public class CreateUserTest extends BaseTest {



@Test
public void createUserAdmTest(){

    User user = new User("Bruno2", "bruno2@test.com", "123@", "true");

     given()
             .contentType(ContentType.JSON)
             .body(user)
             .when()
             .post("/usuarios")
             .then()
             .statusCode(201)
             .body("message", is("Cadastro realizado com sucesso"));

}

@Test
public void createUserTest(){

        User user = new User("Bruno user normal", "normaluser@test.com", "123@", "false");

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", is("Cadastro realizado com sucesso"));

    }


}

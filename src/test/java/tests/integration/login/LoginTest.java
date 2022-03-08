package tests.integration.login;

import tests.factory.Login;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.utils.BaseTest;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {


    @Test
    public void LoginTestSucess(){

        Login login = new Login("fulano@qa.com", "teste");

          given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .statusCode(200);

    }

    @Test
    public void LoginTestFail(){

        Login login = new Login("fulanoss@qa.com", "teste");

        given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("message", equalTo("Email e/ou senha inválidos"));

    }

}
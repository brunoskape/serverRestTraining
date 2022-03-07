package tests.login;

import factory.Login;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {


    private  String email;
    private  String password;

    public LoginTest(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public LoginTest() {

    }

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
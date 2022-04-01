package tests.integration.users;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.utils.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ListUsersTest extends BaseTest {

@Test
public void listAllUser(){

    given()
            .contentType(ContentType.JSON)
            .when()
            .get("/usuarios")
            .then()
            .statusCode(200)
            .body("usuarios.email[0]", equalTo("fulano@qa.com"));
}


@Test
public void listUserWithParam(){

    given()
            .contentType(ContentType.JSON)
            .queryParam("nome", "Fulano da Silva")
            .when()
            .get("/usuarios")
            .then()
            .statusCode(200)
            .body("usuarios.email[0]", equalTo("fulano@qa.com"));


}

}

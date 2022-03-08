package tests.utils;

import tests.factory.Login;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class LoginUtil {

    public String getToken(){

         Login input = new Login("fulano@qa.com", "teste");

        String token =   given()
                .contentType(ContentType.JSON)
                .body(input)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().path("authorization");

        return token;

    }



}

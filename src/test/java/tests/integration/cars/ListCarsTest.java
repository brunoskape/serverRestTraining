package tests.integration.cars;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.utils.BaseTest;

import static io.restassured.RestAssured.given;

public class ListCarsTest extends BaseTest {


@Test
public void listAllCars(){

    given()
            .contentType(ContentType.JSON)
            .when()
            .get("/carrinhos")
            .then()
            .statusCode(200);

}

@Test
public void listCarWithParam(){

  String carId =

          given()
            .contentType(ContentType.JSON)
            .when()
            .get("/carrinhos")
            .then()
            .statusCode(200)
            .extract().path("_id");


    given()
            .contentType(ContentType.JSON)
            .queryParam("_id", carId)
            .get("/carrinhos")
            .then()
            .statusCode(200);


}


}

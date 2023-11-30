package herokuapp.booker.tests.auth;

import herokuapp.booker.models.LoginBodyModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthTest {

    @Test
    void AuthAndCreateTokenTest () {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");
        authData.setPassword("password123");

        LoginBodyModel token = given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginBodyModel.class);

        System.out.println(token);
    }
}

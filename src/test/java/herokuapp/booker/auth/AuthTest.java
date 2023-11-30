package herokuapp.booker.auth;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class AuthTest {

    @Test
    void AuthAndCreateTokenTest () {

        String authData = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        String token = given()
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
                .extract().path("token");
        System.out.println(token);
    }
}

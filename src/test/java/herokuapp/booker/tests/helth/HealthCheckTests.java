package herokuapp.booker.tests.helth;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckTests {
    @Test
    void GetHealthCheckTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://restful-booker.herokuapp.com/ping")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }
}

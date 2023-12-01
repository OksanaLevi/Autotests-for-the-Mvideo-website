package herokuapp.booker.tests.booking;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class DeleteBookingTests {

    @Test
    void DeleteBookingTest() {

        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .header("Cookie", "token=70c7284070f5bf3")
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/13")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }
}

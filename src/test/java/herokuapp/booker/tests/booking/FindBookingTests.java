package herokuapp.booker.tests.booking;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class FindBookingTests {

    @Test
    void GetBookingIdsTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void GetBookingTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/910")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", is("Jake"))
                .body("bookingdates.checkin", is("2018-01-01"));
    }
}

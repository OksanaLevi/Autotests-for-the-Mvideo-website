package herokuapp.booker.tests.booking;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class СhangeBookingTests {

    @Test
    void UpdateBookingTest() {

        String clientsCard ="{\n" +
                "    \"firstname\" : \"Kris\",\n" +
                "    \"lastname\" : \"Cruz\",\n" +
                "    \"totalprice\" : 128,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-05\",\n" +
                "        \"checkout\" : \"2023-11-14\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .cookie("token=<b4b6e55ee19daa8>")
                .body(clientsCard)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/3023")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}

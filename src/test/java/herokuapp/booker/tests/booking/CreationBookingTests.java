package herokuapp.booker.tests.booking;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class CreationBookingTests {

    @Test
    void CreateBookingTest() {

        String clientsCard ="{\n" +
                "    \"firstname\" : \"Kris\",\n" +
                "    \"lastname\" : \"Cruise\",\n" +
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
                .body(clientsCard)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}

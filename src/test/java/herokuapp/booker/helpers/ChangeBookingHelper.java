package herokuapp.booker.helpers;

import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;

public class ChangeBookingHelper {
    public static ExtractableResponse changeBooking(int id) {
        ExtractableResponse response = given()
                .when()
                .get("/booking" + id)
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }
}

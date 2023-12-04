package herokuapp.booker.helpers;

import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.BookingSpec.bookingFindRemovedIdSpec;
import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;

public class FindBookingsHelper {
    public static ExtractableResponse getArrayBookings() {
        ExtractableResponse response = given()
                .when()
                .get("/booking")
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }

    public static ExtractableResponse findBooking(int id) {
        ExtractableResponse response = given()
                .when()
                .get("/booking/" + id)
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }

    public static ExtractableResponse findRemovedBooking(int id) {
        ExtractableResponse response = given()
                .when()
                .get("/booking/" + id)
                .then()
                .spec(bookingFindRemovedIdSpec)
                .extract();

        return response;
    }
}

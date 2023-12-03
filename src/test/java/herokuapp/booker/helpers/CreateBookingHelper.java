package herokuapp.booker.helpers;

import herokuapp.booker.models.BookingBodyModel;
import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.BookingSpec.bookingRequestSpec;
import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;

public class CreateBookingHelper {
    public static ExtractableResponse addBooking (BookingBodyModel value) {

        ExtractableResponse response = given(bookingRequestSpec)
                .body(value)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }
}

package herokuapp.booker.api.requests;

import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.BookingBodyModel;
import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.BookingSpec.bookingRequestSpec;
import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;

public class CreateBookingHelper {
    public static ExtractableResponse addBooking(BookingBodyModel value) {
        RandomTestData url = new RandomTestData();

        ExtractableResponse response = given(bookingRequestSpec)
                .body(value)
                .when()
                .post(url.bookingUrl)
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }
}

package herokuapp.booker.helpers;

import herokuapp.booker.models.BookingBodyModel;
import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.AuthSpec.authRequestSpec;
import static herokuapp.booker.specs.AuthSpec.authResponseSpec;
import static io.restassured.RestAssured.given;

public class CreateBookingHelper {
    public static ExtractableResponse getBookingParams (BookingBodyModel value) {

        ExtractableResponse response = given(authRequestSpec)
                .body(value)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .spec(authResponseSpec)
                .extract();

        return response;
    }
}

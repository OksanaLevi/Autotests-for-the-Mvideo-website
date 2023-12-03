package herokuapp.booker.helpers;

import herokuapp.booker.models.LoginBodyModel;
import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.BookingSpec.bookingRequestSpec;
import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthHelper {

    public static ExtractableResponse getAuthToken(LoginBodyModel value) {

        ExtractableResponse response = given(bookingRequestSpec)
                .body(value)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }
}

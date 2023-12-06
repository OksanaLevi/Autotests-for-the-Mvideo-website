package herokuapp.booker.api.requests;

import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.LoginBodyModel;
import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.BookingSpec.bookingRequestSpec;
import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthHelper {

    public static ExtractableResponse getAuthToken(LoginBodyModel value) {
        RandomTestData url = new RandomTestData();

        ExtractableResponse response = given(bookingRequestSpec)
                .body(value)
                .when()
                .post(url.authUrl)
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }
}

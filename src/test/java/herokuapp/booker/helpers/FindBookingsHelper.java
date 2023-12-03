package herokuapp.booker.helpers;

import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.AuthSpec.authResponseSpec;
import static io.restassured.RestAssured.given;

public class FindBookingsHelper {
    public static ExtractableResponse getBookings() {
        ExtractableResponse response = given()
                .when()
                .get("/booking")
                .then()
                .spec(authResponseSpec)
                .extract();

        return response;
    }
}

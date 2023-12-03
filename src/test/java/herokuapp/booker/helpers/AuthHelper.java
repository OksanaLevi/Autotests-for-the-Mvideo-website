package herokuapp.booker.helpers;

import herokuapp.booker.models.LoginBodyModel;
import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.AuthSpec.authRequestSpec;
import static herokuapp.booker.specs.AuthSpec.authResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthHelper {

    public static ExtractableResponse getAuthToken(LoginBodyModel value) {

        ExtractableResponse response = given(authRequestSpec)
                .body(value)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .spec(authResponseSpec)
                .extract();

        return response;
    }
}

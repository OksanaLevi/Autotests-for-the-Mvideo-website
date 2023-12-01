package herokuapp.booker.tests.auth;

import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static herokuapp.booker.specs.AuthSpec.authRequestSpec;
import static herokuapp.booker.specs.AuthSpec.authResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthTest {

    @Test
    void AuthAndCreateTokenTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");
        authData.setPassword("password123");

        LoginResponseModel response = new LoginResponseModel();

        LoginResponseModel token = given(authRequestSpec)
                .body(authData)
                .when()
                .post("/auth")
                .then()
                .spec(authResponseSpec)
                .extract().as(LoginResponseModel.class);

        System.out.println((token));
    }

    @Test
    void MissingPasswordAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");

        LoginResponseModel response = new LoginResponseModel();

        LoginResponseModel token = given(authRequestSpec)
                .body(authData)
                .when()
                .post("/auth")
                .then()
                .spec(authResponseSpec)
                .extract().as(LoginResponseModel.class);

        System.out.println((token));
    }
}

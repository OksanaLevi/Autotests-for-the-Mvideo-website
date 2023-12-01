package herokuapp.booker.tests.auth;

import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static herokuapp.booker.specs.AuthSpec.authRequestSpec;
import static herokuapp.booker.specs.AuthSpec.authResponseSpec;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {

    @Test
    public void authAndCreateTokenTest() {
        AuthHelper.getAuthToken().isEmpty();
    }

    @Test
    void missingPasswordAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");

        LoginResponseModel response = given(authRequestSpec)
                .body(authData)
                .when()
                .post("/auth")
                .then()
                .spec(authResponseSpec)
                .extract().as(LoginResponseModel.class);

        assertEquals("Bad credentials", response.getReason());
    }
}

package herokuapp.booker.tests.auth;

import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AuthTest {

    @Test
    public void authAndCreateTokenTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");
        authData.setPassword("password123");

        ExtractableResponse response = AuthHelper.getAuthToken(authData);
        String token = response.as(LoginResponseModel.class).getToken();

        assertFalse(token.isEmpty());
    }

    @Test
    void missingPasswordAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();

        authData.setUsername("admin");
        ExtractableResponse response = AuthHelper.getAuthToken(authData);

        assertEquals("Bad credentials", response.as(LoginResponseModel.class).getReason());
    }
}
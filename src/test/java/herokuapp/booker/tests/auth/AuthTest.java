package herokuapp.booker.tests.auth;

import herokuapp.booker.config.AuthConfig;
import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;
import io.restassured.response.ExtractableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AuthTest {
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Test
    public void authAndCreateTokenTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername(config.username());
        authData.setPassword(config.password());

        ExtractableResponse response = AuthHelper.getAuthToken(authData);
        String token = response.as(LoginResponseModel.class).getToken();

        assertFalse(token.isEmpty());
    }

    @Test
    void missingPasswordAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();

        authData.setUsername(config.username());
        ExtractableResponse response = AuthHelper.getAuthToken(authData);

        assertEquals("Bad credentials", response.as(LoginResponseModel.class).getReason());
    }
}
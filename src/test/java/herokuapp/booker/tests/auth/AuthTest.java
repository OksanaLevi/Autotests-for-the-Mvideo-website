package herokuapp.booker.tests.auth;

import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {

    @Test
    public void authAndCreateTokenTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");
        authData.setPassword("password123");

        AuthHelper.getAuthToken(authData).isEmpty();
    }

    @Test
    void missingPasswordAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        LoginResponseModel response = new LoginResponseModel();

        authData.setUsername("admin");
        AuthHelper.getAuthToken(authData);

        assertEquals("Bad credentials", response.getReason());
    }
}
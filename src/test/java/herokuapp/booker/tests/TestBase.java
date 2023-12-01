package herokuapp.booker.tests;

import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.models.LoginBodyModel;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    AuthHelper authorization = new AuthHelper();

    @BeforeEach
    void getAuthToken() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");
        authData.setPassword("password123");

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        authorization.getAuthToken(authData);
    }
}

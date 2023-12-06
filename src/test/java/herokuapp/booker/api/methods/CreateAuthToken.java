package herokuapp.booker.api.methods;

import herokuapp.booker.config.AuthConfig;
import herokuapp.booker.api.requests.AuthHelper;
import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;
import io.restassured.response.ExtractableResponse;
import org.aeonbits.owner.ConfigFactory;

import static io.qameta.allure.Allure.step;

public class CreateAuthToken {
    public static String token;

    public void createAuthToken() {
        step("Создание нового токена аутентификации", () -> {
            AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
            LoginBodyModel authData = new LoginBodyModel();
            authData.setUsername(config.username());
            authData.setPassword(config.password());


            ExtractableResponse responseToken = AuthHelper.getAuthToken(authData);
            token = responseToken.as(LoginResponseModel.class).getToken();
        });
    }
}

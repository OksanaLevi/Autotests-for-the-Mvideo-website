package herokuapp.booker.tests.auth;

import herokuapp.booker.config.AuthConfig;
import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;
import herokuapp.booker.tests.TestBase;
import io.qameta.allure.*;
import io.restassured.response.ExtractableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("Бронирование номеров")
@Story("Аутентификация")
@Owner("Левинская Оксана")
public class AuthTest extends TestBase {

    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Test
    @Tags({
            @Tag("auth"),
            @Tag("post")
    })
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("POST. Проверка успешного создания нового токена аутентификации")
    public void authAndCreateTokenTest() {
        step("Проверка статус-кода и наличия значения в параметре token", () -> {
            LoginBodyModel authData = new LoginBodyModel();
            authData.setUsername(config.username());
            authData.setPassword(config.password());

            ExtractableResponse response = AuthHelper.getAuthToken(authData);
            String token = response.as(LoginResponseModel.class).getToken();

            assertFalse(token.isEmpty());
        });
    }

    @Test
    @Tags({
            @Tag("auth"),
            @Tag("post"),
            @Tag("negative")
    })
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("POST. Проверка ошибки при создании токена аутентификации, если пароль не введен или введен неверно")
    void missingPasswordAuthTest() {
        step("Проверка статус-кода и текста ошибки", () -> {
            LoginBodyModel authData = new LoginBodyModel();

            authData.setUsername(config.username());
            ExtractableResponse response = AuthHelper.getAuthToken(authData);

            assertEquals("Bad credentials", response.as(LoginResponseModel.class).getReason());
        });
    }
}
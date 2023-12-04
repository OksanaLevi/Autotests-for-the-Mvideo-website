package herokuapp.booker.tests.helth;

import herokuapp.booker.helpers.ChangeBookingHelper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;

@Epic("Бронирование номеров")
@Story("Здоровье сборки")
@Owner("Левинская Оксана")
public class HealthCheckTests {

    @Test
    @Tags({
            @Tag("health"),
            @Tag("get")
    })
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET. Проверка здоровья сборки")
    void getHealthCheckTest() {
        step("Проверка статус-кода", () -> {
            ChangeBookingHelper.checkHealth();
        });
    }

    @BeforeEach
    void openUrl() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}

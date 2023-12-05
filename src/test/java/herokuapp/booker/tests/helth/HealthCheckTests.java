package herokuapp.booker.tests.helth;

import herokuapp.booker.helpers.ChangeBookingHelper;
import herokuapp.booker.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Epic("Бронирование номеров")
@Story("Здоровье сборки")
@Owner("Левинская Оксана")
public class HealthCheckTests extends TestBase {

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
}

package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.ChangeBookingHelper;
import herokuapp.booker.helpers.FindBookingsHelper;
import herokuapp.booker.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Epic("Бронирование номеров")
@Story("Удаление бронирования")
@Owner("Левинская Оксана")
public class DeleteBookingTests extends TestBase {

    @Test
    @Tag("delete")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("DELETE. Проверка удаления бронирования")
    void deleteBookingTest() {
        step("Проверка статус-кода, поиск удаленного бронирование по ID", () -> {
            ChangeBookingHelper.deleteBooking(token, bookingId);
            FindBookingsHelper.findRemovedBooking(bookingId);
        });
    }
}

package herokuapp.booker.tests.booking;

import herokuapp.booker.api.requests.ChangeBookingHelper;
import herokuapp.booker.api.requests.FindBookingsHelper;
import herokuapp.booker.api.methods.CreateAuthToken;
import herokuapp.booker.api.methods.CreateBooking;
import herokuapp.booker.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static herokuapp.booker.api.methods.CreateAuthToken.token;
import static herokuapp.booker.api.methods.CreateBooking.bookingId;
import static io.qameta.allure.Allure.step;

@Epic("Бронирование номеров")
@Story("Удаление бронирования")
@Owner("Левинская Оксана")
public class DeleteBookingTests extends TestBase {
    CreateAuthToken auth = new CreateAuthToken();
    CreateBooking newBooking = new CreateBooking();

    @Test
    @Tag("delete")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("DELETE. Проверка удаления бронирования")
    void deleteBookingTest() {
        auth.createAuthToken();
        newBooking.createBooking();
        step("Проверка статус-кода, поиск удаленного бронирование по ID", () -> {
            ChangeBookingHelper.deleteBooking(token, bookingId);
            FindBookingsHelper.findRemovedBooking(bookingId);
        });
    }
}

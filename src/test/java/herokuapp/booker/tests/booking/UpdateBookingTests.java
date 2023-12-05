package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.ChangeBookingHelper;
import herokuapp.booker.helpers.components.CreateAuthToken;
import herokuapp.booker.helpers.components.CreateBooking;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import herokuapp.booker.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static herokuapp.booker.helpers.components.CreateAuthToken.token;
import static herokuapp.booker.helpers.components.CreateBooking.bookingId;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Бронирование номеров")
@Story("Изменение бронирования")
@Owner("Левинская Оксана")
public class UpdateBookingTests extends TestBase {
    CreateAuthToken auth = new CreateAuthToken();
    CreateBooking newBooking = new CreateBooking();

    @Test
    @Tags({
            @Tag("update"),
            @Tag("put")
    })
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("PUT. Проверка обновления всех полей бронирования")
    public void updateBookingTest() {
        auth.createAuthToken();
        newBooking.createBooking();
        step("Проверка статус-кода", () -> {
            RandomTestData randomTestData = new RandomTestData();
            BookingDatesModel date = new BookingDatesModel();
            BookingBodyModel bookingData = new BookingBodyModel();

            date.setCheckin(randomTestData.checkinDate);
            date.setCheckout(randomTestData.checkoutDate);
            bookingData.setFirstname(randomTestData.firstname);
            bookingData.setLastname(randomTestData.lastname);
            bookingData.setTotalprice(randomTestData.price);
            bookingData.setDepositpaid(randomTestData.depositPaid);
            bookingData.setBookingdates(date);
            bookingData.setAdditionalneeds(randomTestData.additionalNeeds);

            ChangeBookingHelper.changeBookingPut(token, bookingId, bookingData);
        });
    }

    @Test
    @Tags({
            @Tag("update"),
            @Tag("patch")
    })
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("PATCH. Проверка обновления выборочных полей бронирования")
    void partialUpdateBookingTest() {
        auth.createAuthToken();
        newBooking.createBooking();
        step("Проверка статус-кода, значений для измененных полей", () -> {
            BookingBodyModel bookingData = new BookingBodyModel();
            RandomTestData randomTestData = new RandomTestData();

            bookingData.setFirstname(randomTestData.firstname);
            bookingData.setLastname(randomTestData.lastname);

            ChangeBookingHelper.changeBookingPatch(token, bookingId, bookingData);

            assertEquals(randomTestData.firstname, bookingData.getFirstname());
            assertEquals(randomTestData.lastname, bookingData.getLastname());
        });
    }
}
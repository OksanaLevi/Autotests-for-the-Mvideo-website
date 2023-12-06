package herokuapp.booker.tests.booking;

import herokuapp.booker.api.requests.CreateBookingHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.ArrayBookingModel;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import herokuapp.booker.tests.TestBase;
import io.qameta.allure.*;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("Бронирование номеров")
@Story("Создание бронирования")
@Owner("Левинская Оксана")
public class CreationBookingTests extends TestBase {

    @Test
    @Tags({
            @Tag("create"),
            @Tag("post")
    })
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("POST. Проверка создания нового бронирования")
    void createBookingTest() {
        step("Проверка статус-кода и присвоения значения в параметр ID", () -> {
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

            ExtractableResponse response = CreateBookingHelper.addBooking(bookingData);
            int bookingDetails = response.as(ArrayBookingModel.class).getBookingid();
            String bookingId = String.valueOf(bookingDetails);

            assertFalse(bookingId.isEmpty());
        });
    }
}

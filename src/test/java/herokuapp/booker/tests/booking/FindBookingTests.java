package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.CreateBookingHelper;
import herokuapp.booker.helpers.FindBookingsHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.ArrayBookingModel;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import herokuapp.booker.tests.TestBase;
import io.qameta.allure.*;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("Бронирование номеров")
@Story("Поиск бронирований")
@Owner("Левинская Оксана")
public class FindBookingTests extends TestBase {

    @Test
    @Tag("get")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка получения списка бронирований")
    void getBookingIdsTest() {
        step("GET. Проверка статус-кода, наличия значения в параметре ID", () -> {
            ExtractableResponse response = FindBookingsHelper.getArrayBookings();
            ArrayBookingModel[] ids = response.as(ArrayBookingModel[].class);
            int id = ids[0].getBookingid();
            String bookingId = String.valueOf(id);

            assertFalse(bookingId.isEmpty());
        });
    }

    @Test
    @Tag("get")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("GET. Проверка получения бронирования по ID")
    void getBookingTest() {
        step("Создание бронирования и его последующий поиск. Проверка статус-кода, соответствия значения полей", () -> {
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

            FindBookingsHelper.findBooking(bookingDetails);

            assertEquals(randomTestData.firstname, bookingData.getFirstname());
            assertEquals(randomTestData.checkinDate, date.getCheckin());
        });
    }
}

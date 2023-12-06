package herokuapp.booker.api.methods;

import herokuapp.booker.api.requests.CreateBookingHelper;
import herokuapp.booker.helpers.RandomTestData;
import herokuapp.booker.models.ArrayBookingModel;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import io.restassured.response.ExtractableResponse;

import static io.qameta.allure.Allure.step;

public class CreateBooking {
    public static int bookingId;

    public void createBooking() {
        step("Создание нового бронирования для последующего использования в тесте", () -> {
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

            ExtractableResponse responseId = CreateBookingHelper.addBooking(bookingData);
            bookingId = responseId.as(ArrayBookingModel.class).getBookingid();
        });

    }
}

package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.CreateBookingHelper;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import herokuapp.booker.models.СreatedReservationModel;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreationBookingTests {

    @Test
    void createBookingTest() {

        BookingDatesModel date = new BookingDatesModel();
        date.setCheckin("2023-11-10");
        date.setCheckout("2023-11-15");

        BookingBodyModel bookingData = new BookingBodyModel();
        bookingData.setFirstname("Olga");
        bookingData.setLastname("Bobrova");
        bookingData.setTotalprice(128);
        bookingData.setDepositpaid(true);
        bookingData.setBookingdates(date);
        bookingData.setAdditionalneeds("Breakfast");

        ExtractableResponse response = CreateBookingHelper.getBookingParams(bookingData);
        int bookingDetails = response.as(СreatedReservationModel.class).getBookingid();
        String bookingId = String.valueOf(bookingDetails);

        assertFalse(bookingId.isEmpty());
    }
}

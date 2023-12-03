package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.CreateBookingHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.ArrayBookingModel;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreationBookingTests {

    @Test
    void createBookingTest() {
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
    }
}

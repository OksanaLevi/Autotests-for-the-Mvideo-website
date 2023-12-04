package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.ChangeBookingHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import herokuapp.booker.tests.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateBookingTests extends TestBase {
    @Test
     public void updateBookingTest() {

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
    }

    @Test
    void partialUpdateBookingTest() {
        BookingBodyModel bookingData = new BookingBodyModel();
        RandomTestData randomTestData = new RandomTestData();

        bookingData.setFirstname(randomTestData.firstname);
        bookingData.setLastname(randomTestData.lastname);

        ChangeBookingHelper.changeBookingPatch(token, bookingId, bookingData);

        assertEquals(randomTestData.firstname, bookingData.getFirstname());
        assertEquals(randomTestData.lastname, bookingData.getLastname());
    }
}
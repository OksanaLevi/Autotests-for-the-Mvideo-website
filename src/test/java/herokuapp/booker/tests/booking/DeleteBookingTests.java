package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.ChangeBookingHelper;
import herokuapp.booker.helpers.FindBookingsHelper;
import herokuapp.booker.tests.TestBase;
import org.junit.jupiter.api.Test;

public class DeleteBookingTests extends TestBase {

    @Test
    void deleteBookingTest() {
        ChangeBookingHelper.deleteBooking(token, bookingId);
        FindBookingsHelper.findRemovedBooking(bookingId);
    }
}

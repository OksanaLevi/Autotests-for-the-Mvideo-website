package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.CreateBookingHelper;
import herokuapp.booker.helpers.FindBookingsHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.ArrayBookingModel;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindBookingTests {

    @Test
    void getBookingIdsTest() {
        ExtractableResponse response = FindBookingsHelper.getArrayBookings();
        ArrayBookingModel[] ids = response.as(ArrayBookingModel[].class);
        int id = ids[0].getBookingid();
        String bookingId = String.valueOf(id);

        assertFalse(bookingId.isEmpty());
    }

    @Test
    void getBookingTest() {
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
    }

    @BeforeEach
    void openUrl() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}

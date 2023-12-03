package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.FindBookingsHelper;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import herokuapp.booker.models.СreatedReservationModel;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindBookingTests {

    @Test
    void getBookingIdsTest() {
        ExtractableResponse response = FindBookingsHelper.getBookings();
        int id = response.as(СreatedReservationModel.class).getBookingid();
        String bookingId = String.valueOf(id);

        assertFalse(bookingId.isEmpty());


    }

    @Test
    void getBookingTest() {
        BookingDatesModel date = new BookingDatesModel();
        date.setCheckin("2018-01-01");

        BookingBodyModel bookingData = new BookingBodyModel();
        bookingData.setFirstname("Josh");
        bookingData.setBookingdates(date);

        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/288")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(BookingBodyModel.class);

        assertEquals("Josh", bookingData.getFirstname());
        assertEquals("2018-01-01", date.getCheckin());
    }

    @BeforeEach
    void openUrl() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}

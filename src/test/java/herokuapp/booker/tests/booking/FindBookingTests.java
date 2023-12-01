package herokuapp.booker.tests.booking;

import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindBookingTests {

    @Test
    void getBookingIdsTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
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
}

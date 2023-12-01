package herokuapp.booker.tests.booking;

import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class CreationBookingTests {

    @Test
    void CreateBookingTest() {

        BookingDatesModel date = new BookingDatesModel();
        date.setCheckin("2023-11-06");
        date.setCheckout("2023-11-15");

        BookingBodyModel bookingData = new BookingBodyModel();
        bookingData.setFirstname("Oleg");
        bookingData.setLastname("Bobrov");
        bookingData.setTotalprice(128);
        bookingData.setDepositpaid(true);
        bookingData.setBookingdates(date);
        bookingData.setAdditionalneeds("Breakfast");


        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .body(bookingData)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}

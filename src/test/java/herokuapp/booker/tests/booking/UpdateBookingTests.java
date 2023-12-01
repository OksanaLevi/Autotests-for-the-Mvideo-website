package herokuapp.booker.tests.booking;

import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.models.BookingDatesModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateBookingTests {

    @Test
    void UpdateBookingTest() {

        BookingDatesModel date = new BookingDatesModel();
        date.setCheckin("2023-11-06");
        date.setCheckout("2023-11-15");

        BookingBodyModel bookingData = new BookingBodyModel();
        bookingData.setFirstname("Olga");
        bookingData.setLastname("Bobrova");
        bookingData.setTotalprice(128);
        bookingData.setDepositpaid(true);
        bookingData.setBookingdates(date);
        bookingData.setAdditionalneeds("Breakfast");

//        LoginResponseModel token = new LoginResponseModel();


        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .cookie("token=0074bc88afc45e1")
                .body(bookingData)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/771")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void PartialUpdateBookingTest() {
        BookingBodyModel bookingData = new BookingBodyModel();
        bookingData.setFirstname("Oksana");
        bookingData.setLastname("Bobrikova");

        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .cookie("token=0074bc88afc45e1")
                .body(bookingData)
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/771")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);

        assertEquals("Oksana", bookingData.getFirstname());
        assertEquals("Bobrikova", bookingData.getLastname());
    }
}

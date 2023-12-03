package herokuapp.booker.tests.booking;

import herokuapp.booker.helpers.ChangeBookingHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.BookingBodyModel;
import herokuapp.booker.tests.TestBase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateBookingTests extends TestBase {
    @Test
     public void updateBookingTest() {

        RandomTestData randomTestData = new RandomTestData();

        ArrayList<Object> list = new ArrayList<>();
        list.add(randomTestData.checkinDate);
        list.add(randomTestData.checkoutDate);
        list.add(randomTestData.firstname);
        list.add(randomTestData.lastname);
        list.add(randomTestData.price);
        list.add(randomTestData.depositPaid);
        list.add(randomTestData.additionalNeeds);

        ChangeBookingHelper.changeBooking(token, bookingId, list);
    }

    @Test
    void partialUpdateBookingTest() {
        BookingBodyModel bookingData = new BookingBodyModel();
        bookingData.setFirstname("Oksana");
        bookingData.setLastname("Bobrikova");

        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .header("Cookie", "token=cbed4c7ee389b9d")
                .body(bookingData)
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/2338")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);

        assertEquals("Oksana", bookingData.getFirstname());
        assertEquals("Bobrikova", bookingData.getLastname());
    }
}

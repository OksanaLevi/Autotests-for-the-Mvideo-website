package herokuapp.booker.helpers;

import herokuapp.booker.models.BookingBodyModel;

import static herokuapp.booker.specs.BookingSpec.bookingDeleteResponseSpec;
import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ChangeBookingHelper {

    public static void changeBookingPut(String token, int id, BookingBodyModel value) {

        given()
                .contentType(JSON)
                .header("Cookie", "token=" + token)
                .body(value)
                .log().body()
                .when()
                .put(String.valueOf(id))
                .then()
                .spec(bookingDeleteResponseSpec)
                .log().status()
                .log().body()
                .extract();
    }

    public static void changeBookingPatch(String token, int id, BookingBodyModel value) {

        given()
                .contentType(JSON)
                .header("Cookie", "token=" + token)
                .when()
                .patch(String.valueOf(id))
                .then()
                .spec(bookingResponseSpec)
                .log().status()
                .log().body()
                .extract();
    }

    public static void deleteBooking(String token, int id) {

        given()
                .contentType(JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete(String.valueOf(id))
                .then()
                .spec(bookingDeleteResponseSpec)
                .extract();
    }
}

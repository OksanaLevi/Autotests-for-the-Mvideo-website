package herokuapp.booker.helpers;

import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.BookingBodyModel;

import static herokuapp.booker.specs.BookingSpec.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ChangeBookingHelper {

    public static void changeBookingPut(String token, int id, BookingBodyModel value) {
        RandomTestData url = new RandomTestData();

        given(bookingRequestSpec)
                .contentType(JSON)
                .header("Cookie", "token=" + token)
                .body(value)
                .log().body()
                .when()
                .put(url.bookingUrl + id)
                .then()
                .spec(bookingResponseSpec)
                .log().status()
                .log().body()
                .extract();
    }

    public static void changeBookingPatch(String token, int id, BookingBodyModel value) {
        RandomTestData url = new RandomTestData();

        given(bookingRequestSpec)
                .contentType(JSON)
                .header("Cookie", "token=" + token)
                .when()
                .patch(url.bookingUrl + id)
                .then()
                .spec(bookingResponseSpec)
                .log().status()
                .log().body()
                .extract();
    }

    public static void deleteBooking(String token, int id) {
        RandomTestData url = new RandomTestData();

        given(bookingRequestSpec)
                .contentType(JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete(url.bookingUrl + id)
                .then()
                .spec(bookingDeleteAndHealthResponseSpec)
                .extract();
    }

    public static void checkHealth() {
        RandomTestData url = new RandomTestData();

        given(bookingRequestSpec)
                .contentType(JSON)
                .when()
                .get(url.healthUrl)
                .then()
                .spec(bookingDeleteAndHealthResponseSpec)
                .extract();
    }
}

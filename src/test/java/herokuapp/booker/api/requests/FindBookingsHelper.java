package herokuapp.booker.api.requests;

import herokuapp.booker.helpers.utils.RandomTestData;
import io.restassured.response.ExtractableResponse;

import static herokuapp.booker.specs.BookingSpec.*;
import static io.restassured.RestAssured.given;

public class FindBookingsHelper {
    public static ExtractableResponse getArrayBookings() {
        RandomTestData url = new RandomTestData();
        ExtractableResponse response = given(bookingRequestSpec)
                .when()
                .get(url.bookingUrl)
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }

    public static ExtractableResponse findBooking(int id) {
        RandomTestData url = new RandomTestData();
        ExtractableResponse response = given(bookingRequestSpec)
                .when()
                .get(url.bookingUrl + id)
                .then()
                .spec(bookingResponseSpec)
                .extract();

        return response;
    }

    public static ExtractableResponse findRemovedBooking(int id) {
        RandomTestData url = new RandomTestData();
        ExtractableResponse response = given(bookingRequestSpec)
                .when()
                .get(url.bookingUrl + id)
                .then()
                .spec(bookingFindRemovedIdSpec)
                .extract();

        return response;
    }
}

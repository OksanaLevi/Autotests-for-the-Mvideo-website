package herokuapp.booker.helpers;

import io.restassured.response.ExtractableResponse;

import java.util.ArrayList;

import static herokuapp.booker.specs.BookingSpec.bookingResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ChangeBookingHelper{

    public static void changeBooking(String token, int id, ArrayList<Object> list) {

        ExtractableResponse response = given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .header("Cookie", "token=" + token)
                .body(list)
                .when()
                .put("/booking/" + id)
                .then()
                .spec(bookingResponseSpec)
                .log().status()
                .log().body()
                .extract();

    }
}

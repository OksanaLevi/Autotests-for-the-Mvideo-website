package herokuapp.booker.tests;

import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.helpers.CreateBookingHelper;
import herokuapp.booker.models.*;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeEach
    void getAuthToken() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");
        authData.setPassword("password123");

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        ExtractableResponse response = AuthHelper.getAuthToken(authData);
        String token = response.as(LoginResponseModel.class).getToken();
    }

    void createBooking() {
        BookingDatesModel date = new BookingDatesModel();
        date.setCheckin("2023-11-30");
        date.setCheckout("2023-12-02");

        BookingBodyModel bookingData = new BookingBodyModel();
        bookingData.setFirstname("Maria");
        bookingData.setLastname("Petrova");
        bookingData.setTotalprice(320);
        bookingData.setDepositpaid(true);
        bookingData.setBookingdates(date);
        bookingData.setAdditionalneeds("Breakfast");

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        ExtractableResponse response = CreateBookingHelper.addBooking(bookingData);
        int bookingId = response.as(ArrayBookingModel.class).getBookingid();
    }
}

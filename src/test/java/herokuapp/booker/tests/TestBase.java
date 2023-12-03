package herokuapp.booker.tests;

import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.helpers.CreateBookingHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.*;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    String token;
    int bookingId;

    @BeforeAll
        public void getAuthToken () {
            LoginBodyModel authData = new LoginBodyModel();
            authData.setUsername("admin");
            authData.setPassword("password123");

            RestAssured.baseURI = "https://restful-booker.herokuapp.com";
            ExtractableResponse responseToken = AuthHelper.getAuthToken(authData);
            token = responseToken.as(LoginResponseModel.class).getToken();
//            UpdateBookingTests.updateBookingTest(token);
        }

    @BeforeEach
        public void createBooking() {
            RandomTestData randomTestData = new RandomTestData();
            BookingDatesModel date = new BookingDatesModel();
            BookingBodyModel bookingData = new BookingBodyModel();

            date.setCheckin(randomTestData.checkinDate);
            date.setCheckout(randomTestData.checkoutDate);

            bookingData.setFirstname(randomTestData.firstname);
            bookingData.setLastname(randomTestData.lastname);
            bookingData.setTotalprice(randomTestData.price);
            bookingData.setDepositpaid(randomTestData.depositPaid);
            bookingData.setBookingdates(date);
            bookingData.setAdditionalneeds(randomTestData.additionalNeeds);

            ExtractableResponse responseId = CreateBookingHelper.addBooking(bookingData);
            bookingId = responseId.as(ArrayBookingModel.class).getBookingid();
//            UpdateBookingTests.updateBookingTest(bookingId);
        }
    }

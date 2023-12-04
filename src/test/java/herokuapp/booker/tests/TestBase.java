package herokuapp.booker.tests;

import herokuapp.booker.config.AuthConfig;
import herokuapp.booker.helpers.AuthHelper;
import herokuapp.booker.helpers.CreateBookingHelper;
import herokuapp.booker.helpers.utils.RandomTestData;
import herokuapp.booker.models.*;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static io.qameta.allure.Allure.step;

public class TestBase {
    protected static String token;
    protected static int bookingId;

    @BeforeAll
        public static void getAuthToken () {
        step("Создание нового токена аутентификации", () -> {
            AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
            LoginBodyModel authData = new LoginBodyModel();
            authData.setUsername(config.username());
            authData.setPassword(config.password());

            RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking/";
            ExtractableResponse responseToken = AuthHelper.getAuthToken(authData);
            token = responseToken.as(LoginResponseModel.class).getToken();
        });
    }

    @BeforeEach
        public void createBooking() {
        step("Создание нового бронирования для последующего использования в тесте", () -> {
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
        });
        }
    }

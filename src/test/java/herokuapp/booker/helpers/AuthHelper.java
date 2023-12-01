package herokuapp.booker.helpers;

import herokuapp.booker.models.LoginBodyModel;
import herokuapp.booker.models.LoginResponseModel;

import static herokuapp.booker.specs.AuthSpec.authRequestSpec;
import static herokuapp.booker.specs.AuthSpec.authResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthHelper {

//    public void getAuthToken() {
//        LoginBodyModel authData = new LoginBodyModel();
//        authData.setUsername("admin");
//        authData.setPassword("password123");
//
//        LoginResponseModel token = given(authRequestSpec)
//                .body(authData)
//                .when()
//                .post("https://restful-booker.herokuapp.co/auth")
//                .then()
//                .spec(authResponseSpec)
//                .spec(authResponseSpec)
//                .extract().as(LoginResponseModel.class);
//
//        return;
//    }

    public static String getAuthToken() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUsername("admin");
        authData.setPassword("password123");

        String token = given(authRequestSpec)
                .body(authData)
                .when()
                .post("https://restful-booker.herokuapp.co/auth")
                .then()
                .spec(authResponseSpec)
                .extract().as(LoginResponseModel.class)
                .getToken();

        return token;
    }
}

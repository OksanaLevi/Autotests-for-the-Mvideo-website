package herokuapp.booker.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void openUrl() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
    }
}

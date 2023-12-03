package herokuapp.booker.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static herokuapp.booker.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class AuthSpec {
    public static RequestSpecification authRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .contentType(JSON);
//            .baseUri("https://restful-booker.herokuapp.com");

    public static ResponseSpecification authResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();
}

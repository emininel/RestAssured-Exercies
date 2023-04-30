package com.hotelreservation;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingTests extends BaseTests {

    @Test
    public void getBookingWithId(){

        Response response =
                given()
                        .when()
                        .get("https://restful-booker.herokuapp.com/booking/"+createBookingAndGetId());
        response.prettyPrint();

        Assertions.assertEquals("Emin",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Inel",response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(2000,(Integer) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("depositpaid"));
        Assertions.assertEquals("Snacks",response.jsonPath().getJsonObject("additionalneeds"));

    }



}

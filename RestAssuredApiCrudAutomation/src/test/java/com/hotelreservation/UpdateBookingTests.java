package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTests{

    @Test
    public void updateTest(){

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("Cookie","token="+createToken())
                        .body(bookingObject("John","Dorie",5000,true))
                        .when()
                        .put("https://restful-booker.herokuapp.com/booking/"+createBookingAndGetId());
        response.prettyPrint();

        Assertions.assertEquals("John",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Dorie",response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(5000,(Integer) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("depositpaid"));


    }

}

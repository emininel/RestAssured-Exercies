package com.hotelreservation.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTests{

//    curl -X PUT \
//    https://restful-booker.herokuapp.com/booking/1 \
//            -H 'Content-Type: application/json' \
//            -H 'Accept: application/json' \
//            -H 'Cookie: token=abc123' \
//            -d '{
//            "firstname" : "James",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//    },
//            "additionalneeds" : "Breakfast"
//}'
    @Test
    public void updateTest(){

        Response response =
                given(spec)
                        .contentType(ContentType.JSON)
                        .header("Cookie","token="+createToken())
                        .body(bookingObject("John","Dorie",5000,true))
                        .when()
                        .put("/booking/"+createBookingAndGetId());


        Assertions.assertEquals("John",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Dorie",response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(5000,(Integer) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("depositpaid"));


    }

}

package com.hotelreservation;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingIdTests extends BaseTests{

    @Test
    public void getAllBooking (){
    Response response =
            given()
                    .when()
                    .get("https://restful-booker.herokuapp.com/booking");
    response.prettyPrint();

        response
                .then()
                .statusCode(200);
    }


}

package com.hotelreservation.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingIdTests extends BaseTests{

    @Test
    public void getAllBooking (){

            given(spec)
                    .when()
                    .get("/booking")
                .then()
                .statusCode(200);
    }


}

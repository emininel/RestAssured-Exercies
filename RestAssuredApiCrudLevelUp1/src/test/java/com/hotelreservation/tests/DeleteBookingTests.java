package com.hotelreservation.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTests{

    @Test
    public void deleteBooking(){

        Response response =
                given(spec)
                        .contentType(ContentType.JSON)
                        .header("Cookie","token="+createToken())
                        .when()
                        .delete("/booking/"+createBookingAndGetId());

        response
                .then()
                .statusCode(201);

    }
}

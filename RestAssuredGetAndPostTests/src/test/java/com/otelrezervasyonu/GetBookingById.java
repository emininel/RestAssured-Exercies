package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingById extends BaseTest {

    @Test
    void GetById(){

        Response bookingCreator = createBooking();
        int bookingId = bookingCreator.jsonPath().getJsonObject("bookingid");

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/"+bookingId)
                .then()
                .log().all()
                .statusCode(200);



    }

}

package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends BaseTest {

    @Test
    void bookingCreate(){

        Response response = createBooking();

        Assertions.assertEquals("Emin",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Ben",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(2500,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("booking.depositpaid"));
        Assertions.assertEquals("2024-05-02",response.jsonPath().getJsonObject("booking.bookingdates.checkin"));
        Assertions.assertEquals("2025-05-02",response.jsonPath().getJsonObject("booking.bookingdates.checkout"));
        Assertions.assertEquals("Whiskey",response.jsonPath().getJsonObject("booking.additionalneeds"));



    }
}

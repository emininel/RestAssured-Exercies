package com.hotelreservation;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends BaseTests {
    @Test
    public void createBooking() {
        Response response = createsBooking();

        Assertions.assertEquals("Emin", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Inel", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(2000, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
    }
}

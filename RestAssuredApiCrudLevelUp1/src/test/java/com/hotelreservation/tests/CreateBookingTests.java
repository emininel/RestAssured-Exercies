package com.hotelreservation.tests;

import com.hotelreservation.models.BookingDates;
import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTests {
    @Test
    public void createBooking() {
        Response response = createsBooking();

        Assertions.assertEquals("Emin", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Inel", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(2000, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
    }

    @Test
    public void createBookingWithPojo(){


        BookingDates bookingDates = new BookingDates("2023-04-12","2023-04-15");
        Booking booking = new Booking("Emin","Inel",5000,true,bookingDates,"Ship");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse);

        Assertions.assertEquals("Emin",bookingResponse.getBookingObject().getFirstname());
        Assertions.assertEquals("Inel",bookingResponse.getBookingObject().getLastname());
        Assertions.assertEquals(5000,bookingResponse.getBookingObject().getTotalprice());
        Assertions.assertEquals(true,bookingResponse.getBookingObject().isDepositpaid());
        Assertions.assertEquals("2023-04-12",bookingResponse.getBookingObject().getBookingdates().getCheckin());
        Assertions.assertEquals("2023-04-15",bookingResponse.getBookingObject().getBookingdates().getCheckout());
        Assertions.assertEquals("Ship",bookingResponse.getBookingObject().getAdditionalneeds());




    }
}

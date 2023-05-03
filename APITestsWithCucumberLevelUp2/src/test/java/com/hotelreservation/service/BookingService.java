package com.hotelreservation.service;

import com.hotelreservation.models.Auth;
import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingDates;
import com.hotelreservation.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingService extends BaseTest {

    public String createToken() {
        Auth auth = new Auth("admin", "password123");

        Response response =
                given(spec)
                        .contentType(ContentType.JSON)
                        .body(auth)
                        .when()
                        .post("/auth");
        response
                .then()
                .statusCode(200);


        return response.jsonPath().getJsonObject("token");
    }

    public BookingResponse createReservation() {

        BookingDates bookingDates = new BookingDates("2023-04-05", "2023-04-15");
        Booking booking = new Booking("Emin", "Inel", 2000, true, bookingDates, "City View");

        Response response =
                given(spec)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(booking)
                        .post("/booking");
        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }

    public void deleteBooking(String token, int bookingid) {
        Response response =
                given(spec)
                        .contentType(ContentType.JSON)
                        .header("Cookie", "token=" + token)
                        .when()
                        .delete("/booking/" + bookingid);
        response
                .then()
                .statusCode(201);

    }


}

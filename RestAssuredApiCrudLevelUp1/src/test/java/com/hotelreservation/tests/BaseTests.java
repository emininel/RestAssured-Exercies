package com.hotelreservation.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTests {

    RequestSpecification spec;

    @BeforeEach
    public void specificationSetup(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter()))
                .build();
    }

    protected String createToken() {
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");

        Response response =
                given(spec)
                        .contentType(ContentType.JSON)
                        .body(body.toString())
                        .when()
                        .post("/auth");

        return response.jsonPath().getJsonObject("token");
    }

    protected String bookingObject(String firstName, String lastName, int totalPrice, boolean depositPaid) {

        JSONObject body = new JSONObject();
        body.put("firstname", firstName);
        body.put("lastname", lastName);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2023-04-03");
        bookingDates.put("checkout", "2023-04-07");

        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Snacks");

        return body.toString();
    }

    protected Response createsBooking() {
        Response response =
                given(spec)
                        .contentType(ContentType.JSON)
                        .body(bookingObject("Emin", "Inel", 2000, true))
                        .when()
                        .post("/booking");

        response
                .then()
                .statusCode(200);
        return response;
    }

    protected int createBookingAndGetId() {
        Response newBooking = createsBooking();
        return newBooking.jsonPath().getJsonObject("bookingid");
    }
}

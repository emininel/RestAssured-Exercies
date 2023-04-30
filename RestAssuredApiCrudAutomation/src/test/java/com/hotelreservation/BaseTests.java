package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTests {

    protected String createToken() {
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(body.toString())
                        .when()
                        .post("https://restful-booker.herokuapp.com/auth");
        response.prettyPrint();
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
                given()
                        .contentType(ContentType.JSON)
                        .body(bookingObject("Emin", "Inel", 2000, true))
                        .when()
                        .post("https://restful-booker.herokuapp.com/booking");
        response.prettyPrint();
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

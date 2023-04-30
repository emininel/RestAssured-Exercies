package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected Response createBooking(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingJson())
                .post("https://restful-booker.herokuapp.com/booking");

        response
                .then()
                .statusCode(200);

        response.prettyPrint();
        return response;
    }

    protected String bookingJson(){
        JSONObject body = new JSONObject();
        body.put("firstname","Emin");
        body.put("lastname","Ben");
        body.put("totalprice",2500);
        body.put("depositpaid",true);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2024-05-02");
        bookingDates.put("checkout","2025-05-02");
        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Whiskey");

        return body.toString();

    }


}

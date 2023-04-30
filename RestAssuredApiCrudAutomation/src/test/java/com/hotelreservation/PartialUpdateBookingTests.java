package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTests extends BaseTests{

    @Test
    public void PartialUpdate(){
        JSONObject partOfObject = new JSONObject();
        partOfObject.put("firstname","John");
        partOfObject.put("lastname","Dorie");
    Response response = given()
            .contentType(ContentType.JSON)
            .header("Cookie","token="+createToken())
            .body(partOfObject.toString())
            .when()
            .patch(" https://restful-booker.herokuapp.com/booking/"+createBookingAndGetId());
    response.prettyPrint();

        Assertions.assertEquals("John",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Dorie",response.jsonPath().getJsonObject("lastname"));

    }
}

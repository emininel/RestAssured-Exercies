package com.hotelreservation.tests;

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

        Response response =
                given(spec)
            .contentType(ContentType.JSON)
            .header("Cookie","token="+createToken())
            .body(partOfObject.toString())
            .when()
            .patch(" /booking/"+createBookingAndGetId());

        Assertions.assertEquals("John",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Dorie",response.jsonPath().getJsonObject("lastname"));

    }
}

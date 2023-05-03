package com.hotelreservation.steps;

import com.hotelreservation.models.Auth;
import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingDates;
import com.hotelreservation.models.BookingResponse;
import com.hotelreservation.service.BookingService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {

    BookingService bookingService;
    String auth;
    BookingResponse bookingResponse;

    @Given("User tries a new hotel reservation")
    public void requestStart(){
        bookingService = new BookingService();
    }
    @Given("User gives the information for the reservation")
    public void createAuth(){
        auth = bookingService.createToken();
    }

    @When("User creates a hotel reservation")
    public void createBooking(){
        bookingResponse = bookingService.createReservation();
    }

    @Then("Reservation has been succesfull")
    public void reservationAssertions(){
        Assertions.assertEquals("Emin",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Inel",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(2000,bookingResponse.getBooking().getTotalprice());
        Assertions.assertEquals(true,bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("2023-04-05",bookingResponse.getBooking().getBookingdates().getCheckin());
        Assertions.assertEquals("2023-04-15",bookingResponse.getBooking().getBookingdates().getCheckout());
        Assertions.assertEquals("City View",bookingResponse.getBooking().getAdditionalneeds());
    }

    @Then("User deletes created reservation")
    public void deleteReservation(){
        bookingService.deleteBooking(bookingService.createToken(), bookingResponse.getBookingid());

    }


}

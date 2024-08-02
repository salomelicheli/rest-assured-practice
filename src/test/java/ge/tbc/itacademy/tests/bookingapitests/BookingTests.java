package ge.tbc.itacademy.tests.bookingapitests;

import ge.tbc.itacademy.data.steps.bookingapisteps.BookingSteps;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.itacademy.data.constants.BookingDetails.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BookingTests {
    BookingSteps steps;

    @BeforeClass
    public void beforeClass() {
        steps = new BookingSteps();
    }

    @Test
    public void bookingUpdate() {
        steps.fillInBookingDates(CHECKIN_DATE, CHECKOUT_DATE)
                .fillInBookingDetails(FIRST_NAME, LAST_NAME, TOTAL_PRICE,
                        true, ADDITIONAL_NEEDS);
        JSONObject jsonBody = steps.getBookingJson();
        Response response = steps.updateBookingDetails(BOOKING_ID, jsonBody);
        int code = response.then().extract().statusCode();
        assertThat(code, equalTo(200));
        steps.logIfStatusCodeIs200(response, code);
    }
}

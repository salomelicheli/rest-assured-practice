package ge.tbc.itacademy.data.steps.bookingapisteps;

import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BookingSteps {
    private JSONObject bookingDetails;
    private JSONObject bookingDates;
    private final RequestSpecification requestSpec;

    public BookingSteps() {
        this.requestSpec = RequestSpecs.bookingRequestSpec();
    }

    public void fillInBookingDetails(String firstName, String lastName, int price, boolean deposit, String needs){
        bookingDetails = new JSONObject();
        bookingDetails.put("firstname", firstName);
        bookingDetails.put("lastname", lastName);
        bookingDetails.put("totalprice", price);
        bookingDetails.put("depositpaid", deposit);
        bookingDetails.put("bookingdates", bookingDates);
        bookingDetails.put("additionalneeds", needs);
    }
    public BookingSteps fillInBookingDates(String checkinDate, String checkoutDate){
        bookingDates = new JSONObject();
        bookingDates.put("checkin", checkinDate);
        bookingDates.put("checkout", checkoutDate);
        return this;
    }
    public void logIfStatusCodeIs200(Response response, int statusCode) {
        if(statusCode == 200){
            response.then().log().all();
        }
    }
    public Response updateBookingDetails(int id, JSONObject jsonBody){
        return given()
                .spec(requestSpec)
                .body(jsonBody.toString())
                .when()
                .put("/booking/{id}", id);
    }
    public JSONObject getBookingJson() {
        return bookingDetails;
    }
}

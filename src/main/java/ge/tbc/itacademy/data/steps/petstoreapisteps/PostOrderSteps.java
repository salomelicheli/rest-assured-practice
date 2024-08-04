package ge.tbc.itacademy.data.steps.petstoreapisteps;

import ge.tbc.itacademy.data.models.requests.PetOrderRequest;
import ge.tbc.itacademy.data.models.responses.PetOrderResponse;
import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostOrderSteps {
    private final RequestSpecification requestSpec;
    private PetOrderRequest petRequest;
    private PetOrderResponse petResponse;
    public PostOrderSteps() {
        requestSpec = RequestSpecs.getPetStoreRequest();
    }

    @Step("creating body request")
    public PostOrderSteps creatingRequestBody(){
        petRequest = new PetOrderRequest()
                .id(10).petId(102).quantity(1)
                .shipDate("2024-05-10T22:31:13.095Z")
                .status("approved")
                .complete(true);
        return this;
    }

    @Step("Posting an order request")
    public PostOrderSteps postingRequest(){
        petResponse = given()
                .spec(requestSpec)
                .body(petRequest)
                .post("store/order")
                .then().extract().as(PetOrderResponse.class);
        return this;
    }

    @Step("Step to validate order id")
    public PostOrderSteps validateOrderId(){
        assertThat(petRequest.id(), equalTo(petResponse.id()));
        return this;
    }

    @Step("Step to validate pet id")
    public PostOrderSteps petIdValidation(){
        assertThat(petRequest.petId(), equalTo(petRequest.petId()));
        return this;
    }

    @Step("Step to validate status")
    public void statusValidation(){
        hasProperty("status", notNullValue());
    }
}

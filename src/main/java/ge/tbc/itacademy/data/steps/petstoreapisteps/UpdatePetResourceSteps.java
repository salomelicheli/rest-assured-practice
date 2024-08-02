package ge.tbc.itacademy.data.steps.petstoreapisteps;

import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdatePetResourceSteps extends CommonElements{
    private final RequestSpecification requestSpec;
    public UpdatePetResourceSteps() {
        this.requestSpec = RequestSpecs.petStoreRequest();
    }

    public Response updatePetInfo(String updatedName, String updatedStatus){
        return given()
                .spec(requestSpec)
                .contentType(ContentType.URLENC)
                .formParam("name", updatedName)
                .formParam("status", updatedStatus)
                .when()
                .post("/pet/{petId}", petDetails.getInt("id"));
    }

    public UpdatePetResourceSteps validateNameUpdate(Response response, String updatedName){
        response.then().log().all().assertThat().body("name", equalTo(updatedName));
        return this;
    }

    public void validateStatusUpdate(Response response, String updatedStatus){
        response.then().assertThat().body("status", equalTo(updatedStatus));
    }
}

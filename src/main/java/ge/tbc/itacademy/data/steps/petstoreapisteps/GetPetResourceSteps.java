package ge.tbc.itacademy.data.steps.petstoreapisteps;

import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetPetResourceSteps extends CommonElements{
    private Map<String, Object> pet;
    private final RequestSpecification requestSpec;
    public GetPetResourceSteps() {
        this.requestSpec = RequestSpecs.petStoreRequest();
    }

    public Response getAPet(){
        return given()
                .spec(requestSpec)
                .queryParam("status", petDetails.getString("status"))
                .when()
                .get("pet/findByStatus");
    }

    public Response getAPetAfterUpdate(){
        return given()
                .spec(requestSpec)
                .when()
                .get("/pet/{petId}", petDetails.getInt("id"));
    }

    public GetPetResourceSteps extractAPet(Response response){
        pet = response
                .jsonPath()
                .getMap("find { it.id == " + petDetails.getInt("id")+ "}");
        System.out.println(pet);
        return this;
    }

    public GetPetResourceSteps validatePetId(){
        assertThat(petDetails.getInt("id"), equalTo((int)pet.get("id")));
        return this;
    }

    public GetPetResourceSteps validatePetName(){
        assertThat(petDetails.getString("name"), equalTo((String)pet.get("name")));
        return this;
    }
    public void validatePetStatus(){
        assertThat(petDetails.getString("status"), equalTo((String)pet.get("status")));
    }

    public GetPetResourceSteps checkIfTheIdIsPresent(Response response){
        response.then().assertThat().body("id", hasItem(petDetails.getInt("id")));
        return this;
    }
}

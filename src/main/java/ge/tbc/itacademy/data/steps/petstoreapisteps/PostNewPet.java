package ge.tbc.itacademy.data.steps.petstoreapisteps;

import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class PostNewPet extends CommonElements{
    private JSONObject petTags;
    private JSONObject category;
    private final RequestSpecification requestSpec;
    public PostNewPet() {
        this.requestSpec = RequestSpecs.petStoreRequest();
    }

    public PostNewPet fillInCategories(){
        category = new JSONObject();
        category.put("id", faker.number().randomNumber())
                .put("name", faker.animal().name());
        return this;
    }

    public PostNewPet fillInPetTags(){
        petTags = new JSONObject();
        petTags.put("id", faker.number().randomNumber())
                .put("name", faker.animal().name());
        return this;
    }

    public void petJsonBody(){
        petDetails = new JSONObject();
        petDetails.put("id", faker.number().randomDigitNotZero())
                .put("category", category)
                .put("name", faker.animal().name())
                .put("photoUrls", Collections.singletonList(faker.internet().url()))
                .put("tags", Collections.singletonList(petTags))
                .put("status", "available");
    }

    public Response addPet(){
        return given()
                .spec(requestSpec)
                .body(petDetails.toString())
                .when()
                .put("/pet");
    }

    public void validateThatTheActionWasSuccessful(Response response) {
        response.then().spec(RequestSpecs.buildAResponse());
    }
}

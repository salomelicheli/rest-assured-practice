package ge.tbc.itacademy.data.steps.fakerapisteps;

import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static ge.tbc.itacademy.data.constants.Constants.FAKER_API_ENDPOINT;
import static io.restassured.RestAssured.given;

public class ValidateSchemaSteps {
    private final RequestSpecification requestSpec;
    public ValidateSchemaSteps() {
        requestSpec= RequestSpecs.fakerApiRequest();
    }
    @Step("get credit card body to validate schema")
    public Response getCreditCardBody(){
        return given().spec(requestSpec)
                .queryParam("_quantity", "2")
                .when()
                .get(FAKER_API_ENDPOINT);
    }
    @Step("validating schema")
    public void validateSchema(Response response){
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/creditsschema.json")));
    }
}

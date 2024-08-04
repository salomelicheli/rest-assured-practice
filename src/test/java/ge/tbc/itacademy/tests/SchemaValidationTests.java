package ge.tbc.itacademy.tests;

import ge.tbc.itacademy.data.steps.fakerapisteps.ValidateSchemaSteps;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SchemaValidationTests {
    ValidateSchemaSteps steps;
    @BeforeClass
    public void beforeClass() {
        steps = new ValidateSchemaSteps();
    }

    @Test
    public void ValidateJsonSchema() {
        Response response =
                steps.getCreditCardBody();
                  steps.validateSchema(response);
    }
}

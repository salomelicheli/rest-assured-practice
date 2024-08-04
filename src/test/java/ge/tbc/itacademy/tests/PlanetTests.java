package ge.tbc.itacademy.tests;

import ge.tbc.itacademy.data.steps.planetsapisteps.PlanetSteps;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlanetTests {
    PlanetSteps steps;
    @BeforeClass
    public void beforeClass() {
        steps = new PlanetSteps();
    }

    @Test
    public void TestToFindThreeRecentObjects() {
        Response response = steps.goToEndpoint();
        steps.extractResponse(response)
                .getThreeMostRecentPlanets()
                .validateFiveFields();
    }

    @Test(dependsOnMethods = {"TestToFindThreeRecentObjects"}, description = "redirecting to the first url and validating fields")
    public void RedirectToPeopleApi() {
        steps.getTopRotationPeriod();
        Response personResponse = steps.redirectToResidentsUrl();
        steps.getPersonObject(personResponse)
                .validateNameField()
                .validateHeight();
    }
}

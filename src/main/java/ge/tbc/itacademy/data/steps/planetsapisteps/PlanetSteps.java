package ge.tbc.itacademy.data.steps.planetsapisteps;

import ge.tbc.itacademy.data.models.responses.Person;
import ge.tbc.itacademy.data.models.responses.Planet;
import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

import static ge.tbc.itacademy.data.constants.Constants.PLANET_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PlanetSteps {
    private final RequestSpecification requestSpec;
    private List<Planet> planets;
    private List<Planet> threeRecentPlanets;
    private Person person;
    public PlanetSteps() {
        requestSpec = RequestSpecs.getRequestSpecForPlanets();
    }
    @Step("directing to planets endpoint")
    public Response goToEndpoint(){
        return given().spec(requestSpec).log().all()
                .queryParam("format", "json")
                .when()
                .get(PLANET_ENDPOINT);
    }
    @Step("extracting response body as list")
    public PlanetSteps extractResponse(Response response){
        planets = new ArrayList<>(response.jsonPath().getList("results", Planet.class));
        return this;
    }
    @Step("getting three recent planets according to created field")
    public PlanetSteps getThreeMostRecentPlanets(){
        planets.sort((p1, p2) -> p2.created().compareTo(p1.created()));
        threeRecentPlanets = planets.subList(0,3);
        return this;
    }

    @Step("validating five fields of each recent planet")
    public void validateFiveFields(){
        for (Planet planet : threeRecentPlanets) {
            assertThat(planet.name(), not(emptyOrNullString()));
            assertThat(planet.climate(),notNullValue());
            hasProperty("rotation_period", matchesPattern("\\d+"));
            hasProperty("surface_water", matchesPattern("\\d+"));
            assertThat(planet.diameter(), notNullValue());
        }
    }

    @Step("getting top recent planet according to rotation period field")
    public void getTopRotationPeriod(){
        planets.sort((p1, p2) -> p2.rotationPeriod());
    }
    @Step("redirecting to url")
    public Response redirectToResidentsUrl(){
        String url = planets.get(0).residents().get(0);
        return RestAssured.get(url);
    }
    @Step("extracting person object")
    public PlanetSteps getPersonObject(Response response){
        person = response.then().extract().body().as(Person.class);
        return this;
    }
    @Step("validating name field")
    public PlanetSteps validateNameField(){
        assertThat(person.getName(), equalTo("Boba Fett"));
        return this;
    }
    @Step("validating person height")
    public void validateHeight(){
        hasProperty("height", matchesPattern("\\d+"));
    }
}

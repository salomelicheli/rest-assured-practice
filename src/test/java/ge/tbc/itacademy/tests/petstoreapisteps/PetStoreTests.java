package ge.tbc.itacademy.tests.petstoreapisteps;

import ge.tbc.itacademy.data.steps.petstoreapisteps.GetPetResourceSteps;
import ge.tbc.itacademy.data.steps.petstoreapisteps.PostNewPet;
import ge.tbc.itacademy.data.steps.petstoreapisteps.PostOrderSteps;
import ge.tbc.itacademy.data.steps.petstoreapisteps.UpdatePetResourceSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.itacademy.data.constants.Constants.PET_NAME;
import static ge.tbc.itacademy.data.constants.Constants.UPDATED_STATUS;

@Epic("Pet Store Tests")
@Feature("Post Request Tests")
public class PetStoreTests {
    PostNewPet steps;
    GetPetResourceSteps getPetSteps;
    UpdatePetResourceSteps update;
    PostOrderSteps orderSteps;
    @BeforeClass
    public void beforeClass() {
        steps = new PostNewPet();
        getPetSteps = new GetPetResourceSteps();
        update = new UpdatePetResourceSteps();
        orderSteps = new PostOrderSteps();
    }

    @Test
    public void AddAPet() {
        steps.fillInCategories()
                .fillInPetTags()
                .petJsonBody();
        Response response = steps.addPet();
        steps.validateThatTheActionWasSuccessful(response);
    }

    @Test(dependsOnMethods = "AddAPet")
    public void getAddedPet() {
        Response response = getPetSteps.getAPet();
        getPetSteps.checkIfTheIdIsPresent(response)
                .extractAPet(response)
                .validatePetId()
                .validatePetName()
                .validatePetStatus();
    }

    @Test(dependsOnMethods = {"AddAPet", "getAddedPet"})
    public void updatePetInfo() {
        Response response = update.updatePetInfo(PET_NAME, UPDATED_STATUS);
        response.then().log().body();
        Response getResponse = getPetSteps.getAPetAfterUpdate();
        update.validateNameUpdate(getResponse, PET_NAME)
                .validateStatusUpdate(getResponse, UPDATED_STATUS);
    }

    @Test(description = "posting a new pet order")
    public void PostOrderTest() {
        orderSteps.creatingRequestBody()
                .postingRequest()
                .validateOrderId()
                .petIdValidation()
                .statusValidation();
    }


}

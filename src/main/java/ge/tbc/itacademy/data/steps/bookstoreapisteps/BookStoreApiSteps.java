package ge.tbc.itacademy.data.steps.bookstoreapisteps;

import ge.tbc.itacademy.data.requestspecifications.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookStoreApiSteps {
    private final RequestSpecification requestSpec;
    public BookStoreApiSteps() {
        this.requestSpec = RequestSpecs.bookStoreRequest();
    }

    public Response getResponse(){
        return given()
                .spec(requestSpec)
                .when()
                .get("/Books");
    }

    public BookStoreApiSteps validatingBookPages(Response response){
        response.then()
                .spec(RequestSpecs.buildAResponse())
                .assertThat()
                .body("books.pages", everyItem(lessThan(1000)));
        return this;
    }

    public void validateFirstTwoAuthors(Response response, String firstAuthor, String secondAuthor){
        response.then().body("books.author[0,1]", hasItems(firstAuthor, secondAuthor));
    }
}

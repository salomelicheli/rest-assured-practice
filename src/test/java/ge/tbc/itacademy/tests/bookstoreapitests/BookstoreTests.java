package ge.tbc.itacademy.tests.bookstoreapitests;

import ge.tbc.itacademy.data.steps.bookstoreapisteps.BookStoreApiSteps;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookstoreTests {
    BookStoreApiSteps steps;
    @BeforeClass
    public void beforeClass() {
        steps = new BookStoreApiSteps();
    }

    @Test
    public void validateBookPagesAndAuthors() {
       Response response = steps.getResponse();
                steps.validatingBookPages(response)
                .validateFirstTwoAuthors(response,
                        "Richard E. Silverman", "Addy Osmani");
                response.then().log().body();
    }
}

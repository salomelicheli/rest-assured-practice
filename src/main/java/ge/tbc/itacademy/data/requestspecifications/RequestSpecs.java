package ge.tbc.itacademy.data.requestspecifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static ge.tbc.itacademy.data.constants.Constants.*;
import static io.restassured.RestAssured.given;

public class RequestSpecs {
    public static RequestSpecification bookingRequestSpec(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setContentType(ContentType.JSON)
                .build();
        return given(requestSpecification).header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    public static RequestSpecification bookStoreRequest(){
        return new RequestSpecBuilder()
                .setBaseUri("https://bookstore.toolsqa.com/BookStore/v1")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification petStoreRequest(){
        return new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getRequestSpecForPlanets(){
        return new RequestSpecBuilder()
                .setBaseUri(PLANETS_BASE_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

    public static RequestSpecification getPetStoreRequest(){
        return new RequestSpecBuilder()
                .setBaseUri(PET_STORE_BASE_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

    public static RequestSpecification fakerApiRequest(){
        return new RequestSpecBuilder()
                .setBaseUri(FAKER_API_BASE_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

    public static ResponseSpecification buildAResponse(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}

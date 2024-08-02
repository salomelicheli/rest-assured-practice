package ge.tbc.itacademy.data.requestspecifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

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

    public static ResponseSpecification buildAResponse(){
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        return resSpec;
    }
}

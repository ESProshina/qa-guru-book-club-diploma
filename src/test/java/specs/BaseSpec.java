package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;

public class BaseSpec {

    public static final RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://book-club.qa.guru")
            .setBasePath("/api/v1")
            .setContentType(JSON)
            .addFilter(new AllureRestAssured())
            .build();

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(statusCode)
                .build();
    }
}
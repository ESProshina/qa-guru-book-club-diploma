package specs.user;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class UserSpec {

    public static final ResponseSpecification userResponse200Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();
}
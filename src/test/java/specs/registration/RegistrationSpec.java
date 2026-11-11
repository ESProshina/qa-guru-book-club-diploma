package specs.registration;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class RegistrationSpec {

    public static final ResponseSpecification registrationResponse201Spec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
}
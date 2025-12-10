package com.sitenetsoft.olingo4.quarkus.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Olingo4QuarkusResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/olingo4-quarkus")
                .then()
                .statusCode(200)
                .body(is("Hello olingo4-quarkus"));
    }
}

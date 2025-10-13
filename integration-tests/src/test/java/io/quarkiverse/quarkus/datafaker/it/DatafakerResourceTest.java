package io.quarkiverse.quarkus.datafaker.it;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DatafakerResourceTest {

    @Test
    public void testHelloEndpoint() {
        String payload = given()
                .when().get("/fake-data")
                .then()
                .statusCode(200)
                .extract().body().asString();
        System.out.println(payload);
        Assertions.assertFalse(payload.isBlank());
        Assertions.assertNotEquals("null", payload);
    }
}

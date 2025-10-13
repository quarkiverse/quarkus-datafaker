package io.quarkiverse.quarkus.datafaker.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import net.datafaker.Faker;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

import static io.restassured.RestAssured.given;

public class DatafakerTest {

    @Path("/fake-data")
    static class MyEndpoint {

        @GET
        @Produces("text/plain")
        public String fakeData() {
            Faker faker = new Faker();
            return faker.superMario().characters();
        }
    }

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class).addClass(MyEndpoint.class));

    @Test
    void check() {
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

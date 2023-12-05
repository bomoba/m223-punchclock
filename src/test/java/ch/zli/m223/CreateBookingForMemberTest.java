package ch.zli.m223;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingForMemberTest {

    private static final String API_ROOT = "http://localhost:8080/api";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = API_ROOT;
    }

    @Test
    public void testCreateBookingForMember() {
        String token = given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"memberUsername\", \"password\":\"memberPassword\"}")
        .when()
            .post("/auth/login")
        .then()
            .extract()
            .path("token");

        given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body("{\"date\":\"2023-12-31T12:00:00Z\", \"status\":\"Pending\"}")
        .when()
            .post("/bookings")
        .then()
            .statusCode(201)
            .body("date", equalTo("2023-12-31T12:00:00Z"))
            .body("status", equalTo("Pending"));
    }
}

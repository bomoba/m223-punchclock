package ch.zli.m223;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RegistrierungAuthentifizierungTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080; 
    }

    @Test
    void testRegistrierung() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\":\"neuuser@example.com\", \"password\":\"neuesPasswort\"}") 
        .when()
            .post("/members/register")
        .then()
            .statusCode(201);
    }

    @Test
    void testAuthentifizierung() {
        String registrierungBody = "{\"email\":\"neuuser@example.com\", \"password\":\"neuesPasswort\"}";
        given()
            .contentType(ContentType.JSON)
            .body(registrierungBody)
            .post("/members/register");

        given()
            .contentType(ContentType.JSON)
            .body(registrierungBody)
        .when()
            .post("/members/login")
        .then()
            .statusCode(200)
            .body("token", not(emptyString())); 
    }
}

package ch.zli.m223;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AdminCanRetrieveAllBookingsTest {

    private static final String API_ROOT = "http://localhost:8080/api";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = API_ROOT;
    }

    @Test
    void testAdminCanRetrieveAllBookings() {
        String token = obtainAdminToken();

        given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .when()
            .get("/admin/bookings")
            .then()
            .statusCode(200)
            .body("", hasSize(greaterThan(0)));
    }

    private String obtainAdminToken() {
        //                                      
        // NOCH ARBEITEN //
        //
        return "mocked-admin-token";
    }
}

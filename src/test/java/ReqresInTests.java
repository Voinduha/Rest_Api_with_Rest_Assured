import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {
    // https://reqres.in/api/users?page=2
    // "page": 2,
    // "total_pages": 2
    // "support": {"text": "To keep ReqRes free, contributions towards server costs are appreciated!"}

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }
    @Test
    void successUsersListTest () {
        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", is(2))
                .body("total_pages", is(2))
                .body("support.text", is ("To keep ReqRes free," +
                        " contributions towards server costs are appreciated!"));
    }
    // https://reqres.in/api/login
    @Test
    void successLoginTest () {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\"," +
                        " \"password\": \"cityslicka\" }")
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}

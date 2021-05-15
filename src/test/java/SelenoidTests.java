import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelenoidTests {

    //make request to https://selenoid.autotests.cloud/status
    //check status code 200
    //check total:5
    //check pending:0

   /*
   {
        state: {},
        origin: "http://136.243.89.21:4445/",
                browsers: {},
        sessions: { },
        version: "1.10.3[2021-02-23_09:48:39AM]",
                errors: [ ]
    }
    */

    @Test
    void successStatusOkWithAssertJTest() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .extract()
                .path("state.total");

        assertThat(response).isEqualTo(5);
    }

    @Test
    void successStatusOkPendingWithAssertJTest() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .extract()
                .path("state.pending");

        assertThat(response).isEqualTo(0);
    }
}

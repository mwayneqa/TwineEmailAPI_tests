package PutRequests;

import Base.BaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

/**
 * Created by mud on 9/2/17.
 */
public class PutUnreadStatus extends BaseTest {

//Asserts that return json is valid documented schema format (assumes email-schema.json is intended schema file. Will fail without schema.)
@Test(priority = 3)
public void assertJSONSchemaValid() throws Exception {


    when()
       .get("/twine-mail-get.json")
    .then().assertThat()
       .statusCode(200)
       .body(matchesJsonSchemaInClasspath("email-schema.json"));

    }

    //Sends PUT request to mark email unread
    @Test
    public void markEmailsUnread() throws Exception {

        when()
            .put("/twine-mail-put.json")
        .then().assertThat()
            .statusCode(200)
            .body("unread", equalTo("false"));

    }

}

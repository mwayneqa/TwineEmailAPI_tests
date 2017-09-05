package PutRequests;

import Base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

/**
 * Created by mud on 9/2/17.
 */
public class PutUnreadStatus extends BaseTest{

    @Test

    //Sends PUT request to mark email unread
    public void markEmailsUnread() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .put("/twine-mail-put.json")
        .then()
            .statusCode(200)
        .body("unread", equalTo("false"));

    }

}

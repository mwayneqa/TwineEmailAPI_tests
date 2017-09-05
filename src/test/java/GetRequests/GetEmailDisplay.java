package GetRequests;

import Base.BaseTest;
import io.restassured.http.ContentType;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

/**
 * Created by mud on 9/2/17.
 */
//@formatter:off
public class GetEmailDisplay extends BaseTest {

    //Asserts that a successful call returns 200 status
    @Test(priority = 1)
    public void assert200StatusWithSuccessfulRequest() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
             .statusCode(200);
    }

    //Asserts that an invalid URL returns 400 status
    @Test(priority = 2)
    public void assert403StatusWithForbiddenRequest() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
             .get("//twine-mail-get.json")
        .then().assertThat()
             .statusCode(403);
    }

    //Asserts that return json is valid schema format (assumes email-schema.json is intended schema file)
    @Test(priority = 3)
    public void assertJSONSchemaValid() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("email-schema.json"));

    }

    //Asserts all email Ids are returned and correct with Get call
    @Test(priority = 4)
    public void assertAllEmailIdsPresent() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body(withArgs("emails.id"), hasItems(0, 1, 2, 3, 4, 5), is("string"));

    }

    //Asserts first email returned has Id 0
    @Test(priority = 5)
    public void assertFirstEmailIdIsZero() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.id[0]", equalTo(0));
    }

    //Asserts all email subjects are returned and correct with Get call
    @Test(priority = 6)
    public void assertAllEmailSubjectsPresent() throws Exception {

         given()
             .contentType(ContentType.JSON)
         .when()
             .get("/twine-mail-get.json")
         .then().assertThat()
              .statusCode(200)
              .body("emails.subject", hasItems("Tahoe Trip Next Weekend", "Next steps", "Hooray!", "Communications Director", "Jupiter photos", "History of Lorem Ipsum"));

    }

    //Asserts all email senders are returned and correct with Get call
    @Test(priority = 7)
    public void assertAllEmailSendersPresent() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.from", hasItems("x@gmail.com", "x@gmail.com", "x@gmail.com", "x@gmail.com", "x@gmail.com", "x@gmail.com"));
    }

    //Asserts email with Id 0 returns correct array of recipients with Get call
    @Test(priority = 8)
    public void assertEmailIdZeroRecipients() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-public/apis/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.to[0]", hasItems("a@gmail.com", "b@gmail.com", "c@gmail.com"));
    }

    //Asserts the body of email Id 5 is returned and correct with Get call
//    @Test(priority = 9)
//    public void assertEmailIdFiveBodyText() throws Exception {
//
//        given().when().get("/twine-public/apis/twine-mail-get.json").then()
//                .body("emails.body[5]", equalTo("It has been used as dummy text since the 1500s"));

//    }

    //Asserts date on email Id 3 is correct
    @Test(priority = 10)
    public void assertEmailIdThreeDate() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.date[3]", equalTo("2012-12-23T19:20Z")); notNullValue();
    }

    @Test(priority = 10)
    public void assertEmailDatesNotNull() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .body("emails.date", notNullValue())
            .statusCode(200);


    }

    //Asserts unread statuses for first email
    @Test(priority = 11)
    public void assertAllUnreadStatusEmailId0() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.unread[0]", equalTo(true));

    }

       @Test(priority = 5)
    public void assertEmailIdsAreIntegers() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then()
            .body("emails.id", Assert.notNull())
            .statusCode(200);

    }
}

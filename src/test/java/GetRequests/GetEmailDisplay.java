package GetRequests;

import Base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static jdk.internal.dynalink.support.Guards.isNotNull;
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

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
               .statusCode(200);
    }

    //Asserts that an invalid URL returns 400 status
    @Test(priority = 2)
    public void assert403StatusWithForbiddenRequest() throws Exception {

        when()
             .get("//twine-mail-get.json")
        .then().assertThat()
               .statusCode(403);
    }

    //Asserts that return json is valid documented schema format (assumes email-schema.json is intended schema file. Will fail without schema.)
    @Test(priority = 3)
    public void assertJSONSchemaValid() throws Exception {


        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("email-schema.json"));

    }


    //Asserts all email Ids are returned and correct with Get call
    @Test(priority = 4)
    public void assertAllEmailIdsPresent() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.id", hasItems(0, 1, 2, 3, 4, 5));


    }

    //Asserts first email returned has Id 0 with Get call
    @Test(priority = 5)
    public void assertFirstEmailIdIsZero() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.id[0]", equalTo(0));
    }

    //Asserts no email IDs contain null value
    @Test(priority = 6)
    public void assertEmailIdsNotNull() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .body("emails.id", hasItems(notNullValue()))
            .statusCode(200);

    }

    //Asserts all email subjects are returned and correct with Get call
    @Test(priority = 7)
    public void assertAllEmailSubjectsCorrect() throws Exception {

         when()
             .get("/twine-mail-get.json")
         .then().assertThat()
             .statusCode(200)
             .body("emails.subject", hasItems("Tahoe Trip Next Weekend", "Next steps", "Hooray!", "Communications Director", "Jupiter photos", "History of Lorem Ipsum"));

    }

    //Asserts email id 2 subject is returned and correct with Get call
    @Test(priority = 8)
    public void assertEmailIdTwoSubjectCorrect() throws Exception {

         when()
            .get("/twine-mail-get.json")
         .then().assertThat()
            .statusCode(200)
            .body("emails.subject[2]", equalTo("Hooray!"));

    }

    //Asserts all email senders are returned and correct with Get call
    @Test(priority = 9)
    public void assertAllEmailSendersPresent() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.from", hasItems("x@gmail.com", "x@gmail.com", "x@gmail.com", "x@gmail.com", "x@gmail.com", "x@gmail.com"));

    }

    @Test(priority = 10)
    public void assertEmailSendersPresentForEmailId5() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.from[5]", equalTo("x@gmail.com"));
            hasItem(isNotNull());

    }

    //Asserts no email senders contain null value
    @Test(priority = 11)
    public void assertEmailSendersNotNull() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .body("emails.from", hasItems(notNullValue()))
            .statusCode(200);

    }

    //Asserts all emails return correct array of recipients with Get call
    @Test(priority = 12)
    public void assertEmailIdZeroRecipients() throws Exception {

        when()
           .get("/twine-mail-get.json")
        .then().assertThat()
           .statusCode(200)
           .body("emails.to[0]", equalTo("[a@gmail.com, b@gmail.com, c.gmail.com]"));

    }

    //Asserts email with Id 0 returns correct array of recipients with Get call
    @Test(priority = 13)
    public void assertAllEmailIdRecipients() throws Exception {

        when()
           .get("/twine-mail-get.json")
        .then().assertThat()
           .statusCode(200)
           .body("emails.to[0]", hasItems("[a@gmail.com, b@gmail.com, c.gmail.com]", "[a@gmail.com, b@gmail.com, c.gmail.com]", "[a@gmail.com, b@gmail.com, c.gmail.com]"));

    }

    //Asserts no email recipients contain null value
    @Test(priority = 14)
    public void assertEmailRecipientsNotNull() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .body("emails.to", hasItems(notNullValue()))
            .statusCode(200);

    }

    //Asserts the body of email Id 5 is returned and correct with Get call
    @Test(priority = 15)
    public void assertEmailIdFiveBodyText() throws Exception {

          when()
             .get("/twine-mail-get.json")
          .then().assertThat()
             .statusCode(200)
             .body("emails.body[5]", equalTo("It has been used as dummy text since the 1500s"));

    }

    //Asserts date on email Id 3 is correct
    @Test(priority = 16)
    public void assertEmailIdThreeDate() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.date[3]", equalTo("2012-12-23T19:20Z"));

    }

    //Asserts no emails contain null value. Will fail because of null value present in email date response
    @Test(priority = 17)
    public void assertEmailDatesNotNull() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .body("emails.date", equalTo(notNullValue()))
            .statusCode(200);

    }


    //Asserts unread statuses for all emails
    @Test(priority = 18)
    public void assertAllUnreadStatusEmails() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.unread", hasItems(true, false, false, true, true, false, true));

    }

    //Asserts unread statuses for first email
    @Test(priority = 19)
    public void assertUnreadStatusEmailId0() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .statusCode(200)
            .body("emails.unread[0]", equalTo(true));

    }

    //Asserts no emails contain null value. Will fail because of null value present in email date response
    @Test(priority = 20)
    public void assertUnreadStatusNotNull() throws Exception {

        when()
            .get("/twine-mail-get.json")
        .then().assertThat()
            .body("emails.unread", hasItems(notNullValue()))
            .statusCode(200);

    }

}

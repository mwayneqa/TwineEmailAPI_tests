package Base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.*;


/**
 * Created by mud on 9/2/17.
 */
public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public static void setUpDefault() {

        setUpBaseURI();

        RestAssured.given()
            .contentType(ContentType.JSON); //JSON content type is being used

    }

    private static void setUpBaseURI() {

        RestAssured.baseURI = "https://s3.us-east-2.amazonaws.com";
        RestAssured.basePath = "/twine-public/apis/";

        }

    }

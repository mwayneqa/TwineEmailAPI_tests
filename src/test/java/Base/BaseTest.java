package Base;

import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

/**
 * Created by mud on 9/2/17.
 */
public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public static void setUpDefault() {

        setUpBaseURI();

    }

    private static void setUpBaseURI() {

        RestAssured.baseURI = "https://s3.us-east-2.amazonaws.com/twine-public/apis/";


        }

    }

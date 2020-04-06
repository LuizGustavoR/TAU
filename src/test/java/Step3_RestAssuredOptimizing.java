import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Step3_RestAssuredOptimizing {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void Setup(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://zippopotam.us/").
                build();

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                expectContentType(ContentType.JSON).
                build();
    }

    // Test RequestSpecification and ResponseSpecification
    @Test
    public void requestUsZipCode_requestSpec_responseSpec(){
        given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                spec(responseSpec).
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    // Test Extract
    @Test
    public void requestUsZipCode_Extract(){

        String placeName =

        given().
                spec(requestSpec).
                when().
                get("https://zippopotam.us/us/90210").
                then().
                extract().
                path("places[0].'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");
    }

}

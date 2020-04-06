import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Step1_RestAssuredBasic {

    @BeforeClass
    public void Setup(){
        RestAssured.baseURI = "https://zippopotam.us/";
        RestAssured.basePath = "us/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    // Test Content Type
    @Test
    public void requestUsZipCode_contentType(){
        given().
                when().
                get("90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    // Test equalTo(x) - JsonPath
    @Test
    public void requestUsZipCode_equalTo_JsonPath(){
        Response response = given().
                when().
                get("90210");

        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        String country = jsonPath.getString("country");
        Assert.assertEquals(country, "United States");
    }

    // Test equalTo(x) - Hamcrest
    @Test
    public void requestUsZipCode_equalTo_HamCrest(){
        given().
                when().
                get("90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    // Test hasItem("Rome")
    @Test
    public void requestUsZipCode_hasItem(){
        given().
                when().
                get("90210").
                then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills"));
    }

    // Test hasSize(3)
    @Test
    public void requestUsZipCode_hasSize(){
        given().
                when().
                get("90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }

    // Test not(equalTo(x))
    @Test
    public void requestUsZipCode_not(){
        given().
                when().
                get("90210").
                then().
                assertThat().
                body("places.'place name'", not(hasItems("Beverly Hills")));
    }

}

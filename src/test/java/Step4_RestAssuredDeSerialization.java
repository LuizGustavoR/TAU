import entities.Location;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Step4_RestAssuredDeSerialization {

    // Test Deserialization
    @Test
    public void requestUsZipCode_Deserialize(){

        Location location =

        given().
                when().
                get("https://api.zippopotam.us/us/90210").
                as(Location.class);

        Assert.assertEquals(location.getPlaces().get(0).getPlaceName(), "Beverly Hills");

    }

    // Test Serialization, Need WireMock to work
    @Test
    public void requestUsZipCode_Serialize(){

        Location location = new Location();

        given().
                contentType(ContentType.JSON).
                body(location).
                log().body().
                when().
                post("https://api.zippopotam.us/us/90210").
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

}

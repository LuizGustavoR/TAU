import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.ReadFile;

import static io.restassured.RestAssured.given;

public class Step5_RestAssuredReadBodyFile {

    public static final String JSON_PATH = "src/test/resources/json/";

    // Test get json file from resource and use it as body, the code is write, endpoint is not working
    @Test
    public void testBodyFromJsonFile(){

        String file = ReadFile.getFile(JSON_PATH + "test.json");
        JSONObject jsonObject = new JSONObject(file);

        given().
                body(jsonObject.toString()).
                when().
                post("https://api.zippopotam.us/us/90210").
                then().statusCode(201);

    }

}

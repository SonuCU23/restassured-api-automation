
import com.learning.map.request.AddPlace;
import com.learning.map.request.UpdatePlace;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class AddPlaceTest {

    public static void main(String[] args){
        // validate Add place api
        RestAssured.baseURI = "https://rahulshettyacademy.com";


        String response =given()
                            .log().all()
                            .queryParam("key","qaclick123")
                            .header("Content-Type","application/json")
                            .body(AddPlace.getAddPlacePayload())
                        .when()
                            .post("maps/api/place/add/json")
                        .then()
                            .log().all()
                            .assertThat().statusCode(200)
                            .body("scope", equalTo("APP"))
                            .header("Server","Apache/2.4.52 (Ubuntu)")
                            .extract().response().asString();


        JsonPath responseJson = new JsonPath(response);
        String placeId = responseJson.getString("place_id");
        System.out.println(placeId);

        // Update Place API Assertion
        String updatePlaceResponse = given()
                                            .log().all()
                                            .queryParam("key","qaclick123")
                                            .header("Content-Type","application/json")
                                            .body(UpdatePlace.getUpdatePlaceRequestPayload(placeId))
                                    .when()
                                            .put("maps/api/place/update/json")
                                    .then()
                                            .log().all()
                                            .assertThat().statusCode(200)
                                            .body("msg",equalTo("Address successfully updated"))
                                            .extract().response().asString();


        // Get Place API Assert to assert the new update place

        String getResponse =  given()
                                    .log().all()
                                    .queryParam("key","qaclick123")
                                    .queryParam("place_id",placeId)
                            .when()
                                    .get("maps/api/place/get/json")
                            .then()
                                    .log().all()
                                    .assertThat().statusCode(200)
                                    .body("address", equalTo("70 winter walk, USA"))
                                    .extract().response().asString();

        // Using TestNG Assertion to assert Update Place
        JsonPath getJson = new JsonPath(getResponse);
        String actualPlace = getJson.getString("address");
        Assert.assertEquals(actualPlace,"70 winter walk, USA");

    }
}



//{
//        "status": "OK",
//        "place_id": "710e538f75636b76d9ac2b83ceb98956",
//        "scope": "APP",
//        "reference": "1d32e831b0dc75ae770af98aba7e50da1d32e831b0dc75ae770af98aba7e50da",
//        "id": "1d32e831b0dc75ae770af98aba7e50da"
//}
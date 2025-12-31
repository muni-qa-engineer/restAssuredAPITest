package RESTapiTesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import files.payLoad;

public class FirstAPITest {
	
	@Test
	public void apiTest(){
		
//		Add the place(POST method) -> Update the place(PUT method) -> get the place with assertion(GET)
		
//		-------------------------------------------------------------
//		Important rule to be remember while dealing with REST API
//		1. GIVEN - it has all inputs like Params, body
//		2. When - It has submit part with details like resource
//		3. Then - Validation part
//		-------------------------------------------------------------
		
//		Add the place(POST method)
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given()
		.header("Content-Type", "application/json")
		.queryParam("key", "qaclick123").body(payLoad.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all()
		.assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response); // -> Here we are getting OK response log, instead of using log().all();
		
		//Now we have complete response, from that we need to extract the "place_id" to reuse.
		//For this we have JsonPath, we need to create object for JsonPath.
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id"); // -> We are storing the place ID in one string for reuse.
		
		
//		-> Update the place(PUT method)
		given().log().all().header("Content-Type", "application/json")
		.queryParam("key", "qaclick123")
		.body("{\n"
				+ "\"placeid\":\""+placeId+"\",\n"
				+ "\"address\":\"70 winter walk, USA\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then()
		.assertThat().statusCode(200);
		
//		-> get the place with assertion(GET)
		
		String updatedAddress = given().log().all()
		.queryParam("place_id", placeId)
		.queryParam("key", "qaclick123")
		.when().get("/maps/api/place/get/json")
		.then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(updatedAddress);

	}

}

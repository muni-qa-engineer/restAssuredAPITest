package RestAssuredAPI_Testing;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) {
		
//		POST method to generate the access_token
		String response = given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.asString();
		
		System.out.println(response);
		
//		GET method to retrive the data using access_token
		JsonPath jp = new JsonPath(response);
		String accessToken = jp.getString("access_token");
		
		String getResponse = given()
		.queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.asString();
		System.out.println(getResponse);
		
	}
	
}

package RestAssuredAPI_Testing;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.path.json.JsonPath;
import pojo.api;
import pojo.deserialization;
import pojo.webAutomation;

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
		
		deserialization getResponse = given()
		.queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(deserialization.class);
		System.out.println(getResponse.getInstructor());
		
		getResponse.getCourses().getApi().get(1).getCourseTitle();
		
		List<api> apiCourseprice = getResponse.getCourses().getApi();
		for(int i=0; i<apiCourseprice.size(); i++) {
			if(apiCourseprice.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("Price of the course is " + apiCourseprice.get(i).getPrice());
			}
		}
		
		List<webAutomation> webAutomationCourse = getResponse.getCourses().getWebAutomation();
		for(int j =0; j<webAutomationCourse.size(); j++) {
			System.out.println(webAutomationCourse.get(j).getCourseTitle());
		}
		
		
	}
	
}

package RestAssuredAPI_Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.location;
import pojo.serializatio;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class mapsAPI {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		serializatio ser = new serializatio();
		
		ser.setAccuracy(50);
		ser.setAddress("Backline house");
		ser.setPhone_number("(+91) 983 893 3937");
		ser.setAddress("29, side layout, cohen 09");
		ser.setWebsite("http://google.com");
		ser.setLanguage("Telugu-IN");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ser.setTypes(myList);
		
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ser.setLocation(l);
		
		
		Response res = given().log().all()
		.queryParams("key", "qaclick123")
		.body(ser)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(res.asString());
		
	}

}

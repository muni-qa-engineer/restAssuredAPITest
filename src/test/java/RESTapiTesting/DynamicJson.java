package RESTapiTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="bookNames")
	public void addBook(String isbn, String aisle) {
		
//		---------------------------------------------------------------------------
//		Library API is validated by passing the values Dynamically.
//		For that I used here passing these two Strings "zsekh", "909090" which are
//		can change. Introduced JsonPath for accessing payload variables.
//		It has both POST and DELETE method with base url: "http://216.10.245.166"
//		---------------------------------------------------------------------------
		
		RestAssured.baseURI = ("http://216.10.245.166");
		
//		POST Method
		String AddBookresponse = given().header("Content-Type", "application/json")
		.body(payLoad.libraryBook(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(AddBookresponse);
		
//		DELETE Method
		JsonPath js = new JsonPath(AddBookresponse);
		String bookid = js.get("ID"); // -> to retrive the ID value from response
		
		String deletebook = given()
				.header("Content-Type", "application/json")
				.body("{ \"ID\": \""+bookid+"\" }")
				.when().post("/Library/DeleteBook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
				
				System.out.println(deletebook);
				
	}
	
	@DataProvider(name="bookNames")
	public Object[][]  bookslist() {
		
		return new Object[][] {{"seklu", "34563"},{"zsekh", "909090"},{"zqsekh", "956790"} };
	}
	
	
}

package RESTapiTesting;

import files.payLoad;
import io.restassured.path.json.JsonPath;

public class complexJsonParsing {
	
	public static void main(String[] args) {
		
		
		JsonPath jp = new JsonPath(payLoad.CourseMockJson());
		
//		1. Print No of courses returned by API
		int count = jp.getInt("courses.size()");
		System.out.println(count);
		
//		2.Print Purchase Amount
		int amount = jp.getInt("dashboard.purchaseAmount");
		System.out.println(amount);

//		3. Print Title of the first course
		String firstTitle = jp.get("courses[0].title");
		System.out.println("First Course name is " + firstTitle);
		
//		4. Print All course titles and their respective Prices
		
		
		for (int i = 0; i<count; i++) {
		
			String Title_Name = jp.get("courses["+i+"].title");
			System.out.println("Name of the course: " + Title_Name);
			
			System.out.println(jp.getInt("courses["+i+"].price"));
			
		}
		
//		5. Print no of copies sold by RPA Course
		for (int i = 0; i<count; i++) {
			
			String Title_Name = jp.get("courses["+i+"].title");
			
			if(Title_Name.equalsIgnoreCase("RPA")) {
			
				System.out.println("Sold copies of "+ Title_Name +":"+ jp.getInt("courses["+i+"].copies"));
			}
			
		}
		
//		6. Verify if Sum of all Course prices matches with Purchase Amount
		int amountSum = 0 ;
		int sum ;
		for(int j=0; j<count; j++) {
			int price = jp.getInt("courses["+j+"].price");
			int copy = jp.getInt("courses["+j+"].copies");
			sum = 	price*	copy;
			amountSum = amountSum + sum ;
			
		}
		System.out.println("Sum of all Course prices " + amountSum);
		
		if(amountSum == amount) {
			System.out.println("Sum of all Course prices is mentioned correct " + amountSum);
		}
		
		
		
	}

}

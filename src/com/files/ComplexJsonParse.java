package com.files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js =new JsonPath(Payload.coursePrice()); 
		
		//1. Print No of courses returned by API
		int count = js.getInt("courses.size()"); 
		System.out.println(count); 
		
		//2.Print Purchase Amount
		int totalamount = js.getInt("dashboard.purchaseAmount"); 
		System.out.println(totalamount); 
		//3. Print Title of the first course 
		String title = js.getString("courses[0].title"); 
		System.out.println(title); 
		
		// Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
			String Alltitle = js.get("courses["+i+"].title"); 
			System.out.println(Alltitle); 
			System.out.println(js.get("courses["+i+"].price").toString());
			
		}
		//5. Print no of copies sold by RPA Course
		for(int i=0;i<count;i++) { 
			String Alltitle = js.get("courses["+i+"].title"); 
			
			if(Alltitle.equalsIgnoreCase("RPA")) {
			   System.out.println(js.get("courses["+i+"].copies").toString()); 
			}		
		}
		//Verify if Sum of all Course prices matches with Purchase Amount

	}

}

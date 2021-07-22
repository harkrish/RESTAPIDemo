package com.files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DynamicJson { 
	
	
	@Test(dataProvider="bookData") 
	public void addBook(String isbn,String aisle){
		RestAssured.baseURI="http://216.10.245.166";

		String response= given().header("Content-Type","application/json").body(Payload.addBook(isbn,aisle)).
	    when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js= ReUsableMethods.getRawToJsonResponse(response);

		   String id=js.get("ID");

		   System.out.println(id);
	}

	 
	
	@DataProvider(name="bookData") 
	public Object[][] getData() {
		return new Object[][] {{"isbn123","aisle101"},{"isb123","aisl101"},{"is123","ais101"}};
	}

}

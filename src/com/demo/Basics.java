package com.demo;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; 
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import com.files.Payload;
import com.files.ReUsableMethods; 

public class Basics {

	public static void main(String[] args) throws IOException {
		
		RestAssured.baseURI="https://rahulshettyacademy.com"; 
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				
				//Payload.postPlace()
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Harikrishnan.S3\\eclipse-workspace\\RestAPIDemo\\src\\com\\demo\\AddPlace.json")))).when().post("maps/api/place/add/json")
		        .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		        .header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString(); 
		
		System.out.println(response); 
		JsonPath js = new JsonPath(response);  //takes input as string and convert again into json  
		String place_id = js.getString("place_id"); //or get returns string
		System.out.println(place_id); 
		
		
		//update place change address 
		String newAddress = "Manju Apartments, Nesapakkam";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.updatePlace(place_id,newAddress)).when().put("maps/api/place/update/json")
		   .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//get place 
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		 .when().get("maps/api/place/get/json")
		 .then().log().all().assertThat().statusCode(200).extract().response().asString();  
		
		JsonPath js2 = ReUsableMethods.getRawToJsonResponse(getPlaceResponse); 
		String actualAddress = js2.getString("address"); 
		System.out.println(actualAddress);
		Assert.assertEquals(newAddress,actualAddress); 
		
		//For POJO Class 
//		GetCourse gc =given().queryParam("key", "val").expect().defaultParser(Parser.JSON).
//				when().get("https://www.google.com/getCourse.php").as(GetCourse.class); 
		
		//If content Type is not application/json and looks like text/html..use defaultParser(Parser.JSON) to parse into JSON.
		
		
		
		

	}

}

package com.files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods { 
	
	public static JsonPath getRawToJsonResponse(String response) {
		JsonPath js = new JsonPath(response); 
		return js;
	}

}

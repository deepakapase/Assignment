package com.api.automation;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

import java.util.Map;
import java.util.Map.Entry;

public class StoreAvailabiltyTest {

	
	@Test
	public void testCountry_Status() {
		Response response =given()
			.relaxedHTTPSValidation()
		.when()
			.get("https://istheapplestoredown.com/api/v1/status/worldwide")
		.then()
			.statusCode(200)
			.extract()
			.response();
		
		Map<String, Map<String,String>> map  = response.jsonPath().getMap("$");
		for (Entry<String,Map<String,String>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": => " + entry.getValue().toString());
			Map<String,String> value = entry.getValue();
			String status = value.get("status");
			if(status != null && status.equalsIgnoreCase("y")){
				System.out.println(value.get("name"));
				Assert.fail("Invalid country status y");

			}
		}
	}
	
}

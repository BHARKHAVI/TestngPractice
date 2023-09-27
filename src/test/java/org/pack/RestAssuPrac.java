package org.pack;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;
import static io.restassured.RestAssured.*;
public class RestAssuPrac {
	
	@Test
	public void sampleTest()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}").when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		//update using put method
		String addup="70 winter walk, USA";
		given().log().all().queryParam("place_id", "placeid")
		.queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+addup+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200);
		
		//get method to see the updated adddress
		String getresponse=given().log().all().queryParam("place_id", "72966593981172c1d355255e92dee64b")
		.queryParam("key", "qaclick123")
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(getresponse);
		JsonPath js1=new JsonPath(getresponse);
		String add = js1.get("address");
		Assert.assertEquals(add, addup);
	}

}

package reqres.services;


import org.hamcrest.core.IsNull;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import json.GetDataFromJson;

public class UserService2 {

	RequestSpecification spec;
	
	@BeforeClass
	public void setRequestSpecification() {
			RestAssured.baseURI = "https://reqres.in";
				spec = RestAssured.given().header("Accept", "*/*").contentType(ContentType.JSON)
						.log().ifValidationFails();
//						.body(IsNull.notNullValue());
						
	}
	
	@BeforeMethod
	public void setBasePath() {
		RestAssured.baseURI = "https://reqres.in";
	}

	@Test
	public void getUserTest() {

		Response res = RestAssured.given().when().get("/api/users?page=1").then()
				.log().ifValidationFails()
				.body(IsNull.notNullValue())
				.statusCode(200)
				.extract().response();
		
		System.out.println(res.body().prettyPrint());

		System.out.println(res.body().jsonPath().get("total_pages").toString());

		Assert.assertEquals(2, Integer.parseInt(res.body().jsonPath().get("total_pages").toString()));

	}

	@Test
	public void getUserWithQueryParamTest() {
		Response res = RestAssured.given().queryParam("page", "1").when().get("/api/users").then()
				.log().ifValidationFails()
				.body(IsNull.notNullValue())
				.statusCode(200)
				.extract().response();
		
		System.out.println(res.body().prettyPrint());

		System.out.println(res.body().jsonPath().get("per_page").toString());

		Assert.assertEquals(6, Integer.parseInt(res.body().jsonPath().get("per_page").toString()));

	}

	@Test
	public void deleteUserWithPathParamTest() {
		RestAssured.given().pathParam("id", "2").when().delete("/api/users/{id}").then()
				.statusCode(204)
				.log().ifValidationFails();		
	}

	@Test
	public void createUserWithRequestBodyAndHeaderTest() {
		String reqBody = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";
		Response res = RestAssured.given(spec).body(reqBody).when()
				.post("/api/users").then()
				.statusCode(201)
				
				.extract().response();

		
		Assert.assertEquals("morpheus", res.body().jsonPath().get("name").toString());
		Assert.assertEquals("leader", res.body().jsonPath().get("job").toString());
		System.out.println("ID: " + res.body().jsonPath().get("id").toString());
	}

	@Test
	public void updateUserWithRequestBodyAndHeaderTest() {

		GetDataFromJson getJson = new GetDataFromJson();
		JSONObject josnObject = getJson.getJsonData("//src//test//resources//TestData//UpdateUser.json");

		Response res = RestAssured.given(spec).pathParam("id", "2")
				.body(josnObject.toString()).when().put("/api/users/{id}").then()
				.statusCode(200)
				.extract().response();
		
		Assert.assertEquals(josnObject.get("name").toString(), res.body().jsonPath().get("name").toString());
		Assert.assertEquals(josnObject.get("job"), res.body().jsonPath().get("job").toString());

	}

	@Test
	public void partialUpdateUserWithRequestBodyAndHeaderTest() {

		GetDataFromJson getJson = new GetDataFromJson();
		JSONObject josnObject = getJson.getJsonData("//src//test//resources//TestData//PartialUpdateUser.json");

		String reqBody = String.format(josnObject.toString(), "Automation Tester");

		System.out.println(reqBody);

		Response res = RestAssured.given(spec).pathParam("id", "2")
				.body(reqBody).when().patch("/api/users/{id}").then()
				.statusCode(200)
				.extract().response();
		Assert.assertEquals("Automation Tester", res.body().jsonPath().get("job").toString());

	}

}

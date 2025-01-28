package reqres.services;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import json.GetDataFromJson;

public class UserService {

	@Test
	public void getUserTest() {
		Response res = RestAssured.given().when().get("https://reqres.in/api/users?page=1").then().extract().response();
		Assert.assertEquals(200, res.statusCode());
		System.out.println(res.body().prettyPrint());

		System.out.println(res.body().jsonPath().get("total_pages").toString());

		Assert.assertEquals(2, Integer.parseInt(res.body().jsonPath().get("total_pages").toString()));

	}

	@Test
	public void getUserWithQueryParamTest() {
		Response res = RestAssured.given().queryParam("page", "1").when().get("https://reqres.in/api/users").then()
				.extract().response();
		Assert.assertEquals(200, res.statusCode());
		System.out.println(res.body().prettyPrint());

		System.out.println(res.body().jsonPath().get("per_page").toString());

		Assert.assertEquals(6, Integer.parseInt(res.body().jsonPath().get("per_page").toString()));

	}

	@Test
	public void deleteUserWithPathParamTest() {
		Response res = RestAssured.given().pathParam("id", "2").when().delete("https://reqres.in/api/users/{id}").then()
				.extract().response();
		Assert.assertEquals(204, res.statusCode());
	}

	@Test
	public void createUserWithRequestBodyAndHeaderTest() {
		String reqBody = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";
		Response res = RestAssured.given().header("Accept", "*/*").contentType(ContentType.JSON).body(reqBody).when()
				.post("https://reqres.in/api/users").then().extract().response();

		Assert.assertEquals(201, res.statusCode());
		Assert.assertEquals("morpheus", res.body().jsonPath().get("name").toString());
		Assert.assertEquals("leader", res.body().jsonPath().get("job").toString());
		System.out.println("ID: " + res.body().jsonPath().get("id").toString());
	}

	@Test
	public void updateUserWithRequestBodyAndHeaderTest() {

		GetDataFromJson getJson = new GetDataFromJson();
		JSONObject josnObject = getJson.getJsonData("//src//test//resources//TestData//UpdateUser.json");

		Response res = RestAssured.given().pathParam("id", "2").header("Accept", "*/*").contentType(ContentType.JSON)
				.body(josnObject.toString()).when().put("https://reqres.in/api/users/{id}").then().extract().response();

		Assert.assertEquals(200, res.statusCode());
		Assert.assertEquals(josnObject.get("name").toString(), res.body().jsonPath().get("name").toString());
		Assert.assertEquals(josnObject.get("job"), res.body().jsonPath().get("job").toString());

	}

	@Test
	public void partialUpdateUserWithRequestBodyAndHeaderTest() {

		GetDataFromJson getJson = new GetDataFromJson();
		JSONObject josnObject = getJson.getJsonData("//src//test//resources//TestData//PartialUpdateUser.json");

		String reqBody = String.format(josnObject.toString(), "Automation Tester");

		System.out.println(reqBody);

		Response res = RestAssured.given().pathParam("id", "2").header("Accept", "*/*").contentType(ContentType.JSON)
				.body(reqBody).when().patch("https://reqres.in/api/users/{id}").then().extract().response();

		Assert.assertEquals(200, res.statusCode());
		Assert.assertEquals("Automation Tester", res.body().jsonPath().get("job").toString());

	}

}

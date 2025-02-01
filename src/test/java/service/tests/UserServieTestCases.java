package service.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import service.utils.ReadDataFromExtrenalFiles;
import services.UserService;

public class UserServieTestCases extends ServiceTestsBaseClass {

	UserService userService = new UserService();

	@Test
	public void testGetListOfUsersEndpoint() {
		RequestSpecification reqSpec = RestAssured.given().baseUri(baseUrl);
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("page", "2");
		List<Map<String, Object>> listOfUsersResponse = userService.getListOfUsers(reqSpec, queryParams, 200, "data");
		Assert.assertTrue(listOfUsersResponse.size() > 0);
	}

	@Test
	public void testCreateUserEndpoint() {
		JSONObject jObject = ReadDataFromExtrenalFiles.readDataFromJson(testDataPath + "CreateUser.json");
		String requestBody = jObject.toString();
		Map<String, String> createUsersResponse = userService.createUser(reqSpec, requestBody, 201);
		Assert.assertEquals(jObject.get("name").toString(), createUsersResponse.get("name"));
		Assert.assertEquals(jObject.get("job").toString(), createUsersResponse.get("job"));
	}

	@Test
	public void testUpdateUserEndpoint() {
		Map<String, String> pathParam = new HashMap<>();
		pathParam.put("id", "2");
		JSONObject jObject = ReadDataFromExtrenalFiles.readDataFromJson(testDataPath + "UpdateUser.json");
		String requestBody = jObject.toString();
		Map<String, String> updateUsersResponse = userService.updateUser(reqSpec, pathParam, requestBody, 200);
		Assert.assertEquals(jObject.get("name").toString(), updateUsersResponse.get("name"));
		Assert.assertEquals(jObject.get("job").toString(), updateUsersResponse.get("job"));
	}
//
//	@Test
//	public void testPartialUpdateUserEndpoint() {
//		Map<String, String> queryParams = new HashMap<>();
//		queryParams.put("page", "2");
//		List<Map<String, Object>> listOfUsersResponse = userService.getListOfUsers(reqSpec, queryParams, 200, "data");
//		Assert.assertTrue(listOfUsersResponse.size() > 0);
//	}
//
//	@Test
//	public void testDeleteUsersEndpoint() {
//	RequestSpecification reqSpec = RestAssured.given().baseUri(baseUrl);
//		Map<String, String> queryParams = new HashMap<>();
//		queryParams.put("page", "2");
//		List<Map<String, Object>> listOfUsersResponse = userService.getListOfUsers(reqSpec, queryParams, 200, "data");
//		Assert.assertTrue(listOfUsersResponse.size() > 0);
//	}

}

package reqres.services;

import org.hamcrest.core.IsNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PetStoreService {

	
	@BeforeMethod
	public void setBasePath() {
		RestAssured.baseURI = "https://petstore.swagger.io";
	}
	
	@Test
	public void getListOfUsers() {
	
		
		Response res = RestAssured.given().auth().basic("test", "abc123").queryParam("status", "available").when().get("/v2/pet/findByStatus").then()
				.log().ifValidationFails()
				.body(IsNull.notNullValue())
				.statusCode(200)
				.extract().response();
		
		System.out.println(res.body().prettyPrint());
	}
}

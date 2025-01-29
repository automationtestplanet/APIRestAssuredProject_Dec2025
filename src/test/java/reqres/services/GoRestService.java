package reqres.services;

import org.hamcrest.core.IsNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GoRestService {

	@BeforeMethod
	public void setBasePath() {
		RestAssured.baseURI = "https://gorest.co.in";
	}
	
	@Test
	public void getListOfUsers() {
		
		String token = "Bearer 461730528f17a70434f9f9979452f04e6f7b4af9b78f022d78884c50b176d917";
		
		Response res = RestAssured.given().header("Authorizaion",token).when().get("/public/v2/users").then()
				.log().ifValidationFails()
				.body(IsNull.notNullValue())
				.statusCode(200)
				.extract().response();
		
		System.out.println(res.body().prettyPrint());
	}
	
	
	
	
	
}

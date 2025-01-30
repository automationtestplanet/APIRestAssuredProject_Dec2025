package services;

import java.util.Map;

import org.hamcrest.core.IsNull;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RestMethods {

	public ValidatableResponse get(RequestSpecification rs, String endPoint, Map<String, String> queryParams,
			int statusCode) {
		return RestAssured.given(rs).queryParams(queryParams).when().get(endPoint).then().statusCode(statusCode)
				.body(IsNull.notNullValue());
	}

	public ValidatableResponse post(RequestSpecification rs, String endPoint, String requestBody, int statusCode) {
		return RestAssured.given(rs).body(requestBody).when().post(endPoint).then().statusCode(statusCode)
				.body(IsNull.notNullValue());
	}

	public ValidatableResponse put(RequestSpecification rs, String endPoint, Map<String, String> pathParams,
			String requestBody, int statusCode) {
		return RestAssured.given(rs).pathParams(pathParams).body(requestBody).when().put(endPoint).then()
				.statusCode(statusCode).body(IsNull.notNullValue());
	}

	public ValidatableResponse patch(RequestSpecification rs, String endPoint, Map<String, String> pathParams,
			String requestBody, int statusCode) {
		return RestAssured.given(rs).pathParams(pathParams).body(requestBody).when().put(endPoint).then()
				.statusCode(statusCode).body(IsNull.notNullValue());
	}

	public ValidatableResponse delete(RequestSpecification rs, String endPoint, Map<String, String> pathParams) {
		return RestAssured.given(rs).pathParams(pathParams).when().put(endPoint).then();
	}
}

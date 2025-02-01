package services;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RestMethods {

	public static RequestSpecification getRequestSpecification(String baseUrl) {
		return RestAssured.given().baseUri(baseUrl).header("Accept", "*/*").contentType(ContentType.JSON).log()
				.ifValidationFails();
	}

	public ValidatableResponse get(RequestSpecification rs, String endPoint, Map<String, String> queryParams,
			int statusCode) {
		return RestAssured.given(rs).queryParams(queryParams).when().get(endPoint).then().statusCode(statusCode);
	}

	public ValidatableResponse post(RequestSpecification rs, String endPoint, String requestBody, int statusCode) {
		return RestAssured.given(rs).body(requestBody).when().post(endPoint).then().statusCode(statusCode);
	}

	public ValidatableResponse put(RequestSpecification rs, String endPoint, String requestBody, int statusCode) {
		return RestAssured.given(rs).body(requestBody).when().put(endPoint).then().statusCode(statusCode);
	}

	public ValidatableResponse putWithPathParams(RequestSpecification rs, String endPoint,
			Map<String, String> pathParams, String requestBody, int statusCode) {

		Set<Map.Entry<String, String>> pathEntry = pathParams.entrySet();
		String pathParamName = "";
		for (Map.Entry<String, String> pathParamEntry : pathEntry) {
			pathParamName = pathParamEntry.getKey();
		}

		return RestAssured.given(rs).pathParams(pathParams).body(requestBody).when()
				.put(endPoint + "/{" + pathParamName + "}").then().statusCode(statusCode);
	}

	public ValidatableResponse patch(RequestSpecification rs, String endPoint, Map<String, String> pathParams,
			String requestBody, int statusCode) {
		return RestAssured.given(rs).pathParams(pathParams).body(requestBody).when().put(endPoint).then()
				.statusCode(statusCode);
	}

	public ValidatableResponse delete(RequestSpecification rs, String endPoint, Map<String, String> pathParams) {
		return RestAssured.given(rs).pathParams(pathParams).when().put(endPoint).then();
	}
}

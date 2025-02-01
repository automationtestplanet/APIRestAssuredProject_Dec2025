package services;

import java.util.List;
import java.util.Map;

import io.restassured.specification.RequestSpecification;

public class UserService extends RestMethods {

	String endPoint = "/api/users";

	public List<Map<String, Object>> getListOfUsers(RequestSpecification rs, Map<String, String> queryParms,
			int statusCode, String resposeFieldName) {
		return get(rs, endPoint, queryParms, statusCode).extract().jsonPath().getList(resposeFieldName);
	}

	public Map<String, String> createUser(RequestSpecification rs, String requestBody, int statusCode) {
		return (Map<String, String>) post(rs, endPoint, requestBody, statusCode).extract().as(Map.class);
	}

	public Map<String, String> updateUser(RequestSpecification rs, Map<String, String> pathParams, String requestBody,
			int statusCode) {
		return (Map<String, String>) putWithPathParams(rs, endPoint, pathParams, requestBody, statusCode).extract().as(Map.class);
	}

	public Map<String, String> partialUpdateUser(RequestSpecification rs, Map<String, String> pathParams,
			String requestBody, int statusCode) {
		return (Map<String, String>) patch(rs, endPoint, pathParams, requestBody, statusCode).extract().as(Map.class);
	}

	public Integer deleteUser(RequestSpecification rs, Map<String, String> pathParams) {
		return delete(rs, endPoint, pathParams).extract().response().getStatusCode();
	}

}

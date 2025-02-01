package service.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.restassured.specification.RequestSpecification;
import service.utils.ApplicationProperties;
import services.RestMethods;

public class ServiceTestsBaseClass {

	public String baseUrl;
	public String testDataPath;
	RequestSpecification reqSpec;

	@BeforeClass(alwaysRun = true)
	@Parameters("Environment")
	public void initilizePropeties(String envronment) {
		baseUrl = ApplicationProperties.getProperty(envronment);
		reqSpec = RestMethods.getRequestSpecification(baseUrl);
		testDataPath = ApplicationProperties.getProperty("test.data.path");
	}
}

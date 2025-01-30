package service.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import service.utils.ApplicationProperties;

public class ServicceTestsBaseClass {

	public String baseUrl;
	
	@BeforeClass(alwaysRun = true)
	@Parameters("environment")
	public void initilizePropeties(String envronment) {
		baseUrl = ApplicationProperties.applicationProperties.getProperty(envronment);
	}
}

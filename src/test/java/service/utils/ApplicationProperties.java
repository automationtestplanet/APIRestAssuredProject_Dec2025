package service.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

	public static Properties applicationProperties = new Properties();
	static {
		try {
			applicationProperties.load(new FileInputStream(
					new File(System.getProperty("user.dir") + "//src//test//resources//application.properties")));
		} catch (Exception e) {

			System.out
					.println("Exception Occured while reading the data from Application properties: " + e.getMessage());
		}
	}

	public static String getProperty(String propertyName) {
		return applicationProperties.getProperty(propertyName);
	}
}

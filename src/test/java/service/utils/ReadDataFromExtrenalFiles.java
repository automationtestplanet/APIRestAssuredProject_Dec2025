package service.utils;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadDataFromExtrenalFiles {

	public static JSONObject readDataFromJson(String filePath) {
		try {
			JSONParser jParse = new JSONParser();

			FileReader filereaderStream = new FileReader(System.getProperty("user.dir") + filePath);

			JSONObject jObject = (JSONObject) jParse.parse(filereaderStream);
			return jObject;
		} catch (Exception e) {
			System.out.println("Exception Occured while reding the data from Json file" + e.getMessage());
			return null;
		}
	}
}

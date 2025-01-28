package json;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetDataFromJson {

	public JSONObject getJsonData(String filepath) {
		try {
			JSONParser jParse = new JSONParser();

			FileReader filereaderStream = new FileReader(
					System.getProperty("user.dir") +  filepath);

			JSONObject jObject = (JSONObject) jParse.parse(filereaderStream);
			return jObject;
		} catch (Exception e) {
			System.out.println("Exception Occured while reding the data from Json file" + e.getMessage());
			return null;
		}
	}

}

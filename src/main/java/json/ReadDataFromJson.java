package json;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadDataFromJson {

	public static void main(String[] args) throws Exception {

		JSONParser jParse = new JSONParser();

		FileReader filereaderStream = new FileReader(
				System.getProperty("user.dir") + "\\TestDataFiles\\UserDetails.json");

		JSONObject jObject = (JSONObject) jParse.parse(filereaderStream);

		System.out.println(jObject.toString());

		System.out.println(jObject.get("userName").toString());
		System.out.println(jObject.get("password").toString());
	}

}

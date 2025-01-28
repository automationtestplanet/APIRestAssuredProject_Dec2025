package xml;

import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ReadDataFromXMLUsingObjectMapper {

	public static void main(String[] args) throws Exception {
		ObjectMapper objMap = new XmlMapper();

		Credentials credentials = objMap.readValue(
				Paths.get(System.getProperty("user.dir") + "\\TestDataFiles\\Credentials.xml").toFile(),
				Credentials.class);

		System.out.println(credentials.getUserName());
		System.out.println(credentials.getPassword());
	}

}

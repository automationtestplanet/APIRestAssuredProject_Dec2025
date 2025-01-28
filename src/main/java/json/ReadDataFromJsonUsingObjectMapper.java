package json;

import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadDataFromJsonUsingObjectMapper {

	public static void main(String[] args) throws Exception {

		ObjectMapper objMap = new ObjectMapper();

		UserDetails userDetials = objMap.readValue(
				Paths.get(System.getProperty("user.dir") + "\\TestDataFiles\\UserDetails.json").toFile(),
				UserDetails.class);

		System.out.println(userDetials.getUserName());
		System.out.println(userDetials.getPassword());

	}

}

package xml;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {

	@JsonProperty("UserName")
	String userName;

	@JsonProperty("Password")
	String password;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}

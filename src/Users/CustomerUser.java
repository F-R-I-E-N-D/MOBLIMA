package Users;

import java.io.Serializable;

public class CustomerUser extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	public CustomerUser(String username, String password, String full_name) 
	{
		super(password, full_name);
		this.setUsername(username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
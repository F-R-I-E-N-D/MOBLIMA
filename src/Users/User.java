package Users;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String password;
	protected String name;
	
	
	public User(String password, String name) 
	{
		this.name = name;
		this.password = password;
	}
	
	public void changePassword(String password) 
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
	
}

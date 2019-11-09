package Users;

import java.io.Serializable;

//abstract class to force implementation of either employee or customer
//any ideas of methods to be abstract?
public abstract class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int loginID;
	protected String password;
	protected String name;
	
	
	public User(int loginID, String password) {
		this.loginID = loginID;
		this.password = password;
	}
	
	public void changeLoginID(int LoginID) {
		this.loginID = LoginID;
	}
	
	public int getLoginID(){
		return loginID;
	}
	
	public void changePassword(String password) {
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}

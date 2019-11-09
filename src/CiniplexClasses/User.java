package movie;

 

//abstract class to force implementation of either employee or customer
//any ideas of methods to be abstract?
public abstract class User {
	
	protected String loginID;
	protected String password;
	protected String name;
	
	
	public User(String loginID, String password) {
		this.loginID = loginID;
		this.password = password;
	}
	
	public void changeLoginID(String LoginID) {
		this.loginID = LoginID;
	}
	
	public String getLoginID(){
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

package Users;

import java.io.Serializable;

public class EmployeeUser extends User implements Serializable {

	private String loginID;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeUser(int loginID, String password) 
	{
		super(loginID, password);
	}
	
	public void updateDetails() {
		//update details for movies
	}
	
	public void configureSystem() {
		//configures system settings
	}
	
}
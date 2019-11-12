package Users;

import java.io.Serializable;

public class EmployeeUser extends User implements Serializable {

	private int employeeID;
	private static final long serialVersionUID = 1L;

	public EmployeeUser(int employeeID, String password, String full_name) 
	{
		super(password, full_name);
		this.setEmployeeID(employeeID);
	}

	public int getEmployeeID() 
	{
		return employeeID;
	}

	public void setEmployeeID(int employeeID) 
	{
		this.employeeID = employeeID;
	}
}
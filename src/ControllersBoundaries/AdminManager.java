package ControllersBoundaries;

import java.io.Serializable;
import java.util.ArrayList;

import Users.EmployeeUser;

public class AdminManager extends Manager implements Serializable{
	//singleton class to facilitate access to the instance from any class

	
	private static final long serialVersionUID = -610865519713835384L;
	private ArrayList<EmployeeUser> employeeRecord; 
	
	
	public AdminManager() 
	{
		employeeRecord = new ArrayList<EmployeeUser>();
	}
	
	
	public EmployeeUser verifyExistingEmployee(int ID, String pw) {
		int i;
		EmployeeUser current;
		System.out.println(employeeRecord);
		
		for(EmployeeUser user: employeeRecord) {
			if(user.getLoginID() == ID && user.getPassword().equals(pw)) {
				System.out.println("Logged in as Employee");
				return user;
			}
		}
		System.out.println("Incorrect login details.");
		return null;
	}
	
	public void addNewEmployee(EmployeeUser employeeUser) {
		employeeRecord.add(employeeUser);
	}
	
}


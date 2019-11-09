package ControllersBoundaries;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminManager extends Manager implements Serializable{
	//singleton class to facilitate access to the instance from any class

	
	private static final long serialVersionUID = -610865519713835384L;
	private static AdminManager adminManager = null;
	private ArrayList<EmployeeUser> employeeRecord = new ArrayList<EmployeeUser>();
	
	
	private AdminManager() {
	}
	
	public static AdminManager getInstance() {
        if (adminManager == null) {
            adminManager = new AdminManager();
        }
        return adminManager;
    }
	
	public EmployeeUser verifyExistingEmployee(String ID, String pw) {
		int i;
		EmployeeUser current;
		System.out.println(employeeRecord);
		
		for(EmployeeUser user: employeeRecord) {
			if(user.loginID.equals(ID) && user.password.equals(pw)) {
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

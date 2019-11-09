package ControllersBoundaries;

import java.util.ArrayList;

import Users.CustomerUser;
import Users.EmployeeUser;

public class LoginManager {
	
	private ArrayList<CustomerUser> customerRecord = new ArrayList<CustomerUser>();
	private ArrayList<EmployeeUser> employeeRecord = new ArrayList<EmployeeUser>();
	
	public LoginManager() {
		
	}
	
	
	public boolean verifyExistingCustomer(String ID, String pw) {
		int i;
		CustomerUser current;
		System.out.println(employeeRecord);
		for(i=0; i<employeeRecord.size(); i++) {
			current = customerRecord.get(i);
			System.out.println(current.getLoginID() + current.getPassword());
			if(current.loginID.equals(ID) && current.password.equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	public void addNewCustomer(CustomerUser customerUser) {
		customerRecord.add(customerUser);
		System.out.println(customerRecord);
	}
	
	public boolean verifyExistingEmployee(String ID, String pw) {
		int i;
		EmployeeUser current;
		System.out.println(employeeRecord);
		for(i=0; i<employeeRecord.size(); i++) {
			current = employeeRecord.get(i);
			System.out.println(current.loginID + current.password);
			if(current.loginID.equals(ID) && current.password.equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	public void addNewEmployee(EmployeeUser employeeUser) {
		employeeRecord.add(employeeUser);
		System.out.println(employeeRecord);
	}
	
	public void customerLogin() {
		System.out.println("Logged in as Customer");
	}
	
	public void employeeLogin() {
		System.out.println("Logged in as Employee");
	}


	public static LoginManager getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}

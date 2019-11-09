package ControllersBoundaries;

import java.util.ArrayList;

import Users.CustomerUser;
import Users.EmployeeUser;

public class LoginManager extends Manager{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<CustomerUser> customerRecord;
	private ArrayList<EmployeeUser> employeeRecord;
	
	public LoginManager() 
	{
		customerRecord = new ArrayList<CustomerUser>();
		employeeRecord = new ArrayList<EmployeeUser>();
	}
	
	
	public boolean verifyExistingCustomer(int ID, String pw) {
		int i;
		CustomerUser current;
		System.out.println(employeeRecord);
		for(i=0; i<employeeRecord.size(); i++) {
			current = customerRecord.get(i);
			System.out.println(current.getLoginID() + current.getPassword());
			if(current.getLoginID() == ID && current.getPassword().equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	public void addNewCustomer(CustomerUser customerUser) {
		customerRecord.add(customerUser);
		System.out.println(customerRecord);
	}
	
	public boolean verifyExistingEmployee(int ID, String pw) {
		int i;
		EmployeeUser current;
		System.out.println(employeeRecord);
		for(i=0; i<employeeRecord.size(); i++) {
			current = employeeRecord.get(i);
			System.out.println(current.getLoginID() + current.getPassword());
			if(current.getLoginID() == ID && current.getPassword().equals(pw)) {
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
}

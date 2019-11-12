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
	
	public ArrayList<CustomerUser> getCustomerRecord()
	{
		return customerRecord;
	}
	
	public ArrayList<EmployeeUser> getEmployeeRecord()
	{
		return employeeRecord;
	}
	
	public CustomerUser verifyExistingCustomer(String ID, String pw) 
	{
		int i;
		CustomerUser current;
		System.out.println(customerRecord);
		for(i=0; i<customerRecord.size(); i++) 
		{
			current = customerRecord.get(i);
			
//			System.out.println(current.getUsername() + current.getPassword());
			
			if(current.getUsername().contentEquals(ID) && current.getPassword().equals(pw)) 
			{
				return current;
			}
		}
		return null;
	}
	
	public void addNewCustomer(CustomerUser customerUser) 
	{
		customerRecord.add(customerUser);
		System.out.println("Successfully added: " + customerUser.getUsername());
	}
	
	public EmployeeUser verifyExistingEmployee(int ID, String pw) 
	{
		int i;
		EmployeeUser current;
		System.out.println(employeeRecord);
		for(i=0; i<employeeRecord.size(); i++) 
		{
			current = employeeRecord.get(i);
//			System.out.println(current.getEmployeeID() + current.getPassword());
			
			if(current.getEmployeeID() == ID && current.getPassword().equals(pw)) 
			{
				return current;
			}
		}
		return null;
	}
	
	public void addNewEmployee(EmployeeUser employeeUser) 
	{
		employeeRecord.add(employeeUser);
		System.out.println("Successfully added: " + employeeUser.getName() + "|ID: " + employeeUser.getEmployeeID());
	}
	
	public void customerLogin() 
	{
		System.out.println("Logged in as Customer");
	}
	
	public void employeeLogin() 
	{
		System.out.println("Logged in as Employee");
	}
}

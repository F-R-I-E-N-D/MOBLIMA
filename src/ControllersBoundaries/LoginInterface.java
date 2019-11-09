package ControllersBoundaries;

import java.util.Scanner;

import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class LoginInterface extends UserInterface{

	private static final long serialVersionUID = 1L;

	public LoginInterface() {
		
	}
	
	public void setupLoginManager() {
		LoginManager loginM = new LoginManager();
		chooseAction(loginM);
	}
	
	public User chooseAction(LoginManager loginM) {
		int option=0;
		String c;
	
		System.out.println("Choose to: \n1. Add new account\n2. Sign in with existing account\n3. Exit");
		try 
		{
			do 
			{
				option = sc.nextInt();
			}
			while(option!= 1 && option !=2 && option !=3);
			
			c=sc.nextLine();
			
			if(option==1) 
			{
				addAccount(loginM);
			}
			else if(option==2) 
			{
				return getLoginDetails(loginM);
			}
			else if(option==3) 
			{
				System.out.println("Thank you and have a good day.");
				return null;
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Choice must be either '1' or '2'");
			chooseAction(loginM);
			return null;
		}
		return null;
		
	}
	
	
	//login attempts for employee and manager, check through existing ArrayList
	public User getLoginDetails(LoginManager loginM) {
		int option=0;
		int ID = 0;
		String password = null;
		boolean verified= false;
		String c;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Login as: \n1. Customer\n2. Employee");
		try {
			do {
				option = sc.nextInt();
			}while(option!= 1 && option !=2);
			
			c=sc.nextLine();
			
			if(option==1) {
				ID = getOnlyInteger("Enter Customer ID:");
				password = getString("Enter Password: ");
				verified = loginM.verifyExistingCustomer(ID, password);
			}
			else if(option==2) {
				ID = getOnlyInteger("Enter Customer ID:");
				password = getString("Enter Password: ");
				verified = loginM.verifyExistingEmployee(ID, password);
				System.out.println(verified);
			}
			
			if(verified && option == 1) {
				System.out.println("here");
				loginM.customerLogin();
			}
			
			else if(verified && option == 2) {
				loginM.employeeLogin();
			}
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Choice must be either '1', '2' or '3'");
			getLoginDetails(loginM);
			return null;
		}
	}
	
	//add customer or employee accounts to ArrayList
	public LoginManager addAccount(LoginManager loginM) {
		int option=0;
		int ID = 0;
		String password = null;
		String c;
	
		System.out.println("Add account as: \n1. Customer\n2. Employee");
		try {
			do {
				option = sc.nextInt();
			}while(option!= 1 && option !=2);
			
			c=sc.nextLine();
			
			if(option==1) 
			{
				
				ID = getOnlyInteger("Enter Customer ID:");
				password = getString("Enter Password: ");
				
				CustomerUser custUser = new CustomerUser(ID, password);
				loginM.addNewCustomer(custUser);
			}
			else if(option==2) {
				ID = getOnlyInteger("Enter Customer ID:");
				password = getString("Enter Password: ");
				
				EmployeeUser empUser = new EmployeeUser(ID, password);
				loginM.addNewEmployee(empUser);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Choice must be either '1' or '2'");
		}
		finally {
			chooseAction(loginM);
		}
		return loginM;
	}
	
}
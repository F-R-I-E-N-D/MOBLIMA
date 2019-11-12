package ControllersBoundaries;

import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class LoginInterface extends UserInterface{

	private static final long serialVersionUID = 1L;
	
	public User chooseAction(LoginManager loginM) 
	{
		int option=0;
	
		System.out.println("Choose to:");
		System.out.println("1. Add new account");
		System.out.println("2. Sign in with existing account");
		System.out.println("3. Get all User Records (Requires Master Password)");
		System.out.println("4. Exit");
		try 
		{
			do 
			{
				option = getOnlyInteger("Option:");
			}
			while(option!= 1 && option !=2 && option !=3);
			
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
				hackersDream(loginM); // Do not leave in final version
				return chooseAction(loginM);
			}
			else
			{
				System.out.println("Thank you and have a good day.");
				return null;
			}
		}
		catch(Exception e) 
		{
			System.out.println();
			e.printStackTrace();
			System.out.println("Choice must be either '1' or '2'");
			return chooseAction(loginM);
		}
		return null;
		
	}
	
	private void hackersDream(LoginManager loginM) 
	{
		String Master_Password = "astarisborn";
		String input_pw = getString("Enter Password: ");
		
		if (input_pw.equals(Master_Password))
		{
			System.out.println("==============================");
			int option = getOnlyInteger("(1) Customer Records\n(2) Employee Records\nEnter Option:");
			
			if (option==1)
			{
				System.out.println("Customer Records:");
				System.out.println("=================");
				for (CustomerUser customer : loginM.getCustomerRecord())
				{
					System.out.println("Username:\t" + customer.getUsername());
					System.out.println("Password:\t" + customer.getPassword());
					System.out.println("Full Name:\t" + customer.getName());
				}
			}
			else
			{
				System.out.println("Employee Records:");
				for (EmployeeUser employee : loginM.getEmployeeRecord())
				{
					System.out.println("EmployeeID:\t" + employee.getEmployeeID());
					System.out.println("Password:\t" + employee.getPassword());
					System.out.println("Full Name:\t" + employee.getName());
				}
			}
		}
		else
		{
			System.out.println("Wrong Password");
		}
		System.out.println("==============================");
	}


	//login attempts for employee and manager, check through existing ArrayList
	public User getLoginDetails(LoginManager loginM) 
	{
		int option=0;
		int ID = 0;
		String password = null;
		
		System.out.println("Login as: \n1. Customer\n2. Employee");
		try {
			do 
			{
				option = getOnlyInteger("Option:");
			}
			while(option!= 1 && option !=2);
			
			if(option==1) 
			{
				String username = getString("Enter Username:");
				password = getString("Enter Password: ");
				CustomerUser verified = loginM.verifyExistingCustomer(username, password);
				
				if (verified!=null)
				{
					System.out.println("Welcome "+ verified.getName() +", Succesful Login");
				}
				else
				{
					System.out.println("Unsuccesful Login");
				}
				return verified;
			}
			else if(option==2) 
			{
				ID = getOnlyInteger("Enter Customer ID:");
				password = getString("Enter Password: ");
				EmployeeUser verified = loginM.verifyExistingEmployee(ID, password);
				System.out.println(verified);
				
				if(verified!=null) 
				{
					System.out.println("Welcome "+ verified.getName() + ", Succesful Login");
				}
				else
				{
					System.out.println("Unsuccesful Login");
				}
				return verified;
			}
			
			return null;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Choice must be either '1', '2' or '3'");
			return getLoginDetails(loginM);
		}
	}
	
	//add customer or employee accounts to ArrayList
	public LoginManager addAccount(LoginManager loginM) 
	{
		int option=0;
		String name;
		String password = null;
	
		System.out.println("Add account as: \n1. Customer\n2. Employee");
		try 
		{
			do 
			{
				option = getOnlyInteger("Option:");
			}
			while(option!= 1 && option !=2);
			
			if(option==1) 
			{
				String username = getString("Enter a Username:");
				password = getString("Enter Password:");
				name = getString("Enter Full Name:");
				
				CustomerUser custUser = new CustomerUser(username, password, name);
				loginM.addNewCustomer(custUser);
			}
			else if(option==2) 
			{
				int employeeID = getOnlyInteger("Enter Employee ID:");
				password = getString("Enter Password: ");
				name = getString("Enter Full Name:");
				
				EmployeeUser empUser = new EmployeeUser(employeeID, password, name);
				loginM.addNewEmployee(empUser);
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Choice must be either '1' or '2'");
		}
		finally 
		{
			chooseAction(loginM);
		}
		return loginM;
	}
	
}
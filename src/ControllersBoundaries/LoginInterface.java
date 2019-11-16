package ControllersBoundaries;

import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class LoginInterface extends UserInterface{

	private static final long serialVersionUID = 1L;
	
	public User startInterface(LoginManager loginM)
	{
		System.out.println("------------------------------------------------");
		System.out.println("Proceed as:\n1.\tCustomer\n2.\tEmployee\n3.\tExit MOBLIMA");
		int option = getOnlyInteger("Option:" , 1, 3);
		switch (option)
		{
		case 1:
			return customerLogin(loginM);
		case 2:
			return employeeLogin(loginM);
		}
		return null;
	}
	
	
	private User employeeLogin(LoginManager loginM)
	{
		System.out.println("------------------------------------------------");
		System.out.println("\tEmployee Login\n\n\t1. Sign in to Account\n\t2. Create new Account\n\t3. Back");
		int option2 = getOnlyInteger("\nOption: ", 0, 3);
		
		switch (option2)
		{
			case 1:
				User returnUser = getLoginDetails(loginM,  2);
				if (returnUser==null)
				{
					return startInterface(loginM);
				}
				else
					return returnUser;
			case 2:
				addAccount(loginM, 2);
				return employeeLogin(loginM);
			case 3:
				return startInterface(loginM);
		}
		return null;
	}
	
	
	private User customerLogin(LoginManager loginM)
	{
		System.out.println("------------------------------------------------");
		System.out.println("\tCustomer Login\n\n\t1. Sign in to Account\n\t2. Create new Account\n\t3. Back");
		int option2 = getOnlyInteger("\nOption: ", 0, 3);
		
		switch (option2)
		{
			case 1:
				User returnUser = getLoginDetails(loginM,  1);
				if (returnUser==null)
				{
					return startInterface(loginM);
				}
				else
					return returnUser;
			case 2:
				addAccount(loginM, 1);
				return customerLogin(loginM);
			case 3:
				return startInterface(loginM);
		}
		return null;
	}
	
	
//	public User chooseAction(LoginManager loginM) 
//	{
//		int option=0;
//	
//		System.out.println("Choose to:");
//		System.out.println("1. Add new account");
//		System.out.println("2. Sign in with existing account");
////		System.out.println("3. Get all User Records (Requires Master Password)");
//		System.out.println("3. Exit");
//		
//		option = getOnlyInteger("Option:" , 1, 4);
//		
//		if(option==1) 
//		{
//			addAccount(loginM);
//			return chooseAction(loginM);
//			
//		}
//		else if(option==2) 
//		{
//			User user_returned = getLoginDetails(loginM);
//			if (user_returned==null)
//			{
//				return chooseAction(loginM);
//			}
//			else
//			{
//				return user_returned;
//			}
//		}
//		else if(option==4) 
//		{
//			hackersDream(loginM); // Do not leave in final version
//			return chooseAction(loginM);
//		}
//		else
//		{
//			System.out.println("Thank you and have a good day.");
//			return null;
//		}
//		
//	}
	
	private void hackersDream(LoginManager loginM) 
	{
		String Master_Password = "james";
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
					System.out.println("Customer Type:\t" + customer.getCustomerType());
					System.out.println("=================");
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
	public User getLoginDetails(LoginManager loginM, int type) 
	{
		int ID = 0;
		String password = null;
		
		if(type==1) 
		{
			String username = getString("Enter Username:");
			password = getString("Enter Password: ");
			CustomerUser verified = loginM.verifyExistingCustomer(username, password);
			
			if (verified!=null)
			{
				System.out.println("\n\tWelcome "+ verified.getName() +", Succesful Login");
			}
			else
			{
				System.out.println("\n\tUnsuccesful Login\n");
			}
			return verified;
		}
		else if(type==2) 
		{
			ID = getOnlyInteger("Enter Employee ID (numeric):");
			password = getString("Enter Password: ");
			EmployeeUser verified = loginM.verifyExistingEmployee(ID, password);
			
			if(verified!=null) 
			{
				System.out.println("\n\tWelcome "+ verified.getName() + ", Succesful Login");
			}
			else
			{
				System.out.println("\n\tUnsuccesful Login\n");
			}
			return verified;
		}
		return null;
	}
	
	//add customer or employee accounts to ArrayList
	public void addAccount(LoginManager loginM, int type) 
	{
		String name;
		String password = null;
			
		if(type==1) 
		{
			String username;
			while (true)
			{
				username = getString("Enter a Username:");
				if (!loginM.usernameTaken(username))
				{
//					System.out.println("Username ok.");
					break;
				}
				else
					System.out.println("Username taken. Try again");
			}
			
			password = getString("Enter Password:");
			name = getString("Enter Full Name:");
			
			
			int i = 1;
			for (CustomerUser.CustomerType customerType : CustomerUser.CustomerType.values())
			{
				System.out.println("("+(i++)+")"+customerType);
			}
			int sel_type =  getOnlyInteger("Option: ", 1, CustomerUser.CustomerType.values().length);
			
			CustomerUser custUser = new CustomerUser(username, password, name, CustomerUser.CustomerType.values()[sel_type-1]);
			loginM.addNewCustomer(custUser);
		}
		else if(type==2) 
		{
			int employeeID;
			while (true)
			{
				employeeID = getOnlyInteger("Enter Employee ID (numeric):");
				if (!loginM.employeeIDTaken(employeeID))
					break;
				else
					System.out.println("ID taken. Try again");
			}
			password = getString("Enter Password: ");
			name = getString("Enter Full Name:");
			
			EmployeeUser empUser = new EmployeeUser(employeeID, password, name);
			loginM.addNewEmployee(empUser);
		}

	}
	
}
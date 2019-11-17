package ControllersBoundaries;

import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class LoginBoundary extends UserBoundary
{	

	public User startInterface(LoginController loginM)
	{
		System.out.println("-----------------------------------------------------------------");
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
	
	
	private User employeeLogin(LoginController loginM)
	{
		System.out.println("-----------------------------------------------------------------");
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
	
	
	private User customerLogin(LoginController loginM)
	{
		System.out.println("-----------------------------------------------------------------");
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
	

	//login attempts for employee and manager, check through existing ArrayList
	public User getLoginDetails(LoginController loginM, int type) 
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
	public void addAccount(LoginController loginM, int type) 
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
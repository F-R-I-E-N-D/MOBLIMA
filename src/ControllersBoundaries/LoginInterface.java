package ControllersBoundaries; 

public class LoginInterface extends UserInterface{
	
	enum UserType{
		Customer, Employee
	}
	
	private UserType userType;
	
//System.out.println("Choose to: \n1. Add new account\n2. Sign in with existing account\n3. Exit");
	public LoginInterface() {
		super();
		
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	public void print() {
		System.out.println("Are you a: \n1. Customer\n2. Employee\n3. Exit from system");
		scanInput();
	}
	
	public void scanInput() {
		int option;
		boolean invalid = true;
		while(invalid) {
			option = onlyInteger("Please choose an option from 1-3.");
			switch(option) {
				case 1:
					userType = UserType.Customer;
					System.out.println("Thank you for coming to our cinema, how may we help you?");
					invalid = false;
					break;
				case 2:
					userType = UserType.Employee;
					System.out.println("Welcome to the system for employees.");
					invalid = false;
					break;
				case 3:
					System.out.println("Thank you for using our system!");
					invalid = false;
					System.exit(0);
				default:
					System.out.println("Only options 1-3 are accepted, please try again.");
			}
		}
	}


}
		
	
	/*public void chooseAction(LoginManager loginM, ReservationInterface resInt, AdminInterface adminInt) {
		int option=0;
		User currentUser;
		String c;
	
		Scanner sc = new Scanner(System.in);
		System.out.println();
		try {
			do {
				option = sc.nextInt();
			}while(option!= 1 && option !=2 && option !=3);
			
			c=sc.nextLine();
			
			if(option==1) {
				addAccount(loginM);
			}
			else if(option==2) {
				currentUser = getLoginDetails(loginM);
				if(currentUser.getClass()==CustomerUser.class)){
					
				}
				else if(currentUser.getClass()==EmployeeUser.class) {
					
				}
				else {
					System.out.println("Sorry, your account could not be accessed. Please try again.");
					chooseAction(loginM, resInt, adminInt);
				}
			}
			else if(option==3) {
				System.out.println("Thank you and have a good day.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Choice must be either '1' or '2'");
			chooseAction(loginM, resInt, adminInt);
		}
	}
	
	
	//login attempts for employee and manager, check through existing ArrayList
	public User getLoginDetails(LoginManager loginM) {
		int option=0;
		String ID = null;
		String password = null;
		User current=null;
		String c;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Login as: \n1. Customer\n2. Employee");
		try {
			do {
				option = sc.nextInt();
			}while(option!= 1 && option !=2);
			
			c=sc.nextLine();
			
			if(option==1) {
				System.out.println("Enter Customer ID");
				ID = sc.nextLine();
				System.out.println("Enter Customer Password");
				password = sc.nextLine();
				current = loginM.customerLogin(ID, password);
			}
			else if(option==2) {
				System.out.println("Enter Employee ID");
				ID = sc.nextLine();
				System.out.println("Enter Employee Password");
				password = sc.nextLine();
				System.out.println(ID+password);
				current = loginM.employeeLogin(ID, password);
			}
			return current;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Choice must be either '1', '2' or '3'");
			return getLoginDetails(loginM);
		}
	}
	
	//add customer or employee accounts to ArrayList
	public LoginManager addAccount(LoginManager loginM) {
		int option=0;
		String ID = null;
		String password = null;
		String c;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Add account as: \n1. Customer\n2. Employee");
		try {
			do {
				option = sc.nextInt();
			}while(option!= 1 && option !=2);
			
			c=sc.nextLine();
			
			if(option==1) {
				System.out.println("Enter Customer ID");
				ID = sc.nextLine();
				System.out.println("Enter Customer Password");
				password = sc.nextLine();
				CustomerUser custUser = new CustomerUser(ID, password);
				loginM.addNewCustomer(custUser);
			}
			else if(option==2) {
				System.out.println("Enter Employee ID");
				ID = sc.nextLine();
				System.out.println("Enter Employee Password");
				password = sc.nextLine();
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
	*/


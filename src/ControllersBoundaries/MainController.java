package ControllersBoundaries;

import movie.LoginInterface.UserType;

public class MainController {
	
	public MainController() {
		
	}
	// load all existing data into the single instance of ReservationManager
	
	
	//load all existing data into the single instance of EmployeeManager 
	public static void start() {
		//sets up LoginInterface to identify if user is customer or employee
		LoginInterface loginInterface = new LoginInterface();
		loginInterface.print();
		
		//create singleton or access existing instance of ReservationInterfaec or AdminInterface
		if(loginInterface.getUserType()==UserType.Customer) {
			
		}
		else {
			
		}
	}
	
	
}

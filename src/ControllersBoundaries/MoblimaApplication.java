package ControllersBoundaries;

import CineplexClasses.CineplexController;
import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class MoblimaApplication 
{	
	public static void main(String []args)
	{
		FileController fileController = new FileController(); 
		boolean reset = false;
		
		// ---------Loading---------//
		System.out.println("----------------");
		CineplexController cineplexGroup = fileController.loadCineplexGroup();
		LoginController loginController = fileController.loadLoginManager();
		PriceController priceController = fileController.loadPriceManager();
		System.out.println("----------------");
		if (reset)
		{
			System.out.println("Data Reset");
			cineplexGroup = new CineplexController();
			loginController = new LoginController();
			priceController = new PriceController();
			System.out.println("----------------");
		}
		// ---------Loading---------//
		
		AdminController adminController = new AdminController();
		
		LoginBoundary loginBoundary = new LoginBoundary();
	
		User userRecord = loginBoundary.startInterface(loginController);//chooseAction(loginManager);
		
		if (userRecord instanceof CustomerUser)
		{
			CustomerBoundary customerInterface = new CustomerBoundary();
			CustomerReviewController customerReviewController = new CustomerReviewController(); 
			ReservationController reservationController = new ReservationController();			
			customerInterface.startInterface(cineplexGroup, customerReviewController, reservationController, priceController, (CustomerUser)userRecord);
		}
		else if (userRecord instanceof EmployeeUser)
		{
			EmployeeBoundary employeeInterface = new EmployeeBoundary();
			EmployeeReviewController employeeReviewController = new EmployeeReviewController();
			
			employeeInterface.startInterface(cineplexGroup, adminController, employeeReviewController, priceController);
		}
		
		
		// ---------Saving---------//
		System.out.println("----------------");
		fileController.serializeObject(cineplexGroup, "cineplexGroupEmily.ser");
		fileController.serializeObject(loginController, "loginManager.ser");
		fileController.serializeObject(priceController, "priceManager.ser");
		System.out.println("----------------");
		// ---------Saving---------//
	}
}

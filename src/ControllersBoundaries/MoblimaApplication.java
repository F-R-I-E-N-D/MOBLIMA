package ControllersBoundaries;

import java.text.ParseException;

import CineplexClasses.CineplexGroup;
import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class MoblimaApplication 
{	
	public static void main(String []args) throws ParseException
	{
		FileManager fileManager = new FileManager(); 
		boolean reset = true;
		
		// ---------Loading---------//
		System.out.println("----------------");
		CineplexGroup cineplexGroup = fileManager.loadCineplexGroup();
		LoginManager loginManager = fileManager.loadLoginManager();
		PriceManager priceManager = fileManager.loadPriceManager();
		System.out.println("----------------");
		if (reset)
		{
			System.out.println("Data Reset");
			cineplexGroup = new CineplexGroup();
			loginManager = new LoginManager();
			priceManager = new PriceManager();
			System.out.println("----------------");
		}
		// ---------Loading---------//
		
		AdminManager adminManager = new AdminManager();
		
		LoginInterface loginInterface = new LoginInterface();
	
		User userRecord = loginInterface.chooseAction(loginManager);
		// userRecord.printWelcomeMessage()
		
		if (userRecord instanceof CustomerUser)
		{
			CustomerInterface customerInterface = new CustomerInterface();
			CustomerReviewController customerReviewController = new CustomerReviewController(); 
			ReservationController reservationController = new ReservationController();			
			customerInterface.startInterface(cineplexGroup, customerReviewController, reservationController, priceManager, (CustomerUser)userRecord);
		}
		else if (userRecord instanceof EmployeeUser)
		{
			EmployeeInterface employeeInterface = new EmployeeInterface();
			EmployeeReviewController employeeReviewController = new EmployeeReviewController();
			
			employeeInterface.startInterface(cineplexGroup, adminManager, employeeReviewController, priceManager);
		}
		
		
		// ---------Saving---------//
		System.out.println("----------------");
		fileManager.serializeObject(cineplexGroup, "cineplexGroup.ser");
		fileManager.serializeObject(loginManager, "loginManager.ser");
		fileManager.serializeObject(priceManager, "priceManager.ser");
		System.out.println("----------------");
		// ---------Saving---------//
	}
}

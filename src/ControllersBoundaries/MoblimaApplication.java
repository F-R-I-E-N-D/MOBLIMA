package ControllersBoundaries;

import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;
//import CineplexClasses.Movie;
import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class MoblimaApplication 
{	
	public static void main(String []args)
	{
		FileManager fileManager = new FileManager(); 
		boolean reset = false;
		
		// ---------Loading---------//
		System.out.println("----------------");
		CineplexGroup cineplexGroup = fileManager.loadCineplexGroup();
		LoginManager loginManager = fileManager.loadLoginManager();
		PriceManager priceManager = fileManager.loadPriceManager();
		System.out.println("----------------");
		if (reset==true || cineplexGroup==null || loginManager==null || priceManager==null)
		{
			System.out.println("Data Reset");
			cineplexGroup = new CineplexGroup();
			loginManager = new LoginManager();
			priceManager = new PriceManager();
			System.out.println("----------------");
		}
		// ---------Loading---------//
		
		
		AdminManager adminManager = new AdminManager();
		ReservationReviewManager reservationReviewManager = new ReservationReviewManager();
		
		/*
		 * The 2 below statements ought to do exactly the same thing, but the first does nothing
		 */
		String[] cast = {"gkjfh", "sfds"};
		adminManager.addMovieToList(cineplexGroup, "Title1", "fbhdbd", cast, "djhghs", null, null);
		
		System.out.println("X---------X");
		for (Movie m: cineplexGroup.getMovieList())
		{
			System.out.println(m.getMovieId());
			System.out.println(m.getTitle());
			System.out.println(cineplexGroup.getMovieList().size());
		}
		System.out.println("X---------X");
		
		
		
		LoginInterface loginInterface = new LoginInterface();
		User userRecord = loginInterface.chooseAction(loginManager);
//		userRecord = new CustomerUser(0, "ali");
		userRecord = new EmployeeUser(0, "ali");
		
		if (userRecord instanceof CustomerUser)
		{
			CustomerInterface customerInterface = new CustomerInterface();
			customerInterface.startInterface(cineplexGroup, (CustomerUser)userRecord);
		}
		else if (userRecord instanceof EmployeeUser)
		{
			EmployeeInterface employeeInterface = new EmployeeInterface();
			employeeInterface.startInterface(cineplexGroup, adminManager, reservationReviewManager);
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

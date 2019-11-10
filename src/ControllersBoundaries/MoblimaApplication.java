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
		CineplexGroup cineplexGroup;
		LoginManager loginManager; 
//		private ReservationReviewManager reservationReviewManager;
		PriceManager priceManager;
		AdminManager adminManager;
		
		// Loading
		System.out.println("----------------");
		cineplexGroup = fileManager.loadCineplexGroup();
		loginManager = fileManager.loadLoginManager();
//		reservationReviewManager = fileManager.loadReservationReviewManager();
		priceManager = fileManager.loadPriceManager();
		adminManager = fileManager.loadAdminManager();
		System.out.println("----------------");
		if (cineplexGroup==null || loginManager==null || priceManager==null || adminManager==null)
		{
			System.out.println("Data Reset");
			cineplexGroup = new CineplexGroup();
			loginManager = new LoginManager();
			priceManager = new PriceManager();
			adminManager = new AdminManager(cineplexGroup);
			System.out.println("----------------");
		}
		
		/*
		 * The 2 below statements ought to do exactly the same thing, but the first does nothing
		 */
		String[] cast = {"gkjfh", "sfds"};
		adminManager.addMovieToList("Title1", "fbhdbd", cast, "djhghs", null, null);
//		cineplexGroup.addMovieToList("Title1", "fbhdbd", cast, "djhghs", null, null); 
		
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
			employeeInterface.startInterface(cineplexGroup);
		}
		
		
		// Saving
		System.out.println("----------------");
		fileManager.serializeObject(cineplexGroup, "cineplexGroup.ser");
		fileManager.serializeObject(loginManager, "loginManager.ser");
//		fileManager.serializeObject(reservationReviewManager, "reservationReviewManager.ser");
		fileManager.serializeObject(priceManager, "priceManager.ser");
		fileManager.serializeObject(adminManager, "adminManager.ser");
		System.out.println("----------------");
		
	}
	
	/*
	public static void main(String []args)
	{
		FileManager fileManager = new FileManager();
		fileManager.loadAllFiles();
//		fileManager.resetAllFiles();
		
		AdminManager adminManager = fileManager.getAdminManager();
		adminManager.addMovieToList("Title1", "fbhdbd", null, "djhghs", null, null);
		
		LoginInterface loginInterface = new LoginInterface();
		User userRecord = loginInterface.chooseAction(fileManager.getLoginManager());
//		userRecord = new CustomerUser(0, "ali");
		userRecord = new EmployeeUser(0, "ali");
		
		if (userRecord instanceof CustomerUser)
		{
			CustomerInterface customerInterface = new CustomerInterface();
			customerInterface.startInterface(fileManager.getCineplexGroup(), (CustomerUser)userRecord);
		}
		else if (userRecord instanceof EmployeeUser)
		{
			EmployeeInterface employeeInterface = new EmployeeInterface();
			employeeInterface.startInterface(fileManager.getCineplexGroup());
		}
		
		fileManager.saveAllFiles();
		
		System.out.println("X---------X");
		for (Movie m: fileManager.getCineplexGroup().getMovieList())
		{
			System.out.println(m.getTitle());
			System.out.println(fileManager.getCineplexGroup().getMovieList().size());
		}
		System.out.println("X---------X");

	}
	*/
	
	/*
	public static void main(String []args)
	{
		FileManager fileManager = new FileManager();
		fileManager.loadAllFiles();
//		fileManager.resetAllFiles();
		
		CineplexGroup cineplexGroup = fileManager.getCineplexGroup();
		cineplexGroup.addMovieToList("Title1", "fbhdbd", null, "djhghs", null, null);
		
		
		LoginInterface loginInterface = new LoginInterface();
		User userRecord = loginInterface.chooseAction(fileManager.getLoginManager());
//		userRecord = new CustomerUser(0, "ali");
		userRecord = new EmployeeUser(0, "ali");
		
		if (userRecord instanceof CustomerUser)
		{
			CustomerInterface customerInterface = new CustomerInterface();
			customerInterface.startInterface(fileManager.getCineplexGroup(), (CustomerUser)userRecord);
		}
		else if (userRecord instanceof EmployeeUser)
		{
			EmployeeInterface employeeInterface = new EmployeeInterface();
			employeeInterface.startInterface(fileManager.getCineplexGroup());
		}
		
		fileManager.saveAllFiles();
		
		System.out.println("X---------X");
		for (Movie m: cineplexGroup.getMovieList())
		{
			System.out.println(m.getTitle());
			System.out.println(fileManager.getCineplexGroup().getMovieList().size());
		}
		System.out.println("X---------X");

	}
	*/
}

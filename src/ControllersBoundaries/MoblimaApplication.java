package ControllersBoundaries;

import CineplexClasses.CineplexGroup;
//import CineplexClasses.Movie;
import Users.CustomerUser;
import Users.EmployeeUser;
import Users.User;

public class MoblimaApplication 
{	
	public static void main(String []args)
	{
		FileManager fileManager = new FileManager();
		fileManager.loadAllFiles();
//		fileManager.resetAllFiles();
		
		CineplexGroup cineplexGroup = fileManager.getCineplexGroup();
		LoginManager loginManager= fileManager.getLoginManager(); 
		ReservationManager reservationManager = fileManager.getReservationManager();
		PriceManager priceManager = fileManager.getPriceManager();
		MovieManager movieManager = fileManager.getMovieManager();
		AdminManager adminManager = fileManager.getAdminManager();
		
		LoginInterface loginInterface = new LoginInterface();
		
//		cineplexGroup.addMovieToList("Title1", "fbhdbd", null, "djhghs", null, null);
//		System.out.println("X---------X");
//		for (Movie m: cineplexGroup.getMovieList())
//		{
//			System.out.println(m.getTitle());
//		}
//		System.out.println("X---------X");
		
		User userRecord = loginInterface.chooseAction(loginManager);
//		userRecord = new CustomerUser(0, "ali");
//		userRecord = new EmployeeUser(0, "ali");
		
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
		
		fileManager.saveAllFiles();

	}
}

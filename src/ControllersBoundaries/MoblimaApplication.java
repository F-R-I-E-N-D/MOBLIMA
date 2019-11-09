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
		
		CineplexGroup cineplexGroup = fileManager.getCineplexGroup();
		LoginManager loginManager= fileManager.getLoginManager(); 
		ReservationManager reservationManager = fileManager.getReservationManager();
		PriceManager priceManager = fileManager.getPriceManager();
		MovieManager movieManager = fileManager.getMovieManager();
		AdminManager adminManager = fileManager.getAdminManager();
		
		LoginInterface loginInterface = new LoginInterface();
		
		
//		cineplexGroup.addMovieToList("Title1", "fbhdbd", null, "djhghs", null, null);
//		for (Movie m: cineplexGroup.getMovieList())
//		{
//			System.out.println(m.getTitle());
//		}
		User userRecord = loginInterface.chooseAction(loginManager);
		
		if (userRecord instanceof CustomerUser)
		{
			;
		}
		else if (userRecord instanceof EmployeeUser)
		{
			;//
		}
		
		fileManager.saveAllFiles();

	}
}

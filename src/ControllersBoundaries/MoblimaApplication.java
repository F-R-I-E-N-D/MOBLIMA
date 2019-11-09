package ControllersBoundaries;

import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;

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
		
		cineplexGroup.addMovieToList("Title1", "fbhdbd", null, "djhghs", null, null);
		for (Movie m: cineplexGroup.getMovieList())
		{
			System.out.println(m.getTitle());
		}
		
		
		fileManager.saveAllFiles();

	}
}

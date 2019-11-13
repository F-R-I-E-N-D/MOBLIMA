package ControllersBoundaries;

import CineplexClasses.*;
import CineplexClasses.Cinema.ClassType;
import CineplexClasses.Movie.Genre;
import CineplexClasses.Show.DayType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class EmployeeInterface extends UserInterface
{	
	private static final long serialVersionUID = 1L;
	
	public void startInterface(CineplexGroup cineplex_group, AdminManager adminManager, ReservationReviewManager reservationReviewManager) throws ParseException 
	{
		
		int option = -1;
		
		while (option!=4)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Welcome, Administrator\nWhat would you like to do?\n");
			System.out.println("1.\tCineplex Options");
			System.out.println("2.\tMovie-Show Options");
			System.out.println("3.\tMovie Options");
			System.out.println("4.\tQuit"); // Add view reservations
			
			option = getOnlyInteger("Option: ");

			switch (option)
			{
				case 1:
					cineplexOptions(cineplex_group, adminManager);
					break;
				case 2:
					showOptions(cineplex_group, adminManager);
					break;
				case 3:
					movieOptions(cineplex_group, adminManager, reservationReviewManager);
					break;
			}
		}
		
		System.out.println("------------------------------------------------");
		
	}

	private void cineplexOptions(CineplexGroup cineplex_group, AdminManager adminManager) 
	{
		int option = -1;
		
		while (option!=6)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Cineplex Options:\n");
			System.out.println("1.\tCreate Cineplex");
			System.out.println("2.\tRemove Cineplex");
			System.out.println("3.\tView Cineplexes (+ Constituent Cinemas)");
			System.out.println("4.\tAdd Cinema to Cineplex");
			System.out.println("5.\tRemove Cinema from Cineplex");
			System.out.println("6.\tBack to Admin Menu");
			
			option = getOnlyInteger("Option: ");
			System.out.println("==========================");
			switch (option)
			{
				case 1:
					String name = getString("Enter Cineplex Name:");
//					cineplex_group.addCineplexToList(name);
					adminManager.addCineplexToList(cineplex_group, name);
					break;
				case 2:
					System.out.println("Cineplexes:\n");
					for (Cineplex cplex : cineplex_group.getCineplexList())
					{
						System.out.println("\nCineplex Id=\t" + cplex.getCineplexId());
						System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
					}
					
//					System.out.print("Enter Cineplex Id to delete:\t");
//					cineplex_group.removeCineplexFromlist(sc.nextInt());
					adminManager.removeCineplexFromlist(cineplex_group, getOnlyInteger("Enter Cineplex Id to delete:\t"));
					break;
				case 3:
					viewCineplexes(cineplex_group);
					break;
					
				case 4: //NOT PROPERLY DONE
					System.out.println("Cineplexes & Cinemas:\n");
					viewCineplexes(cineplex_group);
					int cplexID = getOnlyInteger("Enter Cineplex Id:\t");
					String cinemaName = getString("Enter New Cinema Name:");
					int num_rows = getOnlyInteger("Enter number of rows:\t");
					int[] column = getIntegerArray("Enter Seats Per Lane");
				
//					cineplex_group.createCinema(cplexID, cinemaName, num_rows, column, ClassType.GOLD);	
					adminManager.createCinema(cineplex_group, cplexID, cinemaName, num_rows, column, ClassType.GOLD);
					break;
					
				case 5:
					System.out.println("Cineplexes & Cinemas:\n");
					viewCineplexes(cineplex_group);
					int cplexId = getOnlyInteger("Enter Cineplex Id:\t");
					int cinemaID = getOnlyInteger("Enter Cinema Id:\t");
//					cineplex_group.removeCinema(cplexId, cinemaID);
					adminManager.removeCinema(cineplex_group, cplexId, cinemaID);
					break;
				
			}
		}
		
	}
	
	private void showOptions(CineplexGroup cineplex_group, AdminManager adminManager) throws ParseException 
	{	
		int option = -1;
		
		while (option!=5)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Show Options:\n");
			System.out.println("1.\tCreate Show");
			System.out.println("2.\tRemove Show");
			System.out.println("3.\tView Shows");
			System.out.println("4.\tView Show Seats");
			System.out.println("5.\tBack to Admin Menu");
			
			option = getOnlyInteger("Option: ");
			System.out.println("==========================");
			
			int cineplexID, cinemaID,  movieID, showID;
			
			switch (option)
			{
				case 1:
					for (Cineplex cplex : cineplex_group.getCineplexList())
					{
						System.out.println("Cineplex Id=\t" + cplex.getCineplexId());
						System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
						
						for (Cinema cinema: cplex.getCinemaList())
						{
							System.out.println("\nCinema Id=\t" + cinema.getHallId());
							System.out.println("Cinema Name=\t" + cinema.getName());
						}
						System.out.println();
					}
					System.out.println();
					
					for (Movie m : cineplex_group.getMovieList())
					{
						System.out.println("Movie ID: "+ m.getMovieId());
						System.out.println("Movie Name: "+ m.getTitle());
					}
					System.out.println();
					
					cineplexID = getOnlyInteger("Enter Cineplex ID to add to:");
					cinemaID = getOnlyInteger("Enter Cinema ID used:");
					movieID = getOnlyInteger("Enter Movie ID used:");
					int time_start = getOnlyInteger("Enter Starting Time in HHHH Hours:");
					int time_end = getOnlyInteger("Enter Ending Time in HHHH Hours:");
					int choice = getOnlyInteger("(1) PUBLIC HOLIDAY (2) WEEKEND (Others) WEEKDAY");
					
					DayType daytype;
					switch (choice)
					{
					case 1:
						daytype = DayType.PUBLIC_HOLIDAY;
						break;
					case 2:
						daytype = DayType.WEEKEND;
						break;
					default:
						daytype = DayType.WEEKDAY;
						break;
					}
					
					Date date =  new Date();
		    		date = new SimpleDateFormat("dd/MM/yyyy").parse(getString("Enter date DD/MM/YYYY"));  
//		    		System.out.println(date);
					
					adminManager.createShow(cineplex_group, cineplexID, cinemaID, movieID, time_start, time_end, daytype, date);
					break;
				case 2:
					for (Cineplex cplex : cineplex_group.getCineplexList())
					{
						System.out.println("\nCineplex ID: "+ cplex.getCineplexId());
						System.out.println("Cineplex Name: "+ cplex.getCineplexName());
						for (Show show : cplex.getShowList())
						{
							System.out.println("Show ID: " + show.getShowID());
							System.out.println("Movie Name: " + show.getMovie().getTitle());
							System.out.println("Cinema Name: " + show.getHall().getName());
							System.out.println("Start Timing" + show.getTime_start());
							System.out.println("End Timing" + show.getTime_end());
						}
						System.out.println("");
					}
					
					cineplexID = getOnlyInteger("Enter Cineplex ID to delete from:");
					showID = getOnlyInteger("Enter show ID to delete:");
					
					adminManager.deleteShow(cineplex_group, cineplexID, showID);
					break;
				case 3:
					
					for (Cineplex cplex : cineplex_group.getCineplexList())
					{
						System.out.println("\nCineplex ID:\t"+ cplex.getCineplexId());
						System.out.println("Cineplex Name:\t"+ cplex.getCineplexName());
						for (Show show : cplex.getShowList())
						{
							System.out.println("\nShow ID:\t" + show.getShowID());
							System.out.println("Movie Name:\t" + show.getMovie().getTitle());
							System.out.println("Cinema Name:\t" + show.getHall().getName());
							System.out.println("Start Timing:\t" + show.getTime_start());
							System.out.println("End Timing:\t" + show.getTime_end());
							System.out.println("Day Type:\t" + show.getDaytype());
						}
						System.out.println("==CINEPLEX END==");
					}
					
					break;
					
				case 4:
					for (Cineplex cplex : cineplex_group.getCineplexList())
					{
						System.out.println("\nCineplex ID:\t"+ cplex.getCineplexId());
						System.out.println("Cineplex Name:\t"+ cplex.getCineplexName());
						for (Show show : cplex.getShowList())
						{
							System.out.println("Show ID:\t" + show.getShowID());
							System.out.println("Movie Name:\t" + show.getMovie().getTitle());
							System.out.println("Cinema Name:\t" + show.getHall().getName());
							System.out.println("Start Timing\t" + show.getTime_start());
							System.out.println("End Timing\t" + show.getTime_end());
						}
						System.out.println("==CINEPLEX END==");
					}
					
					cineplexID = getOnlyInteger("Enter Cineplex ID to view from:");
					showID = getOnlyInteger("Enter show ID to view:");
					adminManager.printShowLayout(cineplex_group, cineplexID, showID);
					break;
			}
		}
	}
	
	private void movieOptions(CineplexGroup cineplex_group, AdminManager adminManager, ReservationReviewManager reservationReviewManager) 
	{
		int option = -1;
		
		while (option!=6)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Movie Options:\n");
			System.out.println("1.\tCreate Movie");
			System.out.println("2.\tRemove Movie");
			System.out.println("3.\tView Movies");
			System.out.println("4.\tView Movie Reviews");
			System.out.println("5.\tRemove Movie Reviews");
			System.out.println("6.\tBack to Admin Menu");
			
			option = getOnlyInteger("Option: ");
			System.out.println("==========================");
			
			String title, synopsis,  director;
			String [] cast;
			int option1, movieID;
			
			switch (option)
			{
				case 1:
					title = getString("Enter Movie Title: ");
					synopsis = getString("Enter Movie Synopsis: ");
					director = getString("Enter Movie Director: ");
					cast = getString("Enter Movie Cast (Seperated by commas): ").split(",");
					int i =1;
					
					for (Genre genre : Genre.values())
					{
						System.out.println("("+(i++)+")" + genre.name());
					}
	
					option1 = getOnlyInteger("Enter Genre: (out of range will default to 1)");
					if (option1>Genre.values().length)
						option1 = 1;
					
					Genre genre = Genre.values()[option1-1];
					
					i = 1;
					for (Movie.Type type : Movie.Type.values())
					{
						System.out.println("("+(i++)+")" + type.name());
					}
	
					option1 = getOnlyInteger("Enter Movie Type: (out of range will default to 1)");
					if (option1>Movie.Type.values().length)
						option1 = 1;
					
					Movie.Type type = Movie.Type.values()[option1-1];
					
//					cineplex_group.addMovieToList(title, synopsis, cast, director, genre, type);
					adminManager.addMovieToList(cineplex_group, title, synopsis, cast, director, genre, type);
					break;
				case 2:
					for (Movie m : cineplex_group.getMovieList())
					{
						System.out.println("\nMovie ID:\t" + m.getMovieId());
						System.out.println("Movie Name:\t"+ m.getTitle());
					}
					
					movieID = getOnlyInteger("Enter movieID to remove: ");
//					cineplex_group.removeMovieFromlist(movieID);
					adminManager.removeMovieFromlist(cineplex_group, movieID);
					break;
				case 3:
					for (Movie m : cineplex_group.getMovieList())
					{
						System.out.println("Movie ID:\t" + m.getMovieId());
						System.out.println("Movie Name:\t"+ m.getTitle());
						System.out.println("Movie Director:\t" + m.getDirector());
						System.out.println("Movie Genre:\t"+ m.getGenre());
						System.out.println("Movie Type:\t"+ m.getType());
						System.out.println("Movie Synopsis:\t" + m.getSynopsis());
						printStringArray("Movie Cast:", m.getCast());
						System.out.println("Average Rating:\t" + m.getAvgRating());
					}
					System.out.println("");
					break;
					
				case 4:
					for (Movie m : cineplex_group.getMovieList())
					{
						System.out.println("\nMovie ID:\t" + m.getMovieId());
						System.out.println("Movie Name:\t"+ m.getTitle());
					}
					
					movieID = getOnlyInteger("Enter movieID to get reviews for: ");
					for (MovieReview r: cineplex_group.getReviews(movieID))
					{
						System.out.println("Review ID:\t" + r.getReviewID());
						System.out.println("Review: " + r.getReview());
						System.out.println("Rating: " + r.getRating());
					}

					break;
					
				case 5:
					
					movieID = getOnlyInteger("Enter movieID to delete review");
					int reviewID = getOnlyInteger("Enter reviewID to delete (-1 to cancel)");
					
//					cineplex_group.removeReview(movieID, reviewID);
					reservationReviewManager.removeReview(cineplex_group, movieID, reviewID);
					
					break;
			}
		}
	}
	
	private void viewCineplexes (CineplexGroup cineplex_group)
	{
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("Cineplex Id=\t" + cplex.getCineplexId());
			System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
			
			for (Cinema cinema: cplex.getCinemaList())
			{
				System.out.println("\nCinema Id=\t" + cinema.getHallId());
				System.out.println("Cinema Name=\t" + cinema.getName());
				System.out.println("Cinema Rows=\t" + cinema.getNumRows());
				System.out.println("Cinema Lanes=\t" + Arrays.toString(cinema.getColumn()));
			}
			System.out.println("\n-------\n");
		}
	}
	
}

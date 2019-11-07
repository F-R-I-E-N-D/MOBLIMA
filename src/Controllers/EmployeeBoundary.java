package Controllers;

import CiniplexClasses.*;
import CiniplexClasses.Cinema.ClassType;
import CiniplexClasses.Movie.Genre;
import CiniplexClasses.Show.DayType;

import java.util.Arrays;
import java.util.Scanner;

public class EmployeeBoundary extends UserInterface
{
	private static EmployeeBoundary employeeBoundary = null; 
	private CineplexGroup cineplex_group ;
	  
	private EmployeeBoundary() {}
	
	public static EmployeeBoundary getInstance()
	{
		if (employeeBoundary == null) 
			employeeBoundary = new EmployeeBoundary(); 
  
        return employeeBoundary; 
	}
	
	@Override
	public void startInterface(Scanner sc, CineplexGroup cineplex_group) 
	{	
		
		this.cineplex_group = cineplex_group;
		
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
					cineplexOptions(sc);
					break;
				case 2:
					showOptions(sc);
					break;
				case 3:
					movieOptions(sc);
					break;
			}
		}
		
		System.out.println("------------------------------------------------");
		
	}

	private void cineplexOptions(Scanner sc) 
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
					cineplex_group.addCineplexToList(name);					
					break;
				case 2:
					System.out.println("Cineplexes:\n");
					for (Cineplex cplex : cineplex_group.getCineplexList())
					{
						System.out.println("\nCineplex Id=\t" + cplex.getCineplexId());
						System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
					}
					
					System.out.print("Enter Cineplex Id to delete:\t");
					cineplex_group.removeCineplexFromlist(sc.nextInt());					
					break;
				case 3:
					viewCineplexes();
					break;
					
				case 4:
					System.out.println("Cineplexes & Cinemas:\n");
					viewCineplexes();
					int cplexID = getOnlyInteger("Enter Cineplex Id:\t");
					String cinemaName = getString("Enter New Cinema Name:");
					int num_rows = getOnlyInteger("Enter number of rows:\t");
					int[] _column = getIntegerArray("Enter Seats Per Lane");
				
					cineplex_group.createCinema(cplexID, cinemaName, num_rows, _column, ClassType.GOLD);				
					break;
					
				case 5:
					System.out.println("Cineplexes & Cinemas:\n");
					viewCineplexes();
					int cplexId = getOnlyInteger("Enter Cineplex Id:\t");
					int cinemaID = getOnlyInteger("Enter Cinema Id:\t");
					cineplex_group.removeCinema(cplexId, cinemaID);					
					break;
				
			}
		}
		
	}
	
	private void showOptions(Scanner sc) 
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
					
					cineplex_group.createShow(cineplexID, cinemaID, movieID, time_start, time_end, daytype);
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
					
					cineplex_group.deleteShow(cineplexID, showID);				
					break;
				case 3:
					
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
							System.out.println("Day Type" + show.getDaytype());
						}
						System.out.println("");
					}
					
					break;
					
				case 4:
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
					
					cineplexID = getOnlyInteger("Enter Cineplex ID to view from:");
					showID = getOnlyInteger("Enter show ID to view:");
					cineplex_group.printShowLayout(cineplexID, showID);
					break;
			}
		}
	}
	
	private void movieOptions(Scanner sc) 
	{
		int option = -1;
		
		while (option!=6)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Show Options:\n");
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
					
					cineplex_group.addMovieToList(title, synopsis, cast, director, genre, type);
					break;
				case 2:
					for (Movie m : cineplex_group.getMovieList())
					{
						System.out.println("Movie ID:\t" + m.getMovieId());
						System.out.println("Movie Name:\t"+ m.getTitle());
					}
					
					movieID = getOnlyInteger("Enter movieID to remove: ");
					cineplex_group.removeMovieFromlist(movieID);
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
						System.out.println("Average Rating:\t" + m.getAvgRating());
					}
					System.out.println("");
					break;
					
				case 4:
					for (Movie m : cineplex_group.getMovieList())
					{
						System.out.println("Movie ID:\t" + m.getMovieId());
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
					
					cineplex_group.removeReview(movieID, reviewID);
					
					break;
			}
		}
	}
	
	private void viewCineplexes ()
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
	
	
	// Test
	public static void main (String [] args)
	{
		Scanner sc = new Scanner(System.in);
		
		CineplexGroup goldenVillage = new CineplexGroup();
		
		EmployeeBoundary testInterface = EmployeeBoundary.getInstance();
		testInterface.startInterface(sc , goldenVillage);
		sc.close();
	}
}

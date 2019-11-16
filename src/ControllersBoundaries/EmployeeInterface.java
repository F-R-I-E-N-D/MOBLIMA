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
	
	public void startInterface(CineplexGroup cineplex_group, AdminManager adminManager, EmployeeReviewController employeeReviewController, PriceManager priceManager) throws ParseException 
	{
		int option = -1;
		while (option!=5)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Welcome, Administrator\nWhat would you like to do?\n");
			System.out.println("1.\tCineplex Options");
			System.out.println("2.\tMovie-Show Options");
			System.out.println("3.\tMovie Options");
			System.out.println("4.\tConfigure Price Settings");
			System.out.println("5.\tQuit");
			
			option = getOnlyInteger("Option: ", 1, 5);

			switch (option)
			{
				case 1:
					cineplexOptions(cineplex_group, adminManager);
					break;
				case 2:
					showOptions(cineplex_group, adminManager);
					break;
				case 3:
					movieOptions(cineplex_group, adminManager, employeeReviewController);
					break;
				case 4:
					priceOptions(priceManager);
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
					adminManager.addCineplexToList(cineplex_group, name);
					printCinema(cineplex_group);
					break;
				case 2:
					System.out.println("Cineplexes:\n");
					printCineplex(cineplex_group);
					adminManager.removeCineplexFromlist(cineplex_group, getOnlyInteger("Enter Cineplex Id to delete:\t"));
					printCinema(cineplex_group);
					break;
				case 3:
					printCinema(cineplex_group);
					break;
					
				case 4: 
					System.out.println("Cineplexes & Cinemas:\n");
					printCinema(cineplex_group);
					int cplexID = getOnlyInteger("Enter Cineplex Id:\t");
					String cinemaName = getString("Enter New Cinema Name:");
					int num_rows = getOnlyInteger("Enter number of rows:\t");
					int[] column = getIntegerArray("Enter Seats Per Lane");
					
					int i = 1;
					for (ClassType classtype : ClassType.values())
					{
						System.out.println("("+(i++)+")" + classtype.name());
					}
					int option2 = getOnlyInteger("Class Type:\t", 1, ClassType.values().length);
					
					
//					cineplex_group.createCinema(cplexID, cinemaName, num_rows, column, ClassType.GOLD);	
					adminManager.createCinema(cineplex_group, cplexID, cinemaName, num_rows, column, ClassType.values()[option2-1]);
					printCinema(cineplex_group);
					break;
					
				case 5:
					System.out.println("Cineplexes & Cinemas:\n");
					printCinema(cineplex_group);
					int cplexId = getOnlyInteger("Enter Cineplex Id:\t");
					int cinemaID = getOnlyInteger("Enter Cinema Id:\t");
//					cineplex_group.removeCinema(cplexId, cinemaID);
					adminManager.removeCinema(cineplex_group, cplexId, cinemaID);
					printCinema(cineplex_group);
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
					printCinema(cineplex_group);
					
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
					int choice = getOnlyInteger("(1) PUBLIC HOLIDAY (2) WEEKEND (3) WEEKDAY", 1, 3);
					
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
					printShow(cineplex_group);
					break;
				case 2:
					printShow(cineplex_group);
					
					cineplexID = getOnlyInteger("Enter Cineplex ID to delete from:");
					showID = getOnlyInteger("Enter show ID to delete:");
					
					adminManager.deleteShow(cineplex_group, cineplexID, showID);
					printShow(cineplex_group);
					break;
				case 3:
					
					printShow(cineplex_group);
					break;
					
				case 4:
					printShow(cineplex_group);
					
					cineplexID = getOnlyInteger("Enter Cineplex ID to view from:");
					showID = getOnlyInteger("Enter show ID to view:");
					adminManager.printShowLayout(cineplex_group, cineplexID, showID);
					break;
			}
		}
	}
	
	private void movieOptions(CineplexGroup cineplex_group, AdminManager adminManager, EmployeeReviewController employeeReviewController) 
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
					printMovie(cineplex_group);
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
					printMovie(cineplex_group);
					break;
				case 3:
					printMovie(cineplex_group);
					break;
					
				case 4:
					printReview(cineplex_group);
					break;
					
				case 5:
					printReview(cineplex_group);
					movieID = getOnlyInteger("Enter movieID to delete review");
					int reviewID = getOnlyInteger("Enter reviewID to delete (-1 to cancel)");
					
//					cineplex_group.removeReview(movieID, reviewID);
					employeeReviewController.removeReview(cineplex_group, movieID, reviewID);
					printReview(cineplex_group);
					break;
			}
		}
	}
	
	private void priceOptions(PriceManager priceManager) 
	{		
		System.out.println("1.View Price Settings");
	    System.out.println("2.Change Price Settings");
	    System.out.println("3.Exit");
	    
	    int option = 0;
		option = getOnlyInteger("Option (1-3): ", 1, 3);
		    
	    switch (option)
	    {
		    case 1:
		    	printPrices(priceManager);
		    	priceOptions(priceManager);
		    	break;
		    case 2:
		    	setPrices(priceManager);
		    	break;
		    case 3:
		    	return;
	    }
	}
	
	private void setPrices(PriceManager priceManager)
	{
		printPrices(priceManager);
	    
	    int option = getOnlyInteger("Price to change: ", 1, 12);
	    double price_set = getOnlyDouble("Price set: ", 1, 12);
	    switch (option)
	    {
		    case 1: priceManager.setStudentMarkdown(price_set); break;
		    case 2: priceManager.setElderlyMarkdown(price_set); break;
		    case 3: priceManager.setWeekdayMarkup(price_set); break;
		    case 4: priceManager.setWeekendMarkup(price_set); break;
		    case 5: priceManager.setPublicHolidayMarkup(price_set); break;
		    case 6: priceManager.setThreeDMarkup(price_set); break;
		    case 7: priceManager.setBlockbusterMarkup(price_set); break;
		    case 8: priceManager.setStandardPrice(price_set); break;
		    case 9: priceManager.setGoldClassMarkup(price_set); break;
		    case 10: priceManager.setDeluxeClassMarkup(price_set); break;
		    case 11: priceManager.setGeminiClassMarkup(price_set); break;
		    case 12: priceManager.setMaxClassMarkup(price_set); break;
	    }
	    
	    printPrices(priceManager);
	    option = getOnlyInteger("(1) Reset Another Price (2) Exit: ", 1, 2);
	    
	    if (option==1)
	    {
	    	setPrices(priceManager);
	    }
	    
	}
	
	private void printPrices(PriceManager priceManager)
	{
		System.out.println("New Prices:");
	    System.out.println("1\tstudentMarkdown:\t" + priceManager.getStudentMarkdown());
	    System.out.println("2\telderlyMarkdown:\t" + priceManager.getElderlyMarkdown());
	    System.out.println("3\tweekdayMarkup:\t\t" + priceManager.getWeekdayMarkup());
	    System.out.println("4\tweekendMarkup:\t\t" + priceManager.getWeekendMarkup());
	    System.out.println("5\tpublicHolidayMarkup:\t"+priceManager.getPublicHolidayMarkup());
	    System.out.println("6\tthreeDMarkup:\t\t" + priceManager.getThreeDMarkup());
	    System.out.println("7\tblockbusterMarkup:\t"+priceManager.getBlockbusterMarkup());
	    System.out.println("8\tstandardPrice:\t\t"+priceManager.getStandardPrice());
	    System.out.println("9\tgoldClassMarkup:\t"+priceManager.getGoldClassMarkup());
	    System.out.println("10\tdeluxeClassMarkup:\t"+priceManager.getDeluxeClassMarkup());
	    System.out.println("11\tgeminiClassMarkup:\t"+priceManager.getGeminiClassMarkup());
	    System.out.println("12\tmaxClassMarkup:\t" + priceManager.getMaxClassMarkup());
	    
	    
	}
	
	private void printCineplex (CineplexGroup cineplex_group)
	{
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("Cineplex Id=\t" + cplex.getCineplexId());
			System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
		}
	}
	
	public void printCinema(CineplexGroup cineplex_group) {
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("Cineplex Id=\t" + cplex.getCineplexId());
			System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
			
			for (Cinema cinema: cplex.getCinemaList())
			{
				System.out.println("\n\tCinema Id=\t" + cinema.getHallId());
				System.out.println("\tCinema Name=\t" + cinema.getName());
				System.out.println("\tCinema Class:\t" + cinema.getClasstype());
				System.out.println("\tCinema Rows=\t" + cinema.getNumRows());
				System.out.println("\tCinema Lanes=\t" + Arrays.toString(cinema.getColumn()));
				
				System.out.println(".........................................");
			}
			System.out.println("\n-----------------------------------------------------------------\n");
		}
	}
	
	public void printShow(CineplexGroup cineplex_group) {
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("\nCineplex ID:\t"+ cplex.getCineplexId());
			System.out.println("Cineplex Name:\t"+ cplex.getCineplexName());
			for (Show show : cplex.getShowList())
			{
				System.out.println("\n\tShow ID:\t" + show.getShowID());
				System.out.println("\tMovie Name:\t" + show.getMovie().getTitle());
				System.out.println("\tCinema Name:\t" + show.getHall().getName());
				System.out.println("\tCinema Class:\t" + show.getHall().getClasstype());
				System.out.println("\tStart Timing\t" + show.getTime_start());
				System.out.println("\tEnd Timing\t" + show.getTime_end());
				System.out.println("\tTickets Sold\t" + show.getTicketsSold());
				System.out.println("...........................................");
			}
			System.out.println("\n-----------------------------------------------------------------\n");
		}
		
	}
	
	public void printMovie(CineplexGroup cineplex_group){
		for (Movie m : cineplex_group.getMovieList())
		{
			System.out.println("Movie ID:\t" + m.getMovieId());
			System.out.println("Movie Name:\t"+ m.getTitle());
			System.out.println("Movie Director:\t" + m.getDirector());
			System.out.println("Movie Genre:\t"+ m.getGenre());
			System.out.println("Movie Type:\t"+ m.getType());
			System.out.println("Movie Synopsis:\t" + m.getSynopsis());
			printStringArray("Movie Cast:\t", m.getCast());
			System.out.println("Average Rating:\t" + m.getAvgRating());
			System.out.println("Movie Showing Status:\t"+ m.getShowingStatus());
			System.out.println("Total Tickets sold: \t" + m.getMovieSales());
			System.out.println("...........................................");
		}
		
	}
	public void printReview(CineplexGroup cineplex_group){
		for (Movie m : cineplex_group.getMovieList())
		{
			System.out.println("\nMovie ID:\t" + m.getMovieId());
			System.out.println("Movie Name:\t"+ m.getTitle());
			
			for (MovieReview r: m.getMovieReviewAndRatingList())
			{
				System.out.println("\n\tReview ID:" + r.getReviewID());
				System.out.println("\tReview: " + r.getReview());
				System.out.println("\tRating: " + r.getRating());
				System.out.println("...........................................");
			}
			System.out.println("---------------------------------------------------");
		}
	}
}

package ControllersBoundaries;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import CineplexClasses.Cinema;
import CineplexClasses.Cineplex;
import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;
import CineplexClasses.MovieReview;
import CineplexClasses.Show;

public abstract class UserBoundary
{
	protected Scanner sc = new Scanner(System.in);
	
	public UserBoundary() 
	{
		
	}
	
	protected int [] getIntegerArray(String message)
	{
		System.out.println(message);
		System.out.println("(end with -1)");
		int input = -1;
		int count = 1;
		ArrayList<Integer> column = new ArrayList<Integer>();
		while (true)
		{
			input = getOnlyInteger("Item " + Integer.toString(count), -1, 26); // 26 letters possible
			if (input<=0)
				break;
			column.add(input);
			count++;
		}
		
		return column.stream().mapToInt(i->i).toArray();
	}
	
	protected String getString (String message)
	{
		String input = "";
		System.out.println(message);
		while (input.isBlank())
		{
			input = sc.nextLine();
		}
//		System.out.println("Input \'" + input + "'");
		return input; 
	}
	
	protected int getOnlyInteger(String message) 
	{
		int option = -1;
		System.out.println(message);
		try
		{
			option = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Only integers are allowed. Please try again");
			sc.next();
			option = getOnlyInteger(message); 
		}
		return option;
	}
	
	
	protected int getOnlyInteger(String message, int low_inclusive, int high_inclusive) 
	{
		int option = -1;
		System.out.println(message);
		try
		{
			option = sc.nextInt();
			if (option<low_inclusive || option > high_inclusive)
			{
				System.out.println("Out of bounds. Please try again");
				option = getOnlyInteger(message, low_inclusive, high_inclusive); 
			}
		}
		catch(Exception e)
		{
			System.out.println("Only integers are allowed. Please try again");
			sc.next();
			option = getOnlyInteger(message, low_inclusive, high_inclusive); 	
		}
		return option;
	}
	
	protected double getOnlyDouble (String message, double low_inclusive, double high_inclusive) 
	{
		double return_double = -1;
		System.out.println(message);
		try
		{
			return_double = sc.nextDouble();
			if (return_double<low_inclusive || return_double > high_inclusive)
			{
				System.out.println("Out of bounds. Please try again");
				return_double = getOnlyDouble(message, low_inclusive, high_inclusive); 
			}
		}
		catch(Exception e)
		{
			System.out.println("Only integers are allowed. Please try again");
			sc.next();
			return_double = getOnlyDouble(message, low_inclusive, high_inclusive); 	
		}
		return return_double;
	}
	
	
	protected void printStringArray(String message, String [] array)
	{
		System.out.print(message + "\n\t");
		for (String s : array)
		{
			System.out.print(s + ", ");
		}
		System.out.println();
	}
	
	protected void printPrices(PriceController priceManager)
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
	    System.out.println("12\tmaxClassMarkup:\t\t" + priceManager.getMaxClassMarkup());
	    
	    
	}
	
	protected void printCineplex (CineplexGroup cineplex_group)
	{
		int i=0;
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("Cineplex Id=\t" + (i++));
			System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
		}
	}
	
	protected void printCinema(CineplexGroup cineplex_group) {
		int i=0;
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("Cineplex Id=\t" + (i++));
			System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
			
			int j=0;
			for (Cinema cinema: cplex.getCinemaList())
			{
				System.out.println("\n\tCinema Id=\t" + (j++));
				System.out.println("\tCinema Name=\t" + cinema.getName());
				System.out.println("\tCinema Class:\t" + cinema.getClasstype());
				System.out.println("\tCinema Rows=\t" + cinema.getNumRows());
				System.out.println("\tCinema Lanes=\t" + Arrays.toString(cinema.getColumn()));
				
				System.out.println(".........................................");
			}
			System.out.println("\n-----------------------------------------------------------------\n");
		}
	}
	
	protected void printShow(CineplexGroup cineplex_group) {
		int i=0;
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("\nCineplex ID:\t"+ (i++));
			System.out.println("Cineplex Name:\t"+ cplex.getCineplexName());
			int j=0;
			for (Show show : cplex.getShowList())
			{
				System.out.println("\n\tShow ID\t" + (j++));
				System.out.println("\tMovie Name\t" + show.getMovie().getTitle());
				System.out.println("\tCinema Name\t" + show.getHall().getName());
				System.out.println("\tCinema Class\t" + show.getHall().getClasstype());
				System.out.println("\tShow Date\t" + show.getShow_date());
				System.out.println("\tStart Timing\t" + show.getTime_start());
				System.out.println("\tEnd Timing\t" + show.getTime_end());
				System.out.println("\tTickets Sold\t" + show.getTicketsSold());
				System.out.println("...........................................");
			}
			System.out.println("\n-----------------------------------------------------------------\n");
		}
		
	}
	
	protected void printMovie(CineplexGroup cineplex_group){
		int i =0;
		for (Movie m : cineplex_group.getMovieList())
		{
			System.out.println("Movie ID:\t" + (i++));
			System.out.println("Movie Name:\t"+ m.getTitle());
			System.out.println("Movie Director:\t" + m.getDirector());
			System.out.println("Movie Genre:\t"+ m.getGenre());
			System.out.println("Movie Type:\t"+ m.getType());
			System.out.println("Movie Synopsis:\t" + m.getSynopsis());
			printStringArray("Movie Cast:\t", m.getCast());
			System.out.printf("Average Rating:\t%.2f\n" ,m.getAvgRating());
			System.out.println("Movie Showing Status:\t"+ m.getShowingStatus());
			System.out.println("Total Tickets sold: \t" + m.getMovieSales());
			System.out.println("...........................................");
		}
	}
	
	protected void printShowingMovie(CineplexGroup cineplex_group)
	{
		int i = 0;
		for (Movie m : cineplex_group.getMovieList())
		{
			if (m.getShowingStatus() == Movie.ShowingStatus.NOW_SHOWING)
			{
				System.out.println("Movie ID:\t" + (i++));
				System.out.println("Movie Name:\t"+ m.getTitle());
				System.out.println("Movie Director:\t" + m.getDirector());
				System.out.println("Movie Genre:\t"+ m.getGenre());
				System.out.println("Movie Type:\t"+ m.getType());
				System.out.println("Movie Synopsis:\t" + m.getSynopsis());
				printStringArray("Movie Cast:\t", m.getCast());
				System.out.printf("Average Rating:\t%.2f\n" ,m.getAvgRating());
				System.out.println("Total Tickets sold: \t" + m.getMovieSales());
				System.out.println("...........................................");
			}
			else
				i++;
		}
	}
	
	protected void printReview(CineplexGroup cineplex_group){
		int i =0;
		for (Movie m : cineplex_group.getMovieList())
		{
			if (m.getShowingStatus()==Movie.ShowingStatus.NOW_SHOWING)
	    	{
				System.out.println("\nMovie ID:\t" + (i++));
				System.out.println("Movie Name:\t"+ m.getTitle());
				
				for (MovieReview r: m.getMovieReviewAndRatingList())
				{
					System.out.println("\n\tReview ID:\t" + r.getReviewID());
					System.out.println("\tReview:\t" + r.getReview());
					System.out.println("\tRating:\t" + r.getRating());
					System.out.println("...........................................");
				}
				System.out.println("---------------------------------------------------");
		
	    	}	
			else
				i++;
		}
	}
	
	protected void printTop5Ratings(ArrayList<Movie>movieList)
	{
		System.out.println("Top Movies by Ratings are: \n");
		
		ArrayList<Movie> filteredMovieList = new ArrayList<Movie> ();
		for (Movie m: movieList)
		{
			if (m.getShowingStatus()==Movie.ShowingStatus.NOW_SHOWING)
			{
				filteredMovieList.add(m);
			}
		}
		
		filteredMovieList.sort(Comparator.comparing(Movie::getAvgRating).reversed());
        int i;
        int max =5;
        if(filteredMovieList.size() <= 5 ) {
        	max = filteredMovieList.size();
        }
        for (i = 0; i < max; i++) {
        	if (filteredMovieList.get(i).getAvgRating()!=-1.0)
        	{
                System.out.println((i+1) + ".\t\t" + filteredMovieList.get(i).getTitle());
                System.out.printf("Average Rating:\t%.2f\n" ,filteredMovieList.get(i).getAvgRating());
        	}
        }
	}
	
	protected void printTop5Sales(ArrayList<Movie>movieList)
	{
		System.out.println("Top Movies by Ticket Sales are:\n");
		
		ArrayList<Movie> filteredMovieList = new ArrayList<Movie> ();
		for (Movie m: movieList)
		{
			if (m.getShowingStatus()==Movie.ShowingStatus.NOW_SHOWING)
			{
				filteredMovieList.add(m);
			}
		}
		
		filteredMovieList.sort(Comparator.comparing(Movie::getMovieSales).reversed());
        int i;
        int max =5;
        if(filteredMovieList.size() <= 5 ) {
        	max = filteredMovieList.size();
        }
        for (i = 0; i < max; i++) {
            System.out.println((i+1) + ".\t" + filteredMovieList.get(i).getTitle());
            System.out.println("Sales:\t" + filteredMovieList.get(i).getMovieSales() + "\n");
        }
	}
}


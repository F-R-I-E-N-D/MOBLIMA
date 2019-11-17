package ControllersBoundaries;

import CineplexClasses.*;
import CineplexClasses.Cinema.ClassType;
import CineplexClasses.Movie.Genre;
import CineplexClasses.Show.DayType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeBoundary extends UserBoundary
{	
	
	public void startInterface(CineplexController cineplex_group, AdminController adminManager, EmployeeReviewController employeeReviewController, PriceController priceManager)
	{
		int option = -1;
		while (option!=5)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Welcome, Administrator\nWhat would you like to do?\n");
			System.out.println("1.\tCineplex Options");
			System.out.println("2.\tShow Options");
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

	private void cineplexOptions(CineplexController cineplex_group, AdminController adminManager) 
	{
		int option = -1;
		
		while (option!=7)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Cineplex Options:\n");
			System.out.println("1.\tCreate Cineplex");
			System.out.println("2.\tRemove Cineplex");
			System.out.println("3.\tView Cineplexes (+ Constituent Cinemas)");
			System.out.println("4.\tAdd Cinema to Cineplex");
			System.out.println("5.\tUpdate Cinema Record");
			System.out.println("6.\tRemove Cinema from Cineplex");
			System.out.println("7.\tBack to Admin Menu");
			
			option = getOnlyInteger("Option: ");
			System.out.println("==========================");
			int cplexID, num_rows, i, num_rows_hand, option2; int [] column, columnHand;
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
					cplexID = getOnlyInteger("Enter Cineplex Id:\t");
					String cinemaName = getString("Enter New Cinema Name:");
					num_rows = getOnlyInteger("Enter number of rows:\t");
					num_rows_hand = getOnlyInteger("Enter number of handicapped rows:\n(excess rows will be ignored)");
					column = getIntegerArray("Enter Seats Per Lane");
					columnHand = getIntegerArray("Enter Handicapped Seats Per Lane\n(excess rows will be ignored)");
					
					i = 1;
					for (ClassType classtype : ClassType.values())
					{
						System.out.println("("+(i++)+")" + classtype.name());
					}
					option2 = getOnlyInteger("Class Type:\t", 1, ClassType.values().length);
					
					adminManager.createCinema(cineplex_group, cplexID, cinemaName, num_rows, num_rows_hand, column, columnHand, ClassType.values()[option2-1]);
					printCinema(cineplex_group);
					break;
					
				case 5:
					System.out.println("\nCineplexes & Cinemas:\n");
					printCinema(cineplex_group);
					cplexID = getOnlyInteger("Enter Cineplex ID:\t");
					int cinemaID = getOnlyInteger("Enter Cinema ID:");
					
					System.out.println("Update:\n");
					System.out.println("1.\tName");
					System.out.println("2.\tRows");
					System.out.println("3.\tNum of seats per Lane");
					System.out.println("4.\tClass Type");
					System.out.println("5.\t(No updates, Back)");
					
					option2 = getOnlyInteger("Option 1-5:",1 , 5);
					
					switch (option2)
					{
						case 1:
							String new_name = getString("Enter new name:");
							adminManager.updateCinemaName(cineplex_group, new_name, cplexID, cinemaID);					
							break;
						case 2:
							num_rows = getOnlyInteger("Enter new number of rows:");
							adminManager.updateCinemaRows(cineplex_group, num_rows, cplexID, cinemaID);					
							break;
						case 3:
							column = getIntegerArray("Enter new seats Per Lane");
							adminManager.updateCinemaSeatsPerLane(cineplex_group, column, cplexID, cinemaID);				
							break;
						case 4:
							i = 1;
							for (ClassType classtype : ClassType.values())
							{
								System.out.println("("+(i++)+")" + classtype.name());
							}
							int option3 = getOnlyInteger("Class Type:\t", 1, ClassType.values().length);
							
							adminManager.updateCinemaClassType(cineplex_group, option3, cplexID, cinemaID);			
							break;
						
					}
					System.out.println("Cinema Updated");
					printCinema(cineplex_group);
					break;
					
				case 6:
					System.out.println("Cineplexes & Cinemas:\n");
					printCinema(cineplex_group);
					int cplexId = getOnlyInteger("Enter Cineplex Id:\t");
					cinemaID = getOnlyInteger("Enter Cinema Id:\t");
//					cineplex_group.removeCinema(cplexId, cinemaID);
					adminManager.removeCinema(cineplex_group, cplexId, cinemaID);
					printCinema(cineplex_group);
					break;
			}
		}
		
	}
	
	private void showOptions(CineplexController cineplex_group, AdminController adminManager)
	{	
		int option = -1;
		
		while (option!=6)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Show Options:\n");
			System.out.println("1.\tCreate Show");
			System.out.println("2.\tRemove Show");
			System.out.println("3.\tView Shows");
			System.out.println("4.\tView Show Seats");
			System.out.println("5.\tUpdate Show");
			System.out.println("6.\tBack to Admin Menu");
			
			option = getOnlyInteger("Option: ",1,6);
			System.out.println("==========================");
			
			int cineplexID, cinemaID,  movieID, showID;
			
			switch (option)
			{
				case 1:
					printCinema(cineplex_group);
					int i=0;
					for (Movie m : cineplex_group.getMovieList())
					{
						System.out.println("Movie ID: "+ (i++));
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
					while (true)
					{
						try
						{
				    		date = new SimpleDateFormat("dd/MM/yyyy").parse(getString("Enter date DD/MM/YYYY"));  
				    		break;
						}
						catch (ParseException e)
						{
							System.out.println("Wrong date format");
							continue;
						}
					}					
					adminManager.createShow(cineplex_group, cineplexID, cinemaID, movieID, time_start, time_end, daytype, date);
					cineplex_group.getMovieList().get(movieID).setShowingStatus(Movie.ShowingStatus.NOW_SHOWING);
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
				case 5:
					System.out.println("\nShows:\n");
					printShow(cineplex_group);
					cineplexID = getOnlyInteger("Enter Cineplex ID to add to:",0,cineplex_group.getCineplexList().size()-1);
					showID = getOnlyInteger("Enter Show ID used:",0,cineplex_group.getShowList(cineplexID).size()-1);
					
					cinemaID = cineplex_group.getShowList(cineplexID).get(showID).getHall().getHallId();

					System.out.println("Update:\n");
					System.out.println("1.\tStart time");
					System.out.println("2.\tEnd time");
					System.out.println("3.\tDate");
					System.out.println("4.\t(No updates, Back)");
					
					int option2 = getOnlyInteger("Option 1-4:",1 , 4);
					
					switch (option2)
					{
						case 1:
							int tstart = getOnlyInteger("Enter Starting Time in HHHH Hours:");
							adminManager.updateShowStartTime(cineplex_group, cineplexID, cinemaID, showID, tstart);
							break;
						case 2:
							int tend = getOnlyInteger("Enter Ending Time in HHHH Hours:");
							adminManager.updateShowEndTime(cineplex_group, cineplexID, cinemaID, showID, tend);
							break;
						case 3:
							Date datenew =  new Date();
							while (true)
							{
								try
								{
						    		datenew = new SimpleDateFormat("dd/MM/yyyy").parse(getString("Enter date DD/MM/YYYY"));  
						    		break;
								}
								catch (ParseException e)
								{
									System.out.println("Wrong date format");
									continue;
								}
							}
							adminManager.updateShowDate(cineplex_group, cineplexID, cinemaID, showID, datenew);
					}
					System.out.println("Show has been Updated");
					printShow(cineplex_group);
					break;
			}
		}
	}
	
	private void movieOptions(CineplexController cineplex_group, AdminController adminManager, EmployeeReviewController employeeReviewController) 
	{
		int option = -1;
		
		while (option!=9)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Movie Options:\n");
			System.out.println("1.\tCreate Movie");
			System.out.println("2.\tDiscontinue Movie");
			System.out.println("3.\tView Movies");
			System.out.println("4.\tView Movie Reviews");
			System.out.println("5.\tRemove Movie Reviews");
			System.out.println("6.\tView Top Movies by Ratings");
			System.out.println("7.\tView Top Movies by Tickets Sold");
			System.out.println("8.\tUpdate Movie");
			System.out.println("9.\tBack to Admin Menu");
			
			option = getOnlyInteger("Option: ", 1, 9);
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
					printMovie(cineplex_group);
					movieID = getOnlyInteger("Enter movieID to remove: ");
//					adminManager.removeMovieFromlist(cineplex_group, movxieID);
					cineplex_group.getMovieList().get(movieID).setShowingStatus(Movie.ShowingStatus.DISCONTINUED);
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
				case 6:
					printTop5Ratings(cineplex_group.getMovieList());
					break;
				case 7:
					printTop5Sales(cineplex_group.getMovieList());
					break;
				case 8:
					printMovie(cineplex_group);
					movieID = getOnlyInteger("Enter movieID to Update: ");
					
					System.out.println("Update:\n");
					System.out.println("1.\tTitle");
					System.out.println("2.\tSynopsis");
					System.out.println("3.\tCast");
					System.out.println("4.\tDirector");
					System.out.println("5.\tGenre");
					System.out.println("6.\tType");
					System.out.println("7.\tShowing Status");
					System.out.println("8.\t(No updates, Back)");
					
					int option2 = getOnlyInteger("Option 1-8:",1 , 8);
					
					switch(option2) {
						case 1:
							title = getString("Enter Movie Title: ");
							adminManager.updateMovieTitle(cineplex_group, movieID, title);
							break;
						case 2: 
							synopsis = getString("Enter Movie Synopsis: ");
							adminManager.updateMovieSynopsis(cineplex_group, movieID, synopsis);
							break;
						case 4: 
							director = getString("Enter Movie Director: ");
							adminManager.updateMovieDirector(cineplex_group, movieID, director);
							break;
						case 3: 
							cast = getString("Enter Movie Cast (Seperated by commas): ").split(",");
							adminManager.updateMovieCast(cineplex_group, movieID, cast);
							break;
						case 5: 
							i = 1;
							for (Genre genrenew : Genre.values())
							{
								System.out.println("("+(i++)+")" + genrenew.name());
							}
			
							option1 = getOnlyInteger("Enter Genre: (out of range will default to 1)");
							if (option1>Genre.values().length)
								option1 = 1;
							
							Genre genrenew = Genre.values()[option1-1];
							adminManager.updateMovieGenre(cineplex_group, movieID, genrenew);
							break;
						case 6:
							i = 1;
							for (Movie.Type typenew : Movie.Type.values())
							{
								System.out.println("("+(i++)+")" + typenew.name());
							}
			
							option1 = getOnlyInteger("Enter Movie Type: (out of range will default to 1)");
							if (option1>Movie.Type.values().length)
								option1 = 1;
							
							Movie.Type typenew = Movie.Type.values()[option1-1];
							adminManager.updateMovieType(cineplex_group, movieID, typenew);
							break;
						case 7:
							i = 1;
							for (Movie.ShowingStatus status : Movie.ShowingStatus.values())
							{
								System.out.println("("+(i++)+")" + status.name());
							}
			
							option1 = getOnlyInteger("Enter Movie Showing Status: (out of range will default to 1)");
							if (option1>Movie.ShowingStatus.values().length)
								option1 = 1;
							
							Movie.ShowingStatus status = Movie.ShowingStatus.values()[option1-1];
							adminManager.updateMovieShowingStatus(cineplex_group, movieID, status);
							break;
					}
					System.out.println("Movie Updated");
					printMovie(cineplex_group);
					break;
			}
		}
	}
	
	private void priceOptions(PriceController priceManager) 
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
	
	private void setPrices(PriceController priceManager)
	{
		printPrices(priceManager);
	    
	    int option = getOnlyInteger("Price Option to change: ", 1, 12);
	    double price_set = getOnlyDouble("Price set: ", 0, 12);
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
	
}

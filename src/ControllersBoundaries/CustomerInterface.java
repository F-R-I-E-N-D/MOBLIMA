package ControllersBoundaries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import CineplexClasses.Cineplex;
import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;
import CineplexClasses.Reservation;
import CineplexClasses.Show;
import Users.CustomerUser;
import Users.CustomerUser.CustomerType;

public class CustomerInterface extends UserInterface {

	private static final long serialVersionUID = 1L;
	
    public void startInterface(CineplexGroup cineplex_group, ReservationReviewManager reservationReviewManager, PriceManager priceManager, CustomerUser customer) throws ParseException 
    {  

        int option = -1;

        while (option != 4) {
            System.out.println("------------------------------------------------");
            System.out.println("Welcome to Golden Village!\nWhat would you like to do?\n");
            System.out.println("1.\tView Movies / Add Review");
            System.out.println("2.\tBuy Movie Ticket");
            System.out.println("3.\tView Booking History");
            System.out.println("4.\tQuit"); // Add view reservations

            option = sc.nextInt();
            switch (option) 
            {
                case 1: 
                {
                    viewMovies(cineplex_group, reservationReviewManager, customer);
                    break;
                }

                case 2: 
                {
                    buyTicket(cineplex_group, reservationReviewManager, priceManager, customer);
                    break;
                }

                case 3: 
                {
                    printBookingHistory(cineplex_group, customer);
                    break;
                }
            }
        }
    }

    private void printBookingHistory(CineplexGroup cineplex_group, CustomerUser customer) 
    {
    	for (Cineplex cplex : cineplex_group.getCineplexList())
    	{
    		for (Reservation r : cplex.getReservationList())
    		{
    			if (r.getUsername() == customer.getUsername())
    			{
    				System.out.println("Reservation ID:\t" + r.getReservationID());
//    				System.out.println("Date:\t" + r.getDate());
    				System.out.println("Movie Name:\t" + cplex.getShowList().get(r.getShowID()).getMovie().getTitle());
    				System.out.println("Seat:\t" + r.getRow() +"-"+ r.getLane() + "-" + r.getSeat());
    			}
    		}
    	}
	}

	// viewMovies method
    public void viewMovies(CineplexGroup cineplex_group, ReservationReviewManager reservationReviewManager, CustomerUser customer) 
    {
        int option2 = -1;
//        System.out.println("we are in "+ cineplex_group.getCineplexList().get(0).getCineplexName());
        while (option2 != 4) {
            System.out.println("------------------------------------------------");
            System.out.println("1.\tView All Movies / Add Review");
            System.out.println("2.\tView Top 5 Movies by overall reviewers' rating");
            System.out.println("3.\tView Top 5 Movies by ticket sales");
            System.out.println("4.\tBack to Main Menu");
            option2 = sc.nextInt();
            if (option2 == 4)
                break;
            ArrayList<Movie> movieList = cineplex_group.getMovieList(); // getMovieList from cineplex_group
            switch (option2) {
                case 1: {
                    for (Movie movie : movieList) {
                        //show all movies that are ComingSoon, Preview, NowShowing
//                        if (movie.getShowingStatus() != ShowingStatus.DISCONTINUED)
                            System.out.println("Title: " + movie.getTitle());
                    }
                    break;
                }
                case 2: {
                    movieList.sort(Comparator.comparing(Movie::getAvgRating).reversed());
                    int i;
                    for (i = 0; i < 5; i++) {
                        System.out.println(i + ". " + movieList.get(i).getTitle());
                    }
                    break;
                }
                case 3: {
                    // top 5 movies by ticket sales
                }

            }

            // view more details about a movie or return
            int option3 = -1;
            while (option3 != 2) {
                System.out.println("------------------------------------------------");
                System.out.println("1.\tView More Details about a Movie");
                System.out.println("2.\tAdd Review");
                System.out.println("3.\tBack to Viewing Menu");
                option3 = getOnlyInteger("Option: ");
                if (option3 == 1) 
                {
                    System.out.println("------------------------------------------------");
                    System.out.println("\tEnter Movie Name:");
                    String movieName = sc.next();
                    int found = 0;
                    for (Movie movie : movieList) {
                        //find corresponding movie
                        if (movieName.equalsIgnoreCase(movie.getTitle())) {
                            System.out.println("------------------------------------------------");
                            System.out.println("Title: " + movie.getTitle());
                            System.out.println("Showing Status: " + movie.getShowingStatus());
                            System.out.println("Synopsis: " + movie.getSynopsis());
                            System.out.println("Director: " + movie.getDirector());
                            printStringArray("Cast: ", movie.getCast());
                            System.out.println("Type: " + movie.getType());
                            System.out.println("Genre: " + movie.getGenre());
                            movie.printReviewsAndRatings();
                            found = 1;
                            break;
                        }
                    }
                    if (found == 0)
                        System.out.println("Movie not found");
                }
                else if (option3==2)
                {
                	System.out.println("------------------------------------------------");
                    System.out.println("\tEnter Movie Name:");
                    for (Movie movie : movieList) 
                    {
                    	System.out.println("Movie ID: " + movie.getMovieId());
                    	System.out.println("Title: " + movie.getTitle());
                    }
                    int movieID = getOnlyInteger("Movie ID to add review: ");
                    
                    int rating  = getOnlyInteger("Enter rating from 1 to 5");
                    while (rating>5 || rating <1)
                    {
                    	rating  = getOnlyInteger("Enter rating from 1 to 5");
                    }
                    reservationReviewManager.createReview(cineplex_group, movieID, getString("Enter Review: "), rating, customer.getUsername());
                    
                }
            }
        }
    }

    //buyTicket method
    public void buyTicket(CineplexGroup cineplex_group, ReservationReviewManager reservationReviewManager, PriceManager priceManager, CustomerUser customer) throws ParseException 
    {
        System.out.println("------------------------------------------------");
        System.out.println("Cineplexes:");
        for (Cineplex cineplex : cineplex_group.getCineplexList()) {
            System.out.println("Cineplex ID: " + cineplex.getCineplexId());
            System.out.println("Cineplex Name: " + cineplex.getCineplexName());

        }
        System.out.println("Please enter Cineplex ID:");
        int cineplexID = sc.nextInt();
        Cineplex cineplexChoice = null;
        for (Cineplex cineplex : cineplex_group.getCineplexList()) 
        {
            if (cineplex.getCineplexId() == cineplexID)
                cineplexChoice = cineplex;
        }

        ArrayList<Show> showList = cineplexChoice.getShowList();
        System.out.println("------------------------------------------------");
        System.out.println("Shows at " + cineplexChoice.getCineplexName());
        for (Show show : showList) {
            System.out.println("Show ID: " + show.getShowID());
            System.out.println("Movie Title: " + show.getMovie().getTitle());
            System.out.println("Start Time: " + show.getTime_start());
            System.out.println("End Time: " + show.getTime_end());

        }
        System.out.println("Please enter Show ID:");
        int showID = sc.nextInt();
        Show showChoice = null;
        for (Show show : showList) {
            if (show.getShowID() == showID)
                showChoice = show;
        }

        System.out.println("------------------------------------------------");
        System.out.println("Hall Layout:");
        showChoice.printLayout();

        System.out.println("------------------------------------------------");
        System.out.println("Please enter number of tickets:");
        int numOfSeats = sc.nextInt();
        int i;
        for (i = 0; i < numOfSeats; i++) {
            System.out.println("Ticket " + (i + 1));
            System.out.println("Please enter row:");
            char row = sc.next().charAt(0);
            System.out.println("Please enter lane:");
            int lane = sc.nextInt();
            System.out.println("Please enter seat:");
            int seat = sc.nextInt();

            double price = priceManager.getPrice(cineplex_group.getShowList(cineplexID).get(showID).getDaytype(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getTime_start(), customer.getCustomerType(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getHall().getClasstype(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getMovie().getType());
            
            System.out.println("Price Charged:\t$" + (price));
            
            reservationReviewManager.createReservation(cineplex_group, cineplexID, customer.getUsername(), showID, row, lane, seat, price);
        }
    }
}

		
				
				
				
				

		


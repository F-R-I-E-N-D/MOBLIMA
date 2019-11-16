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
	
    public void startInterface(CineplexGroup cineplex_group, CustomerReviewController customerReviewController, ReservationController reservationController, PriceManager priceManager, CustomerUser customer) throws ParseException 
    {  

        int option = -1;

        while (option != 4) {
            System.out.println("------------------------------------------------");
            System.out.println("Welcome to Golden Village!\nWhat would you like to do?\n");
            System.out.println("1.\tMovies & Reviews");
            System.out.println("2.\tBuy Movie Ticket");
            System.out.println("3.\tView Booking History");
            System.out.println("4.\tQuit"); // Add view reservations

            option = getOnlyInteger("Option: ", 1, 4);
            switch (option) 
            {
                case 1: 
                {
                    viewMovies(cineplex_group, customerReviewController, customer);
                    break;
                }

                case 2: 
                {
                    buyTicket(cineplex_group, reservationController, priceManager, customer);
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
    				System.out.println("Date:\t" + r.getReservationDate());
    				System.out.println("Movie Name:\t" + cplex.getShowList().get(r.getShowID()).getMovie().getTitle());
    				System.out.println("Seat:\t" + r.getRow() +"-"+ r.getLane() + "-" + r.getSeat());
    			}
    		}
    	}
	}

	// viewMovies method
    private void viewMovies(CineplexGroup cineplex_group, CustomerReviewController customerReviewController, CustomerUser customer) 
    {
        int option = 0;
        
        while (option != 6) {
            System.out.println("------------------------------------------------");
            System.out.println("1.\tView All Shows Available");
            System.out.println("2.\tView All Movies Showing");
            System.out.println("3.\tMovie Reviews");
            System.out.println("4.\tView Top 5 Movies by overall reviewers' rating");
            System.out.println("5.\tView Top 5 Movies by ticket sales");
            System.out.println("6.\tBack to Main Menu (to buy a ticket or check history)");
            option = getOnlyInteger("Option: ", 1, 6);
            if (option == 6)
                break;
            
            ArrayList<Movie> movieList = cineplex_group.getMovieList(); // getMovieList from cineplex_group
            switch (option) 
            {
                case 1: 
                {
                    printShow(cineplex_group);
                    break;
                }
                case 2:
                {
                	printShowingMovie(cineplex_group);
                	break;
                }
                case 3:
                {
                	reviewOptions(cineplex_group, customerReviewController, customer);
                	break;
                }
                case 4: 
                {
                	System.out.println("Top Movies by Ratings are: \n");
                	movieList.sort(Comparator.comparing(Movie::getAvgRating).reversed());
                    int i;
                    int max =5;
                    if(movieList.size() <= 5 ) {
                    	max = movieList.size();
                    }
                    for (i = 0; i < max; i++) {
                    	if (movieList.get(i).getAvgRating()!=-1.0)
                    	{
	                        System.out.println((i+1) + ".\t" + movieList.get(i).getTitle());
	                        System.out.println("Rating:\t" + movieList.get(i).getAvgRating()+"\n");
                    	}
                    }
                    break;
                }
                case 5: 
                {
                	System.out.println("Top Movies by Ticket Sales are:\n");
                	movieList.sort(Comparator.comparing(Movie::getMovieSales).reversed());
                    int i;
                    int max =5;
                    if(movieList.size() <= 5 ) {
                    	max = movieList.size();
                    }
                    for (i = 0; i < max; i++) {
                        System.out.println((i+1) + ".\t" + movieList.get(i).getTitle());
                        System.out.println("Sales:\t" + movieList.get(i).getMovieSales() + "\n");
                    }
                    break;
                }

            }
        }
    }

    private void reviewOptions(CineplexGroup cineplex_group, CustomerReviewController customerReviewController, CustomerUser customer) 
    {
		int option = 0;
        
        while (option != 5) 
        {
            System.out.println("------------------------------------------------");
            System.out.println("1.\tPrint Reviews");
            System.out.println("2.\tAdd Reviews");
            System.out.println("3.\tBack");
             
            option = getOnlyInteger("Option: ", 1, 3);
            if (option==3)
            	break;
            
            switch (option)
            {
            case 1:
            	printReview(cineplex_group);
            	break;
            case 2:
            	for (Movie m: cineplex_group.getMovieList())
            	{
            		
            		System.out.println("\nMovie ID:\t" + m.getMovieId());
            		System.out.println("Movie Name:\t" + m.getTitle());
            		
            	}
            	
            	int movieID = getOnlyInteger("Movie ID to add review: ");
                int rating  = getOnlyInteger("Enter rating from 1 to 5");
                while (rating>5 || rating <1)
                {
                	rating  = getOnlyInteger("Enter rating from 1 to 5");
                }
                customerReviewController.createReview(cineplex_group, movieID, getString("Enter Review: "), rating, customer.getUsername());
             
            }
        }
	}

	//buyTicket method
    private void buyTicket(CineplexGroup cineplex_group, ReservationController reservationController, PriceManager priceManager, CustomerUser customer) throws ParseException 
    {
        printShow(cineplex_group);
        int cineplexID = getOnlyInteger("Please enter Cineplex ID:", 0, cineplex_group.getCineplexList().size()-1);
        int showID = getOnlyInteger("Please enter Show ID:", 0, cineplex_group.getShowList(cineplexID).size()-1);

        Show showChoice = null;
        for (Show show : cineplex_group.getShowList(cineplexID)) 
        {
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
        double sum = 0;
        for (i = 0; i < numOfSeats; i++) {
            System.out.println("Ticket " + (i + 1));
            System.out.println("Please enter row letter:");
            char row = sc.next().charAt(0);
            System.out.println("Please enter lane:");
            int lane = sc.nextInt();
            System.out.println("Please enter seat:");
            int seat = sc.nextInt();

            
            double price = priceManager.getPrice(cineplex_group.getShowList(cineplexID).get(showID).getDaytype(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getTime_start(), customer.getCustomerType(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getHall().getClasstype(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getMovie().getType());
            sum+=price;
            System.out.println("Price Charged:\t$" + (price));
            
            if (reservationController.createReservation(cineplex_group, cineplexID, customer.getUsername(), showID, row, lane, seat, price))
            {
            	sum-=price;
            	i--;
            }
        }
        
        cineplex_group.getShowList(cineplexID).get(showID).addTicketsSold(numOfSeats);
        System.out.println("Total Price: " + sum);
        System.out.println("------------------------------------------------");
        System.out.println("New Hall Layout:");
        showChoice.printLayout();
    }
}

		
				
				
				
				

		


package ControllersBoundaries;

import java.util.ArrayList;
import java.util.Date;

import CineplexClasses.Cineplex;
import CineplexClasses.CineplexController;
import CineplexClasses.Movie;
import CineplexClasses.Reservation;
import CineplexClasses.Show;
import Users.CustomerUser;

public class CustomerBoundary extends UserBoundary 
{
	
    public void startInterface(CineplexController cineplex_group, CustomerReviewController customerReviewController, ReservationController reservationController, PriceController priceManager, CustomerUser customer) 
    {  

        int option = -1;

        while (option != 4) {
            System.out.println("-----------------------------------------------------------------");
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

    private void printBookingHistory(CineplexController cineplex_group, CustomerUser customer) 
    {
    	for (Cineplex cplex : cineplex_group.getCineplexList())
    	{
    		for (Reservation r : cplex.getReservationList())
    		{
    			if (r.getUsername().equals(customer.getUsername()))
    			{
    				System.out.println("\nReservation ID:\t" + r.getReservationID());
//    				System.out.println("Booking Date:\t" + r.getReservationDate());
    				System.out.println("Movie Name:\t" + cplex.getShowList().get(r.getShowID()).getMovie().getTitle());
    				Date showdate =  cplex.getShowList().get(r.getShowID()).getShow_date();
    				System.out.println("Show Date:\t" + showdate.getDate() + "/" + showdate.getMonth() + "/" + (1900+showdate.getYear()));
    				System.out.println("Seat:\t\t" + r.getRow() +"-"+ r.getLane() + "-" + r.getSeat());
    			}
    		}
    	}
	}

	// viewMovies method
    private void viewMovies(CineplexController cineplex_group, CustomerReviewController customerReviewController, CustomerUser customer) 
    {
        int option = 0;
        
        while (option != 6) {
            System.out.println("-----------------------------------------------------------------");
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
                	printTop5Ratings(movieList);
                    break;
                }
                case 5: 
                {
                	printTop5Sales(movieList);
                    break;
                }

            }
        }
    }

    private void reviewOptions(CineplexController cineplex_group, CustomerReviewController customerReviewController, CustomerUser customer) 
    {
		int option = 0;
        
        while (option != 3) 
        {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("1.\tPrint Reviews");
            System.out.println("2.\tAdd Reviews");
            System.out.println("3.\tBack");
             
            option = getOnlyInteger("Option: ", 1, 3);
            
            switch (option)
            {
            case 1:
            	printReview(cineplex_group);
            	break;
            case 2:
            	int i = 0;
            	for (Movie m: cineplex_group.getMovieList())
            	{
            		
            		if (m.getShowingStatus()==Movie.ShowingStatus.NOW_SHOWING)
            		{
	            		System.out.println("\nMovie ID:\t" + (i++));
	            		System.out.println("Movie Name:\t" + m.getTitle());
            		}
            		else i++;
            	}
            	
            	int movieID = getOnlyInteger("Movie ID to add review: (-1 to go back)",-1,cineplex_group.getMovieList().size()-1);
                if (movieID==-1)
                	return;
            	int	rating  = getOnlyInteger("Enter rating from 1 to 5", 1, 5);
                customerReviewController.createReview(cineplex_group, movieID, getString("Enter Review: "), rating, customer.getUsername());
             
            }
        }
	}

	//buyTicket method
    private void buyTicket(CineplexController cineplex_group, ReservationController reservationController, PriceController priceManager, CustomerUser customer)
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
        if (showChoice == null)
        {
        	System.out.println("No such show");
        	return;
        }

        System.out.println("------------------------------------------------");
        System.out.println("Hall Layout:");
        showChoice.printLayout();

        System.out.println("------------------------------------------------");
        int numOfSeats = getOnlyInteger("Please enter number of tickets: (0 to go back)", 0, 10);
        if (numOfSeats==0)
        	return;
        double sum = 0;
        int i = 0;
        while (numOfSeats>0) 
        {
            System.out.println("\nTicket " + (i + 1));
            System.out.println("Please enter row letter:");
            char row = sc.next().charAt(0);
            int lane = getOnlyInteger("Please enter lane:");
            int seat = getOnlyInteger("Please enter seat:");

            
            double price = priceManager.getPrice(cineplex_group.getShowList(cineplexID).get(showID).getDaytype(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getTime_start(), customer.getCustomerType(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getHall().getClasstype(), 
            		cineplex_group.getShowList(cineplexID).get(showID).getMovie().getType());
            
            if (reservationController.createReservation(cineplex_group, cineplexID, customer.getUsername(), showID, row, lane, seat, price))
            {
            	sum+=price;
            	numOfSeats--;
            	i++;
            	System.out.println("Price Charged:\t$" + (price));
            }
        }
        
        cineplex_group.getShowList(cineplexID).get(showID).addTicketsSold(numOfSeats);
        
        System.out.println("====================");
        System.out.println("Ticket");
        System.out.println("===========");
        System.out.println("Movie:\t\t" +showChoice.getMovie().getTitle() );
        System.out.println("Show Date:\t" +showChoice.getShow_date().getDate() + "/" + showChoice.getShow_date().getMonth() + "/" + (1900+showChoice.getShow_date().getYear()));
        System.out.println("Tickets Bought:\t" + i);
        System.out.println("Total Price:\t" + sum);
        System.out.println("====================");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("New Hall Layout:");
        showChoice.printLayout();
    }
}

		
				
				
				
				

		


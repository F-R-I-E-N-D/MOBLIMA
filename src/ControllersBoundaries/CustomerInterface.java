package ControllersBoundaries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import CineplexClasses.Cineplex;
import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;
import CineplexClasses.Movie.ShowingStatus;
import CineplexClasses.Reservation;
import CineplexClasses.Show;
import Users.CustomerUser;

public class CustomerInterface extends UserInterface implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static CustomerInterface customerInterface = null;
    private CineplexGroup cineplex_group;

    private CustomerInterface() {}

    public static CustomerInterface getInstance() {
        if (customerInterface == null)
            customerInterface = new CustomerInterface();

        return customerInterface;
    }
    
    public void startInterface(Scanner sc, CineplexGroup cineplex_group, CustomerUser customer) 
    {    	
    	this.cineplex_group = cineplex_group;

        int option = -1;

        while (option != 4) {
            System.out.println("------------------------------------------------");
            System.out.println("Welcome to Golden Village!\nWhat would you like to do?\n");
            System.out.println("1.\tView Movies");
            System.out.println("2.\tBuy Movie Ticket");
            System.out.println("3.\tView Booking History");
            System.out.println("4.\tQuit"); // Add view reservations

            option = sc.nextInt();
            switch (option) {
                case 1: {
                    viewMovies();
                    break;
                }

                case 2: {
                    buyTicket(customer);
                    break;
                }

                case 3: {
                    printBookingHistory(customer);
                    break;
                }
            }
        }
    }

    private void printBookingHistory(CustomerUser customer) 
    {
    	for (Cineplex cplex : cineplex_group.getCineplexList())
    	{
    		for (Reservation r : cplex.getReservationList())
    		{
    			if (r.getUserID() == customer.getLoginID())
    			{
    				System.out.println("Reservation ID:\t" + r.getReservationID());
    				System.out.println("Date:\t" + r.getDate());
    				System.out.println("Movie Name:\t" + cplex.getShowList().get(r.getShowID()).getMovie().getTitle());
    				System.out.println("Seat:\t" + r.getRow() +"-"+ r.getLane() + "-" + r.getSeat());
    			}
    		}
    	}
	}

	// viewMovies method
    public void viewMovies() 
    {
        int option2 = -1;
        System.out.println("we are in "+ cineplex_group.getCineplexList().get(0).getCineplexName());
        while (option2 != 4) {
            System.out.println("------------------------------------------------");
            System.out.println("1.\tView All Movies");
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
                System.out.println("2.\tBack to Viewing Menu");
                option3 = sc.nextInt();
                if (option3 == 1) {
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
            }
        }
    }

    //buyTicket method
    public void buyTicket(CustomerUser customer) 
    {
        ArrayList<Cineplex> cineplexList = cineplex_group.getCineplexList();
        System.out.println("------------------------------------------------");
        System.out.println("Cineplexes:");
        for (Cineplex cineplex : cineplexList) {
            System.out.println("Cineplex ID: " + cineplex.getCineplexId());
            System.out.println("Cineplex Name: " + cineplex.getCineplexName());

        }
        System.out.println("Please enter Cineplex ID:");
        int cineplexID = sc.nextInt();
        Cineplex cineplexChoice = null;
        for (Cineplex cineplex : cineplexList) {
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
//            cineplex_group.createReservation(cineplexID, userID, showID, row, lane, seat);
            cineplex_group.createReservation(cineplexID, customer.getLoginID() ,showID,row,lane, seat);
        }
    }


    // Test
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CineplexGroup goldenVillage =  CineplexGroup.getInstance();

        CustomerInterface testInterface = CustomerInterface.getInstance();
        CustomerUser customer = new CustomerUser(0, "hello");
        testInterface.startInterface(sc, goldenVillage, customer);
        sc.close();
    }
}

		
				
				
				
				

		


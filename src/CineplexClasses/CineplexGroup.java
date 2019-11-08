package CineplexClasses;

import java.util.ArrayList;

public class CineplexGroup
{
    private ArrayList<Cineplex> cineplexList;
    private ArrayList<Movie> movieList;

    public CineplexGroup()
    {
        cineplexList = new ArrayList<Cineplex>();
        movieList = new ArrayList<Movie>();
    }
    
    // Cinema Hall
    
    public void createCinema (int cineplexID, String name, int num_rows, int [] column, Cinema.ClassType classtype)
    {
    	cineplexList.get(cineplexID).addCinemaToList(name, num_rows, column, classtype);
    }
    
    public void removeCinema (int cineplexID, int cinemaID)
    {
    	if (cineplexID>=0 && cineplexID<cineplexList.size() && cinemaID>=0 && cinemaID<cineplexList.get(cineplexID).getCinemaList().size())
    		cineplexList.get(cineplexID).removeCinemaFromlist(cinemaID);
        else
        	System.out.println("CineplexGroup -- removeCinema -- " + cineplexID + cinemaID);
    	
    }
    
    public ArrayList<Cinema> getCinemaList(int ciniplexID)
    {
    	return cineplexList.get(ciniplexID).getCinemaList();
    }
    
    // Cineplex
    
    public ArrayList<Cineplex> getCineplexList()
    {
    	return cineplexList;
    }
    
    public void addCineplexToList(String newCineplexName) 
    {
    	cineplexList.add(new Cineplex(cineplexList.size(), newCineplexName));
    }
    
    public void removeCineplexFromlist(int cineplexID) 
    {
        if (cineplexID>=0 && cineplexID<cineplexList.size())
        	cineplexList.remove(cineplexID);
        else
        	System.out.println("Invalid CineplexID");
    }
    
    // Movie
    
    public ArrayList<Movie> getMovieList()
    {
    	return movieList;
    }
    
    public void addMovieToList(String title, String synopsis, String[]cast, String director, Movie.Genre genre, Movie.Type type) 
    {
//    	new MovieID(MovieId, title, synopsis, cast, director, genre, type)
    	movieList.add(new Movie(movieList.size(), title, synopsis, cast, director, genre, type));
    }
    
    public void removeMovieFromlist(int movieID) 
    {
        if (movieID>=0 && movieID<movieList.size())
        	movieList.remove(movieID);
        else
        	System.out.println("Invalid MovieID");
    }    
    
    // Movie Review
    
    public void createReview(int movieID, String review, int rating, int userID)
    {
    	if (rating<=5 && rating>=1)
    		movieList.get(movieID).addReview(review, rating, userID);
    	else
    		System.out.println("Rating must be 1 and 5 (inclusive)");
    }
    
    public void removeReview (int movieID, int reviewID)
    {
    	movieList.get(movieID).deleteReview(reviewID);
    }
    
    public void printAvgRating (int movieID)
    {
    	double avg = movieList.get(movieID).getAvgRating();
    	if (avg==-1)
    	{
    		System.out.println("Not enough reviews to return for \"" + movieList.get(movieID).getTitle() + "\"");
    	}
    	else
    	{
    		System.out.println("Average rating for \"" + movieList.get(movieID).getTitle() + "\" = " + avg);
    	}
    }
    
    public ArrayList<MovieReview> getReviews(int movieID)
    {
    	return movieList.get(movieID).getMovieReviewAndRatingList();
    }
    
    // Shows
    
    public ArrayList<Show> getShowList(int cineplexID)
    {
    	return cineplexList.get(cineplexID).getShowList();
    }
    
    public void createShow (int cineplexID, int cinemaID, int movieID, int time_start, int time_end, Show.DayType daytype)
    {
    	if (time_start>=time_end || 0>time_start || time_start>2400 || 0>time_end || time_end>2400)
    	{
    		System.out.println ("Invalid Time");
			return;
    	}
    	
    	for (Show otherShow: getShowList(cineplexID))
    	{
    		if (cinemaID == otherShow.getHall().getHallId()) // same hall
    		{
    			if (!((otherShow.getTime_end()<=time_start)||(otherShow.getTime_start()>=time_end))) // time clash
    			{
    				// Assumption : shows happen only between 0000 hours and 2400 hours on the same day
    				System.out.println ("Time Slot Clash for given Cinema");
    				return;
    			}		
    		}
    	}
    	
    	cineplexList.get(cineplexID).addShowToList(movieList.get(movieID), cinemaID, time_start, time_end, daytype);
    }
    
    public void deleteShow (int cineplexID, int showID)
    {
    	cineplexList.get(cineplexID).removeShowFromlist(showID);
    }
    
    public void printShowLayout (int cineplexID, int showID)
    {
    	cineplexList.get(cineplexID).getShowList().get(showID).getLayout().printSeats();
    }
    
    // Reservation
    
    public ArrayList<Reservation> getReservations(int cineplexID)
    {
    	return cineplexList.get(cineplexID).getReservationList();
    }
    
    public void createReservation(int cineplexID, int userID, int showID, char row, int lane, int seat, String date_string)
    {
    	if (cineplexList.get(cineplexID).addReservationToList(userID, showID, row, lane, seat, date_string))
    		cineplexList.get(cineplexID).getShowList().get(showID).assignSeat(row, lane, seat);
    }
    
    public void deleteReservation(int cineplexID, int reservationID)
    {
    	Reservation r = cineplexList.get(cineplexID).getReservationList().get(reservationID);
    	int showID = r.getShowID();
    	char row = r.getRow();
    	int lane = r.getLane();
    	int seat = r.getSeat();
    	
    	if (cineplexList.get(cineplexID).getShowList().get(showID).unassignSeat(row, lane, seat))
    			cineplexList.get(cineplexID).removeReservationFromList(reservationID);
    }
    
    public int countNumTicketSales (int movieID)
    {
    	int count = 0;
    	for (Cineplex cplex : cineplexList)
    	{
    		for (Reservation r : cplex.getReservationList())
    		{	
    			if (cplex.getShowList().get(r.getShowID()).getMovie().getMovieId() == movieID)
    			{
    				count++;
    			}
    		}
    	}
    	return count;
    }
    
        
}
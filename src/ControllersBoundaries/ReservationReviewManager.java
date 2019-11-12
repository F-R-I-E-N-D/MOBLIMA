package ControllersBoundaries;

import CineplexClasses.CineplexGroup;
import CineplexClasses.Cineplex;
import CineplexClasses.Reservation;

public class ReservationReviewManager extends Manager
{
	private static final long serialVersionUID = 1L;
	
	// Reservation
    
    public void createReservation(CineplexGroup cineplexGroup, int cineplexID, int userID, int showID, char row, int lane, int seat)
    {
    	if (cineplexGroup.getCineplexList().get(cineplexID).addReservationToList(userID, showID, row, lane, seat))
    		cineplexGroup.getCineplexList().get(cineplexID).getShowList().get(showID).assignSeat(row, lane, seat);
    }
    
    public void deleteReservation(CineplexGroup cineplexGroup, int cineplexID, int reservationID)
    {
    	Reservation r = cineplexGroup.getCineplexList().get(cineplexID).getReservationList().get(reservationID);
    	int showID = r.getShowID();
    	char row = r.getRow();
    	int lane = r.getLane();
    	int seat = r.getSeat();
    	
    	if (cineplexGroup.getCineplexList().get(cineplexID).getShowList().get(showID).unassignSeat(row, lane, seat))
    		cineplexGroup.getCineplexList().get(cineplexID).removeReservationFromList(reservationID);
    }
    
    public int countNumTicketSales (CineplexGroup cineplexGroup, int movieID)
    {
    	int count = 0;
    	for (Cineplex cplex : cineplexGroup.getCineplexList())
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
	
	// Review

	public void createReview(CineplexGroup cineplexGroup, int movieID, String review, int rating, int userID)
    {
    	if (rating<=5 && rating>=1)
    	{
    		cineplexGroup.getMovieList().get(movieID).addReview(review, rating, userID);
    	}    		
    	else
    		System.out.println("Rating must be 1 and 5 (inclusive)");
    }
    
    public void removeReview (CineplexGroup cineplexGroup, int movieID, int reviewID)
    {
    	cineplexGroup.getMovieList().get(movieID).deleteReview(reviewID);
    }
    
    public void printAvgRating (CineplexGroup cineplexGroup, int movieID)
    {
    	double avg = cineplexGroup.getMovieList().get(movieID).getAvgRating();
    	if (avg==-1)
    	{
    		System.out.println("Not enough reviews to return for \"" + cineplexGroup.getMovieList().get(movieID).getTitle() + "\"");
    	}
    	else
    	{
    		System.out.println("Average rating for \"" + cineplexGroup.getMovieList().get(movieID).getTitle() + "\" = " + avg);
    	}
    }
    
    


}

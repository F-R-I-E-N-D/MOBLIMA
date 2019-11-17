package ControllersBoundaries;

import CineplexClasses.Cineplex;
import CineplexClasses.CineplexController;
import CineplexClasses.Reservation;

public class ReservationController 
{	
	// Reservation
    public boolean createReservation(CineplexController cineplexGroup, int cineplexID, String userID, int showID, char row, int lane, int seat, double price)
    {
    	if (cineplexGroup.getCineplexList().get(cineplexID).addReservationToList(userID, showID, row, lane, seat, price))
    	{
    		cineplexGroup.getCineplexList().get(cineplexID).getShowList().get(showID).assignSeat(row, lane, seat);
    		return true;
    	}
    	else
    		return false;
    }
    
    public void deleteReservation(CineplexController cineplexGroup, int cineplexID, int reservationID)
    {
    	Reservation r = cineplexGroup.getCineplexList().get(cineplexID).getReservationList().get(reservationID);
    	int showID = r.getShowID();
    	char row = r.getRow();
    	int lane = r.getLane();
    	int seat = r.getSeat();
    	
    	if (cineplexGroup.getCineplexList().get(cineplexID).getShowList().get(showID).unassignSeat(row, lane, seat))
    		cineplexGroup.getCineplexList().get(cineplexID).removeReservationFromList(reservationID);
    }
    
    public int countNumTicketSales (CineplexController cineplexGroup, int movieID)
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
}

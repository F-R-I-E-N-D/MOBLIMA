package CiniplexClasses;

import java.util.ArrayList;

public class Cineplex
{
    // instance variables - replace the example below with your own
    private ArrayList<Show> showList;
    private String cineplexName;
    private int cineplexId;
    private ArrayList<Cinema> cinemaList;
    private ArrayList<Reservation> reservationList;
    
    public Cineplex(int newId, String name) 
    {
        showList = new ArrayList<Show>();
        cineplexId = newId;
        cineplexName = name;
        cinemaList = new ArrayList<Cinema>();
        reservationList = new ArrayList<Reservation>();
    }
    
    public String getCineplexName() 
    {
        return cineplexName;
    }
    public int getCineplexId() 
    {
        return cineplexId;
    }
    public void setCineplexName(String name) 
    {
        cineplexName = name;
    }
    
    // Show
    public void addShowToList(Movie movie, int hall_id, int time_start, int time_end, Show.DayType daytype) 
    {
        showList.add(new Show(showList.size(), movie, cinemaList.get(hall_id), time_start, time_end, daytype));
    }
    
    public void removeShowFromlist(int showID) 
    {
    	if (showID>=0 && showID<showList.size())
    		showList.remove(showID);
        else
        	System.out.println("Invalid ShowID");
    }
    
    public ArrayList<Show> getShowList()
    {
        return showList;
    }
    
    // Cinema
    public ArrayList<Cinema> getCinemaList()
    {
    	return cinemaList;
    }
    
    public void addCinemaToList(String name, int num_rows, int [] column, Cinema.ClassType classtype) 
    {
        cinemaList.add(new Cinema(cinemaList.size(), name, num_rows, column, classtype));
    }
    
    public void removeCinemaFromlist(int cinemaID) 
    {
    	if (cinemaID>=0 && cinemaID<cinemaList.size())
    		cinemaList.remove(cinemaID);
        else
        	System.out.println("Ciniplex -- removeCinemaFromlist --" + cinemaID);
    }
    
    // Reservation
    public ArrayList<Reservation> getReservationList() 
    {
		return reservationList;
	}
    
    public boolean addReservationToList(int userID, int showID, char row, int lane, int seat)
    {
    	try
    	{
	    	reservationList.add(new Reservation(reservationList.size(), userID, showID, row, lane, seat));
	    	return true;
    	}
    	catch (Exception e)
    	{
    		return false;
    	}
    }
    
    public boolean removeReservationFromList (int reservationID)
    {
    	try
    	{
    		reservationList.remove(reservationID);
    		return true;
    	}
    	catch (Exception e)
    	{
    		return false;
    	}
    }
}
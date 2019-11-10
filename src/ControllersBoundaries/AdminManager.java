package ControllersBoundaries;

import java.io.Serializable;

import CineplexClasses.Cinema;
import CineplexClasses.Cineplex;
import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;
import CineplexClasses.Show;


public class AdminManager extends Manager implements Serializable
{
	private static final long serialVersionUID = 1L;
	private CineplexGroup cineplexGroup; 
		
	public AdminManager (CineplexGroup cineplexGroup)
	{
		this.cineplexGroup = cineplexGroup;
	}
    
	// Cinema Hall
    public void createCinema (int cineplexID, String name, int num_rows, int [] column, Cinema.ClassType classtype)
    {
    	cineplexGroup.getCineplexList().get(cineplexID).addCinemaToList(name, num_rows, column, classtype);   
    }
    
    public void removeCinema (int cineplexID, int cinemaID)
    {
    	if (cineplexID>=0 && cineplexID<cineplexGroup.getCineplexList().size() && cinemaID>=0 && cinemaID<cineplexGroup.getCineplexList().get(cineplexID).getCinemaList().size())
    		cineplexGroup.getCineplexList().get(cineplexID).removeCinemaFromlist(cinemaID);
        else
        	System.out.println("CineplexGroup -- removeCinema -- " + cineplexID + cinemaID);
    }
    
    // Cineplex
    public void addCineplexToList(String newCineplexName) 
    {
    	cineplexGroup.getCineplexList().add(new Cineplex(cineplexGroup.getCineplexList().size(), newCineplexName));
    }
    
    public void removeCineplexFromlist(int cineplexID) 
    {
        if (cineplexID>=0 && cineplexID<cineplexGroup.getCineplexList().size())
        	cineplexGroup.getCineplexList().remove(cineplexID);
        else
        	System.out.println("Invalid CineplexID");
    }
    
    // Shows
    
    public void createShow (int cineplexID, int cinemaID, int movieID, int time_start, int time_end, Show.DayType daytype)
    {
    	if (time_start>=time_end || 0>time_start || time_start>2400 || 0>time_end || time_end>2400)
    	{
    		System.out.println ("Invalid Time");
			return;
    	}
    	
    	for (Show otherShow: cineplexGroup.getShowList(cineplexID))
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
    	
    	cineplexGroup.getCineplexList().get(cineplexID).addShowToList(cineplexGroup.getMovieList().get(movieID), cinemaID, time_start, time_end, daytype);
    }
    
    public void deleteShow (int cineplexID, int showID)
    {
    	cineplexGroup.getCineplexList().get(cineplexID).removeShowFromlist(showID);
    }
    
    public void printShowLayout (int cineplexID, int showID)
    {
    	cineplexGroup.getCineplexList().get(cineplexID).getShowList().get(showID).getLayout().printSeats();
    }
    
    // Movie
    
    public void addMovieToList(String title, String synopsis, String[]cast, String director, Movie.Genre genre, Movie.Type type) 
    {    	
    	cineplexGroup.addMovieToList(title, synopsis, cast, director, genre, type);
//    	System.out.println(title + "|" +cineplexGroup.getMovieList().size());
    }
    
    public void removeMovieFromlist(int movieID) 
    {
        if (movieID>=0 && movieID<cineplexGroup.getMovieList().size())
        	cineplexGroup.getMovieList().remove(movieID);
        else
        	System.out.println("Invalid MovieID");
    }   
    
    
}


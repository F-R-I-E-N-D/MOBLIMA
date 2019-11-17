package CineplexClasses;

import java.util.ArrayList;
public class CineplexController implements java.io.Serializable
{

	private static final long serialVersionUID = 6771695211220580754L;
	private ArrayList<Cineplex> cineplexList;
    private ArrayList<Movie> movieList;
    
    public CineplexController()
    {
        cineplexList = new ArrayList<Cineplex>();
        movieList = new ArrayList<Movie>();
    }
    
    public ArrayList<Cinema> getCinemaList(int ciniplexID)
    {
    	return cineplexList.get(ciniplexID).getCinemaList();
    }
    
    public ArrayList<Cineplex> getCineplexList()
    {
    	return cineplexList;
    }
    
    public ArrayList<Movie> getMovieList()
    {
    	return movieList;
    }
   
    public ArrayList<MovieReview> getReviews(int movieID)
    {
    	return movieList.get(movieID).getMovieReviewAndRatingList();
    }
    
    public ArrayList<Show> getShowList(int cineplexID)
    {
    	return cineplexList.get(cineplexID).getShowList();
    }
    
    public ArrayList<Reservation> getReservations(int cineplexID)
    {
    	return cineplexList.get(cineplexID).getReservationList();
    }
        
}
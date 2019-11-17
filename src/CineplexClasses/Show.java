package CineplexClasses;

import java.util.Date;

public class Show implements java.io.Serializable
{

	private static final long serialVersionUID = -3315757482151658013L;

	public enum DayType {WEEKDAY, WEEKEND, PUBLIC_HOLIDAY}
	
    private Movie movie;
    private int showID;
    private Cinema hall;
    private Layout hallLayout;
    private int time_start, time_end;
    private Date show_date;
    private DayType daytype;
    private int ticketsSold;
    
    public Show (int showID, Movie movie, Cinema hall, int time_start, int time_end, DayType daytype, Date show_date)
    {
        this.setShowID(showID);
    	this.hall = hall;
        this.movie = movie;
        setLayout(hall);
        setTime_end(time_end);
        setTime_start(time_start);
        this.setDaytype(daytype);
        this.setShow_date(show_date);
        ticketsSold = 0;
    }
    
    private void setShowID(int showID) {
		this.showID = showID;
	}

	// Times
    public int getTime_start() {
        return time_start;
    }

    public void setTime_start(int time_start) {
        this.time_start = time_start;
    }

    public int getTime_end() {
        return time_end;
    }

    public void setTime_end(int time_end) {
        this.time_end = time_end;
    }
    
    //Movie
    public Movie getMovie()
    {
        return movie;
    }
    
    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }
    
    // Cinema
    public Cinema getHall()
    {
        return hall;
    }
    
    public void changeHall(Cinema newHall){
        hall = newHall;
        setLayout(hall);
    }
    
    // Layout
    public Layout getLayout()
    {
    	return hallLayout;   
    }
 
    private void setLayout(Cinema hall)
    {
        hallLayout = new Layout(hall.getNumRows(), hall.getNumHandicappedRows(), hall.getColumn(), hall.getColumnHandicapped());
    }
    
    public void printLayout()
    {
 	   hallLayout.printSeats();
    }
    
	// Seats
	public void assignSeat(char row_letter, int lane, int seat)
	{	   
	   int row = (int)(row_letter-'A');
	   lane--;
	   seat--;
	   hallLayout.getlane(row, lane).assignSeat(seat);    
	}
	    
	public boolean unassignSeat(char row_letter, int lane, int seat)
	{
	   try
	   {
		   int row = (int)(row_letter-'A');
		   lane--;
		   seat--;
		   hallLayout.getlane(row, lane).unassignSeat(seat);
		   return true;
	   }
	   catch (Exception e)
	   {
		   return false;
	   }
	}

	public int getShowID() 
	{
		return showID;
	}

	public DayType getDaytype() 
	{
		return daytype;
	}

	public void setDaytype(DayType daytype) 
	{
		this.daytype = daytype;
	}

	public Date getShow_date() 
	{
		return show_date;
	}

	public void setShow_date(Date show_date) 
	{
		this.show_date = show_date;
	}
	
	//tickets sold
	public int getTicketsSold() 
	{
		return ticketsSold;
	}

	public void setTicketsSold(int sold) 
	{
		ticketsSold = sold;
	}
	
	public void addTicketsSold(int newTickets) 
	{
		ticketsSold = ticketsSold + newTickets;
		movie.addMovieSales(newTickets);
	}
}
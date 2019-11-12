package CineplexClasses;
import java.io.*;
import java.util.Date;

public class Reservation implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int reservationID;
	private String username;
	private int showID;
	private char row;
	private int lane;
	private int seat;
	private Date reservationDate;
	
	public Reservation(int reservationID, String username, int showID, char row, int lane, int seat)
	{
		this.setReservationID(reservationID);
		this.username = username;
		this.showID = showID;
		this.setRow(row);
		this.setLane(lane);
		this.setSeat(seat);
		setReservationDate(new Date()); // Initialised with current date
	}

	public String getUsername() 
	{
		return username;
	}

	public int getShowID() {
		return showID;
	}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public char getRow() {
		return row;
	}

	public void setRow(char row) {
		this.row = row;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date date) {
		this.reservationDate = date;
	}
	
}

package CineplexClasses;

import java.util.Date;

public class Reservation {
	
	private int reservationID;
	private int userID;
	private int showID;
	private char row;
	private int lane;
	private int seat;
	private Date date;
	
	@SuppressWarnings("deprecation")
	public Reservation(int reservationID, int userID, int showID, char row, int lane, int seat, String date_string)
	{
		this.setReservationID(reservationID);
		this.userID = userID;
		this.showID = showID;
		this.setRow(row);
		this.setLane(lane);
		this.setSeat(seat);
		setDate(new Date(date_string));
	}

	public int getUserID() 
	{
		return userID;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

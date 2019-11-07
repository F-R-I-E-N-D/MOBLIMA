package CiniplexClasses;

public class Reservation {
	
	private int reservationID;
	private int userID;
	private int showID;
	private char row;
	private int lane;
	private int seat;
	
	public Reservation(int reservationID, int userID, int showID, char row, int lane, int seat)
	{
		this.setReservationID(reservationID);
		this.userID = userID;
		this.showID = showID;
		this.setRow(row);
		this.setLane(lane);
		this.setSeat(seat);
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
	
}

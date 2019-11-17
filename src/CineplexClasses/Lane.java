package CineplexClasses;
import java.io.*;

public class Lane implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean [] seats;
	protected int num_seats;
	
	public Lane (int num_seats)
	{
		seats = new boolean[num_seats];
		this.num_seats =num_seats;
		for (int i=0; i<num_seats; i++)
		{
			seats[i] = false;
		}
	}

	public boolean getSeat(int index) {
		return seats[index];
	}

	public boolean assignSeat(int index) {
		if (index>=0 && index<seats.length)
		{
			if (seats [index])
			{
				System.out.println("Already Occupied");
				return false;
			}
			seats[index] = true;
			return true;
		}
		else
		{
			System.out.println(index+ "Invalid Index");
			return false;
		}
	}
	
	public boolean unassignSeat(int index) {
		if (index>=0 && index<seats.length)
		{
			if (!seats [index])
			{
				System.out.println("Already Empty");
				return false;
			}
			seats[index] = false;
			return true;
		}
		else
		{
			System.out.println("Invalid Index");
			return false;
		}
	}

	public int getNum_seats() 
	{
		return num_seats;
	}
	
	public void printLane()
	{
		
		//
		for (int k=0; k<getNum_seats(); k++)
		{
			if (getSeat(k))
				System.out.print(" [_X_] \t");
			else
				System.out.print(" [___] \t");
		}
		System.out.print("\t");
		//
			
		
	}
}

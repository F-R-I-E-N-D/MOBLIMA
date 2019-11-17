package CineplexClasses;

public class LaneHandicapped extends Lane
{
	private static final long serialVersionUID = 8305680571937814405L;
	private int handicapped_seats;
	
	public LaneHandicapped(int total_num_seats, int handicapped_seats) 
	{
		super(total_num_seats);
		this.setHandicapped_seats(handicapped_seats);
	}

	public int getHandicapped_seats() 
	{
		return handicapped_seats;
	}

	public void setHandicapped_seats(int handicapped_seats) 
	{
		this.handicapped_seats = handicapped_seats;
	}

	public void printLane()
	{
		int handicappedSeats = handicapped_seats;
		for (int k=0; k<getNum_seats(); k++)
		{
			if (handicappedSeats-- > 0)
			{
				if (getSeat(k))
					System.out.print("H[_X_]H\t");
				else
					System.out.print("H[___]H\t");
			}
			else
			{
				if (getSeat(k))
					System.out.print(" [_X_] \t");
				else
					System.out.print(" [___] \t");
			}
		}
		System.out.print("\t");
		//
	}
	
	
}

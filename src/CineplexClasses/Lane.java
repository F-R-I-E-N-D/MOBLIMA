package CineplexClasses;

public class Lane {
	private boolean [] seats;
	private int num_seats;
	
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

	public int getNum_seats() {
		return num_seats;
	}
}

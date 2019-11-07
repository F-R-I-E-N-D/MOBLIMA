package CiniplexClasses;

public class Layout {
	
	private Lane [][] seats;
	
	public Layout (int num_rows, int[] column) {
		
		initialiseSeats(num_rows, column); 
	}

	
	private void initialiseSeats(int num_rows, int[] column) {
		
		seats = new Lane [num_rows][column.length];
		
		for (int i =0; i< num_rows; i++)
		{
			for (int j=0; j<column.length; j++)
			{
				seats[i][j] = new Lane (column[j]);	
			}
		}
	}
	
	public Lane getlane(int row, int lane)
	{
		return seats[row][lane];
	}
	
	public void printSeats ()
	{
		Lane [] row;
		Lane lane;
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		
		System.out.println("\t\t===SCREEN===");
		
		for (int i=0; i<seats.length; i++)
		{
			row = seats[i];
			for (int j=0; j<row.length; j++)
			{
				lane = row[j];
				for (int k=0; k<lane.getNum_seats(); k++)
				{
					if (lane.getSeat(k))
						System.out.print("[_X_]\t");
					else
						System.out.print("[___]\t");
				}
				System.out.print("\t");
			}
			System.out.println();
			for (int j=0;j< row.length; j++)
			{
				lane = row[j];
				for (int k=0; k<lane.getNum_seats(); k++)
				{
					System.out.print(alpha.charAt(i)+"-"+(j+1)+"-"+(k+1)+"\t");
				}
				System.out.print("\t");
			}
			System.out.println();
		}
	}
	
	// FOR TESTING
	public static void main (String [] args)
	{
		int [] column = {2,3,2};
		Layout t = new Layout (4, column);
		t.seats[3][1].assignSeat(2);
		t.printSeats();
	}

}

package CineplexClasses;
import java.io.*;
public class Layout implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private Lane [][] seats;
	
	public Layout (int num_rows, int[] column) 
	{
		initialiseSeats(num_rows, column); 
	}
	
	public Layout (int num_rows, int num_handicapped_rows, int[] column_normal, int[] column_handicapped) 
	{
		initialiseSeats(num_rows, num_handicapped_rows, column_normal, column_handicapped); 
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
	
	private void initialiseSeats(int num_rows, int num_handicapped_rows, int[] column_normal, int[] column_handicapped) 
	{
		
		seats = new Lane [num_rows][column_normal.length];
		
		for (int i =0; i< num_rows; i++)
		{
			if(num_handicapped_rows-- >0)
			{
				for (int j=0; j<column_normal.length; j++)
				{
					seats[i][j] = new LaneHandicapped(column_normal[j], column_handicapped[j]);
				}
			}
			else
			{
				for (int j=0; j<column_normal.length; j++)
				{
					seats[i][j] = new Lane (column_normal[j]);	
				}
			}
		}
	}
	
	public Lane getlane(int row, int lane)
	{
		return seats[row][lane];
	}
	
//	public void printSeats ()
//	{
//		Lane [] row;
//		Lane lane;
//		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		
//		
//		System.out.println("\t\t===SCREEN===");
//		
//		for (int i=0; i<seats.length; i++)
//		{
//			row = seats[i];
//			for (int j=0; j<row.length; j++)
//			{
//				lane = row[j];
//				for (int k=0; k<lane.getNum_seats(); k++)
//				{
//					if (lane.getSeat(k))
//						System.out.print("[_X_]\t");
//					else
//						System.out.print("[___]\t");
//				}
//				System.out.print("\t");
//			}
//			System.out.println();
//			for (int j=0;j< row.length; j++)
//			{
//				lane = row[j];
//				for (int k=0; k<lane.getNum_seats(); k++)
//				{
//					System.out.print(alpha.charAt(i)+"-"+(j+1)+"-"+(k+1)+"\t");
//				}
//				System.out.print("\t");
//			}
//			System.out.println();
//		}
//	}
	
	public void printSeats () // Example for LSP
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
				lane.printLane();
			}
			System.out.println();
			for (int j=0;j< row.length; j++)
			{
				lane = row[j];
				for (int k=0; k<lane.getNum_seats(); k++)
				{
					System.out.print(" " + alpha.charAt(i)+"-"+(j+1)+"-"+(k+1)+"\t");
				}
				System.out.print("\t");
			}
			System.out.println();
		}
	}
	
//	// FOR TESTING
//	public static void main (String [] args)
//	{
//		int [] column = {2,3,2};
////		Layout t = new Layout (4, column);
////		t.seats[3][1].assignSeat(2);
////		t.printSeats();
////		t.printSeatsNew();
////		
////		System.out.println("----------");
//		
//		int[] column_hand = {1,0,1};
//		Layout t1 = new Layout (4, 5, column, column_hand);
//		t1.seats[3][1].assignSeat(2);
//		t1.printSeats();
//	}

}

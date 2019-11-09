package ControllersBoundaries;

import java.util.Scanner;
import java.util.ArrayList;

public abstract class UserInterface {
	
	protected Scanner sc = new Scanner(System.in);
	
	public UserInterface() 
	{
		
	}
	
	public abstract void startInterface(Scanner sc, CiniplexClasses.CineplexGroup cplex_grp);
	
	public int [] getIntegerArray(String message)
	{
		System.out.println(message);
		System.out.println("(end with -1)");
		int input = -1;
		int count = 1;
		ArrayList<Integer> column = new ArrayList<Integer>();
		while (true)
		{
			input = getOnlyInteger("Item " + Integer.toString(count));
			if (input<=0)
				break;
			column.add(input);
			count++;
		}
		
		return column.stream().mapToInt(i->i).toArray();
	}
	
	public String getString (String message)
	{
		String input = "";
		System.out.println(message);
		while (input.isBlank())
		{
			input = sc.nextLine();
		}
//		System.out.println("Input \'" + input + "'");
		return input; 
	}
	
	public int getOnlyInteger(String message) 
	{
		int option = -1;
		while(true) 
		{
			System.out.println(message);
			if (sc.hasNextInt())
			{
				option = sc.nextInt();
				break;	
			}
			else
				System.out.println("Only integers are allowed. Please try again");
			
		}
		
		return option;
	}
	
}


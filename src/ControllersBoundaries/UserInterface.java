package ControllersBoundaries;

import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import CineplexClasses.CineplexGroup;

public abstract class UserInterface implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Scanner sc = new Scanner(System.in);
	
	public UserInterface() 
	{
		
	}
	
	public int [] getIntegerArray(String message)
	{
		System.out.println(message);
		System.out.println("(end with -1)");
		int input = -1;
		int count = 1;
		ArrayList<Integer> column = new ArrayList<Integer>();
		while (true)
		{
			input = getOnlyInteger("Item " + Integer.toString(count), -1, 26); // 26 letters possible
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
		System.out.println(message);
		try
		{
			option = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Only integers are allowed. Please try again");
			sc.next();
			option = getOnlyInteger(message); 
		}
		return option;
	}
	
	
	public int getOnlyInteger(String message, int low_inclusive, int high_inclusive) 
	{
		int option = -1;
		System.out.println(message);
		try
		{
			option = sc.nextInt();
			if (option<low_inclusive || option > high_inclusive)
			{
				System.out.println("Out of bounds. Please try again");
				option = getOnlyInteger(message, low_inclusive, high_inclusive); 
			}
		}
		catch(Exception e)
		{
			System.out.println("Only integers are allowed. Please try again");
			sc.next();
			option = getOnlyInteger(message, low_inclusive, high_inclusive); 	
		}
		return option;
	}
	
	public double getOnlyDouble (String message, double low_inclusive, double high_inclusive) 
	{
		double return_double = -1;
		System.out.println(message);
		try
		{
			return_double = sc.nextDouble();
			if (return_double<low_inclusive || return_double > high_inclusive)
			{
				System.out.println("Out of bounds. Please try again");
				return_double = getOnlyDouble(message, low_inclusive, high_inclusive); 
			}
		}
		catch(Exception e)
		{
			System.out.println("Only integers are allowed. Please try again");
			sc.next();
			return_double = getOnlyDouble(message, low_inclusive, high_inclusive); 	
		}
		return return_double;
	}
	
	
	public void printStringArray(String message, String [] array)
	{
		System.out.println(message);
		for (String s : array)
		{
			System.out.print(s + ", ");
		}
		System.out.println();
	}
	
}


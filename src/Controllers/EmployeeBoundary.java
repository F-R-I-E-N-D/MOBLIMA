package Controllers;

import CiniplexClasses.*;
import CiniplexClasses.Cinema.ClassType;

import java.util.Arrays;
import java.util.Scanner;

public class EmployeeBoundary extends UserInterface
{
	private static EmployeeBoundary employeeBoundary = null; 
	private CineplexGroup cineplex_group ;
	  
	private EmployeeBoundary() {}
	
	public static EmployeeBoundary getInstance()
	{
		if (employeeBoundary == null) 
			employeeBoundary = new EmployeeBoundary(); 
  
        return employeeBoundary; 
	}
	
	@Override
	public void startInterface(Scanner sc, CineplexGroup cineplex_group) 
	{	
		
		this.cineplex_group = cineplex_group;
		
		int option = -1;
		
		while (option!=4)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Welcome, Administrator\nWhat would you like to do?\n");
			System.out.println("1.\tCineplex Options");
			System.out.println("2.\tMovie-Show Options");
			System.out.println("3.\tMovie Options");
			System.out.println("4.\tQuit"); // Add view reservations
			
			option = getOnlyInteger("Option: ");

			switch (option)
			{
				case 1:
					cineplexOptions(sc);
					break;
				case 2:
					showOptions(sc);
					break;
				case 3:
					movieOptions(sc);
					break;
			}
		}
		
		System.out.println("------------------------------------------------");
		
	}

	private void cineplexOptions(Scanner sc) 
	{
		int option = -1;
		
		while (option!=6)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Cineplex Options:\n");
			System.out.println("1.\tCreate Cineplex");
			System.out.println("2.\tRemove Cineplex");
			System.out.println("3.\tView Cineplexes (+ Constituent Cinemas)");
			System.out.println("4.\tAdd Cinema to Cineplex");
			System.out.println("5.\tRemove Cinema from Cineplex");
			System.out.println("6.\tBack to Admin Menu");
			
			option = getOnlyInteger("Option: ");
			System.out.println("==========================");
			switch (option)
			{
				case 1:
					String name = getString("Enter Cineplex Name:");
					cineplex_group.addCineplexToList(name);					
					break;
				case 2:
					System.out.println("Cineplexes:\n");
					for (Cineplex cplex : cineplex_group.getCineplexList())
					{
						System.out.println("\nCineplex Id=\t" + cplex.getCineplexId());
						System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
					}
					
					System.out.print("Enter Cineplex Id to delete:\t");
					cineplex_group.removeCineplexFromlist(sc.nextInt());					
					break;
				case 3:
					viewCineplexes();
					break;
					
				case 4:
					System.out.println("Cineplexes & Cinemas:\n");
					viewCineplexes();
					int cplexID = getOnlyInteger("Enter Cineplex Id:\t");
					String cinemaName = getString("Enter New Cinema Name:");
					int num_rows = getOnlyInteger("Enter number of rows:\t");
					
					/*
					System.out.println("Enter lanes' seats (end with -1)");
					int input = -1;
					int count = 1;
					ArrayList<Integer> column = new ArrayList<Integer>();
					while (true)
					{
						input = getOnlyInteger("Lane " + Integer.toString(count)
						if (input<=0)
							break;
						column.add(input);
						count++;
					}
					
					int[] _column = column.stream().mapToInt(i->i).toArray();
					*/
					
					int[] _column = getIntegerArray("Enter Seats Per Lane");
				
					cineplex_group.createCinema(cplexID, cinemaName, num_rows, _column, ClassType.GOLD);				
					break;
					
				case 5:
					System.out.println("Cineplexes & Cinemas:\n");
					viewCineplexes();
					int cplexId = getOnlyInteger("Enter Cineplex Id:\t");
					int cinemaID = getOnlyInteger("Enter Cinema Id:\t");
					cineplex_group.removeCinema(cplexId, cinemaID);					
					break;
				
			}
		}
		
	}
	
	private void showOptions(Scanner sc) 
	{
		
	}
	
	private void movieOptions(Scanner sc) 
	{
		
	}
	
	private void viewCineplexes ()
	{
		for (Cineplex cplex : cineplex_group.getCineplexList())
		{
			System.out.println("Cineplex Id=\t" + cplex.getCineplexId());
			System.out.println("Cineplex Name=\t" + cplex.getCineplexName());
			
			for (Cinema cinema: cplex.getCinemaList())
			{
				System.out.println("\nCinema Id=\t" + cinema.getHallId());
				System.out.println("Cinema Name=\t" + cinema.getName());
				System.out.println("Cinema Rows=\t" + cinema.getNumRows());
				System.out.println("Cinema Lanes=\t" + Arrays.toString(cinema.getColumn()));
			}
			
			System.out.println("\n-------\n");
		}
	}
	
	
	// Test
	public static void main (String [] args)
	{
		Scanner sc = new Scanner(System.in);
		
		CineplexGroup goldenVillage = new CineplexGroup();
		
		EmployeeBoundary testInterface = EmployeeBoundary.getInstance();
		testInterface.startInterface(sc , goldenVillage);
		sc.close();
	}
}

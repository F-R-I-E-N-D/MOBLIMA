package ControllersBoundaries;

import java.io.*;
import java.util.Scanner;

import CineplexClasses.CineplexGroup;
import ControllersBoundaries.LoginInterface.UserType;
import Users.CustomerUser;

public class MainController implements Serializable
{	
	private static final long serialVersionUID = 8130355990188225040L;

	public MainController() 
	{
		
	}
	
	//static method used as an instance of MainController is never needed
	public static void start() {
		// load all existing data into the single instance of ReservationManager
		Scanner sc = new Scanner(System.in);
		
		ReservationManager reservationManager = ReservationManager.getInstance();
		//saveFile("CustomerData", reservationManager);
		loadFile("CustomerData", reservationManager);
		
		//load all existing data into the single instance of EmployeeManager
		AdminManager adminManager = AdminManager.getInstance();
		//saveFile("EmployeeData", adminManager);
		loadFile("EmployeeData", adminManager);
		//load all existing data into the single instance of CineplexGroup
		CineplexGroup cineplexGroup = CineplexGroup.getInstance(); 
		loadFile("CinemaData", cineplexGroup);
		
		System.out.println("We got here; Movie 1: " + cineplexGroup.getMovieList().get(0).getTitle());
		
		//saveFile("CinemaData", cineplexGroup);
		
	
		//sets up LoginInterface to identify if user is customer or employee
		LoginInterface loginInterface = new LoginInterface();
		loginInterface.print();
		
		//create singleton or access existing instance of ReservationInterface or AdminInterface
		//only the Interface needed will be created/accessed
		if(loginInterface.getUserType()== UserType.Customer) 
		{
			CustomerUser cy = new CustomerUser(2, "kjsdnfsn");
			System.out.println(cineplexGroup.getMovieList().get(0).getTitle());
			CustomerInterface.getInstance().startInterface(sc, cineplexGroup, cy);
			
			
			saveFile("CinemaData", cineplexGroup);
			saveFile("EmployeeData", adminManager);
		}
		else 
		{
			EmployeeInterface.getInstance().startInterface(sc, cineplexGroup);
			saveFile("EmployeeData", adminManager);
			saveFile("CinemaData", cineplexGroup);
		}
	}
	public static void end() {
		ReservationManager reservationManager = ReservationManager.getInstance();
		saveFile("CustomerData", reservationManager);
		AdminManager adminManager = AdminManager.getInstance();
		saveFile("EmployeeData", adminManager);
		CineplexGroup cineplexGroup = CineplexGroup.getInstance(); 
		saveFile("CinemaData", cineplexGroup);
		
	}
	
	public static void saveFile(String s, Manager m){
        try
        {
            FileOutputStream fileOut = new FileOutputStream(s + ".ser");//creates a card serial file in output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);//routes an object into the output stream.
            out.writeObject(m);// we designate our array of cards to be routed
            out.close();// closes the data paths
            fileOut.close();// closes the data paths
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
        }
    }
	
	public static void loadFile(String s, Manager m){
	    try{// If this doesn't work throw an exception
	        FileInputStream fileIn = new FileInputStream(s+".ser");// Read serial file.
	        ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
	        m = (Manager)in.readObject();// allocate it to the object file already instantiated.
	        in.close();//closes the input stream.
	        fileIn.close();//closes the file data stream.
	    }
	    catch(IOException i) {//exception stuff
	        i.printStackTrace();
	        return;
	    }
		catch(ClassNotFoundException c) {//more exception stuff
	        System.out.println("Error");
	        c.printStackTrace();
	        return;
    	}
	}
	
	public static void saveFile(String s, CineplexGroup cplex_grp){
        try
        {
            FileOutputStream fileOut = new FileOutputStream(s + ".ser");//creates a card serial file in output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);//routes an object into the output stream.
            out.writeObject(cplex_grp);// we designate our array of cards to be routed
            out.close();// closes the data paths
            fileOut.close();// closes the data paths
        }
        catch(IOException i)//exception stuff
        {
            i.printStackTrace();
        }
    }
	
	public static void loadFile(String s, CineplexGroup cplex_grp){
	    try{// If this doesn't work throw an exception
	        FileInputStream fileIn = new FileInputStream(s+".ser");// Read serial file.
	        ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
	        cplex_grp = (CineplexGroup)in.readObject();// allocate it to the object file already instantiated.
	        in.close();//closes the input stream.
	        fileIn.close();//closes the file data stream.
	        System.out.println("Successful load");
	        System.out.println("Movie 1: " + cplex_grp.getMovieList().get(0).getTitle());
	    }
	    catch(IOException i) {//exception stuff
	        i.printStackTrace();
	        return;
	    }
		catch(ClassNotFoundException c) {//more exception stuff
	        System.out.println("Error");
	        c.printStackTrace();
	        return;
    	}
	}
	public static void main(String[] args) {
		MainController m = new MainController();
		m.start();
		m.end();
		
	}
}


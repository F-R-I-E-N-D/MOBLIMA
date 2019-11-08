package ControllersBoundaries;

import java.io.*;

public class MainController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8130355990188225040L;

	public MainController() {
		
	}
	
	//static method used as an instance of MainController is never needed
	public static void start() {
		// load all existing data into the single instance of ReservationManager
		ReservationManager reservationManager = ReservationManager.getInstance();
		loadFile("CustomerData", reservationManager);
		
		//load all existing data into the single instance of EmployeeManager
		AdminManager adminManager = AdminManager.getInstance(); 
		loadFile("EmployeeData", adminManager);
		saveFile("EmployeeData", adminManager);
		//load all existing data into the single instance of CineplexGroup
		CineplexGroup cineplexGroup = CineplexGroup.getInstance(); 
		loadFile("CinemaData", cineplexGroup);
	
		//sets up LoginInterface to identify if user is customer or employee
		LoginInterface loginInterface = new LoginInterface();
		loginInterface.print();
		
		//create singleton or access existing instance of ReservationInterface or AdminInterface
		//only the Interface needed will be created/accessed
		if(loginInterface.getUserType()==UserType.Customer) {
			ReservationInterface.getInstance().print();
		}
		else {
			AdminInterface.getInstance().print();
		}
	}
	public static void end() {
		ReservationManager reservationManager = ReservationManager.getInstance();
		saveFile("CustomerData", reservationManager);
		AdminManager adminManager = AdminManager.getInstance();
		saveFile("EmployeeData", adminManager);
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
}


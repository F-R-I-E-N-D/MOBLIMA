package ControllersBoundaries;

import java.io.*;

import CineplexClasses.CineplexGroup;

public class FileManager
{		
	private CineplexGroup cineplexGroup;
	private LoginManager loginManager; 
	private ReservationManager reservationManager;
	private PriceManager priceManager;
	private MovieManager movieManager;
	private AdminManager adminManager;
	
	CineplexGroup getCineplexGroup()
	{
		return cineplexGroup;
	}
	LoginManager getLoginManager ()
	{
		return loginManager;
	}
	ReservationManager getReservationManager ()
	{
		return reservationManager;
	}
	PriceManager getPriceManager ()
	{
		return priceManager;
	}
	MovieManager getMovieManager ()
	{
		return movieManager;
	}
	AdminManager getAdminManager ()
	{
		return adminManager;
	}
	
	public void loadAllFiles ()
	{
		System.out.println("----------------");
		cineplexGroup = (CineplexGroup)deserializeObject("cineplexGroup.ser");
		if (cineplexGroup==null)
		{
			cineplexGroup = CineplexGroup.getInstance();
		}
		
		loginManager = (LoginManager)deserializeObject("loginManager.ser");
		if (loginManager==null)
		{
			loginManager = LoginManager.getInstance();
		}
		
		reservationManager = (ReservationManager)deserializeObject("reservationManager.ser");
		if (reservationManager==null)
		{
			reservationManager = ReservationManager.getInstance();
		}
		
		priceManager = (PriceManager)deserializeObject("priceManager.ser");
		if (priceManager==null)
		{
			priceManager = PriceManager.getInstance();
		}
		
		movieManager = (MovieManager)deserializeObject("movieManager.ser");
		if (movieManager==null)
		{
			movieManager = MovieManager.getInstance();
		}
		
		adminManager = (AdminManager)deserializeObject("adminManager.ser");
		if (adminManager==null)
		{
			adminManager = AdminManager.getInstance();
		}
		System.out.println("----------------");
	}
	
	public void saveAllFiles ()
	{
		System.out.println("----------------");
		serializeObject(cineplexGroup, "cineplexGroup.ser");
		serializeObject(loginManager, "loginManager.ser");
		serializeObject(reservationManager, "reservationManager.ser");
		serializeObject(priceManager, "priceManager.ser");
		serializeObject(movieManager, "movieManager.ser");
		serializeObject(adminManager, "adminManager.ser");
		System.out.println("----------------");
//		serializeObject(cineplexGroup, "cineplexGroup.ser");
	}
	
	
	public void serializeObject(Object objectToBeSaved, String filename) 
	{

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) 
		{
			oos.writeObject(objectToBeSaved);
			System.out.println("Successfully Saved: " + filename);
		} 
		catch (Exception ex) 
		{
			System.out.println("Failure to Save: " + filename);
		}
	}
	
	public Object deserializeObject(String filename)  // Needs to be static
	{
		Object returnObj = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) 
		{
			returnObj = (Object) ois.readObject();
			System.out.println("Successfully Loaded: " + filename);
			return returnObj;

		} 
		catch (Exception ex) 
		{
			System.out.println("Failure to Load: " + filename);
			return null; 
		}
	}
	
	public static void main(String[] args) 
	{
		FileManager fileManager = new FileManager();		
		fileManager.saveAllFiles();
		fileManager.loadAllFiles();
		System.out.println("We got here; Movie 1: " + fileManager.cineplexGroup.getMovieList().get(0).getTitle());

	}
	
	/*
	public void start() 
	{
		Scanner sc = new Scanner(System.in);
		
		 = ReservationManager.getInstance();
		//saveFile("CustomerData", reservationManager);
		loadFile("CustomerData", reservationManager);
		
		//load all existing data into the single instance of EmployeeManager
		AdminManager adminManager = AdminManager.getInstance();
		//saveFile("EmployeeData", adminManager);
		loadFile("EmployeeData", adminManager);
		//load all existing data into the single instance of CineplexGroup
		
		cineplexGroup = loadFile("CinemaData");
		
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
	public void end() {
		ReservationManager reservationManager = ReservationManager.getInstance();
		saveFile("CustomerData", reservationManager);
		AdminManager adminManager = AdminManager.getInstance();
		saveFile("EmployeeData", adminManager);
		CineplexGroup cineplexGroup = CineplexGroup.getInstance(); 
		saveFile("CinemaData", cineplexGroup);
		
	}
	
	public void saveFile(String s, Manager m){
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
	
	public void loadFile(String s, Manager m){
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
	
	public void saveFile(String s, CineplexGroup cplex_grp){
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
	
	public CineplexGroup loadFile(String s)
	{
	    try{// If this doesn't work throw an exception
	        FileInputStream fileIn = new FileInputStream(s+".ser");// Read serial file.
	        ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
	        CineplexGroup cplex_grp = (CineplexGroup)in.readObject();// allocate it to the object file already instantiated.
	        in.close();//closes the input stream.
	        fileIn.close();//closes the file data stream.
	        System.out.println("Successful load");
	        System.out.println("Movie 1: " + cplex_grp.getMovieList().get(0).getTitle());
	        return cplex_grp;
	    }
	    catch(IOException i) {//exception stuff
	        i.printStackTrace();
	        return (CineplexGroup.getInstance());
	    }
		catch(ClassNotFoundException c) {//more exception stuff
	        System.out.println("Error");
	        c.printStackTrace();
	        return (CineplexGroup.getInstance());
    	}
	}
	*/
}


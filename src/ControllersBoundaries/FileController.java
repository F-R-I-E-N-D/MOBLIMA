package ControllersBoundaries;

import java.io.*;

import CineplexClasses.CineplexController;

public class FileController
{
	public CineplexController loadCineplexGroup()
	{
		CineplexController returnCineplexGroup = (CineplexController)deserializeObject("cineplexGroupEmily.ser");
		if (returnCineplexGroup==null)
		{
			returnCineplexGroup = new CineplexController();
		}
		return returnCineplexGroup;
	}
	public LoginController loadLoginManager ()
	{
		LoginController returnLoginManager =  (LoginController)deserializeObject("loginManager.ser");
		if (returnLoginManager==null)
		{
			returnLoginManager = new LoginController();
		}
		return returnLoginManager;
	}
	public PriceController loadPriceManager ()
	{
		PriceController returnPriceManager = (PriceController)deserializeObject("priceManager.ser");
		if (returnPriceManager==null)
		{
			returnPriceManager = new PriceController();
		}
		return returnPriceManager;
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
			System.out.println();
			ex.printStackTrace();
			System.out.println("Failure to Save: " + filename);
		}
	}
	
	public Object deserializeObject(String filename) 
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
			System.out.println();
			ex.printStackTrace();
			System.out.println("Failure to Load: " + filename);
			return null; 
		}
	}
	
}

/*
import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;

public class FileManager
{		
	private CineplexGroup cineplexGroup;
	private LoginManager loginManager; 
//	private ReservationReviewManager reservationReviewManager;
	private PriceManager priceManager;
	private AdminManager adminManager;
	
	public CineplexGroup getCineplexGroup()
	{
		return cineplexGroup;
	}
	public LoginManager getLoginManager ()
	{
		return loginManager;
	}
//	public ReservationReviewManager getreservationReviewManager ()
//	{
//		return reservationReviewManager;
//	}
	public PriceManager getPriceManager ()
	{
		return priceManager;
	}
	public AdminManager getAdminManager ()
	{
		return adminManager;
	}
	
	public void loadAllFiles ()
	{
		System.out.println("----------------");
		cineplexGroup = (CineplexGroup)deserializeObject("cineplexGroup.ser");
		if (cineplexGroup==null)
		{
			resetAllFiles();
			return;
		}
		
		loginManager = (LoginManager)deserializeObject("loginManager.ser");
		if (loginManager==null)
		{
			resetAllFiles();
			return;
		}
		
//		reservationReviewManager = (ReservationReviewManager)deserializeObject("reservationReviewManager.ser");
//		if (reservationReviewManager==null)
//		{
//			resetAllFiles();
//			return;
//		}
		
		priceManager = (PriceManager)deserializeObject("priceManager.ser");
		if (priceManager==null)
		{
			resetAllFiles();
			return;
		}
		
		adminManager = (AdminManager)deserializeObject("adminManager.ser");
		if (adminManager==null)
		{
			resetAllFiles();
			return;
		}
		System.out.println("----------------");
		
//		System.out.println("LoadTest");
//		adminManager.addMovieToList("Title3", "fbhdbd", null, "djhghs", null, null);
//		adminManager.addMovieToList("Title4", "fbhdbd", null, "djhghs", null, null);
//		
//		System.out.println(cineplexGroup.getMovieList().size());
	}
	
	public void saveAllFiles ()
	{
//		System.out.println("SaveTest");
//		adminManager.addMovieToList("Title5", "fbhdbd", null, "djhghs", null, null);
//		adminManager.addMovieToList("Title6", "fbhdbd", null, "djhghs", null, null);
//		
//		System.out.println(cineplexGroup.getMovieList().size());
		
		System.out.println("----------------");
		serializeObject(cineplexGroup, "cineplexGroup.ser");
		serializeObject(loginManager, "loginManager.ser");
//		serializeObject(reservationReviewManager, "reservationReviewManager.ser");
		serializeObject(priceManager, "priceManager.ser");
		serializeObject(adminManager, "adminManager.ser");
		System.out.println("----------------");
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
			ex.printStackTrace();
			System.out.println("Failure to Save: " + filename);
		}
	}
	
	public Object deserializeObject(String filename) 
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
			ex.printStackTrace();
			System.out.println("Failure to Load: " + filename);
			return null; 
		}
	}
	
	public void resetAllFiles()
	{
		cineplexGroup = new CineplexGroup ();
		loginManager = new LoginManager();
//		reservationReviewManager = new ReservationReviewManager(cineplexGroup);
		priceManager = new PriceManager();
		adminManager = new AdminManager(cineplexGroup);
		saveAllFiles();
	}
}
*/


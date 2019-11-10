package Experiments;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import CineplexClasses.CineplexGroup;
import CineplexClasses.Movie;

public class ExperimentSaveObjects
{
	public static void main(String[] args) 
	{
		ExperimentSaveObjects exptclass = new ExperimentSaveObjects();
		CineplexGroup cineplexGroup = new CineplexGroup ();
//		CineplexGroup cineplexGroup = (CineplexGroup)exptclass.deserializeObject("cineplexGroup.ser");
		
		String[] cast = {"sdsa", "sfafsfa"};
		cineplexGroup.addMovieToList("title1", "dfdfasf", cast, "adi", null, null);
		String[] cast2 = {"sdsa", "sfafsfa"};
		cineplexGroup.addMovieToList("title2", "dfdfasf", cast2, "adi", null, null);
		
		exptclass.serializeObject(cineplexGroup, "cineplexGroup.ser");
		
//		cineplexGroup = (CineplexGroup)exptclass.deserializeObject("cineplexGroup.ser");
		for (Movie m: cineplexGroup.getMovieList())
		{
			System.out.println("Movie title: "+ m.getTitle());
		}
	}
	
	public void serializeObject(Object objectToBeSaved, String filename) 
	{

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) 
		{
			oos.writeObject(objectToBeSaved);
			System.out.println("Done");
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public Object deserializeObject(String filename)  // Needs to be static
	{

		Object returnObj = null;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {

			returnObj = (Object) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnObj;

	}
	
	/*
	public static void main(String[] args) 
	{
		ExperimentSaveObjects exptclass = new ExperimentSaveObjects();
		
		ClassB objectToBeSaved = new ClassB (1,2,3);
		
		String filename = "saved1.ser"; 
		
		exptclass.serializeObject(objectToBeSaved, filename);
		
		ClassB readObj = exptclass.deserializeObject(filename);
		System.out.println(readObj.getA().getA() + "," + readObj.getA().getB() + "," + readObj.getN());
		
	}
	
	public void serializeObject(ClassB objectToBeSaved, String filename) 
	{

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) 
		{
			oos.writeObject(objectToBeSaved);
			System.out.println("Done");
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public ClassB deserializeObject(String filename)  // Needs to be static
	{

		ClassB returnObj = null;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {

			returnObj = (ClassB) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnObj;

	}
	*/
	

}

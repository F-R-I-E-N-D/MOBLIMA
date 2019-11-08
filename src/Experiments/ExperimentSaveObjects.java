package Experiments;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExperimentSaveObjects
{

	public static void main(String[] args) 
	{
		ExperimentSaveObjects exptclass = new ExperimentSaveObjects();
		
		ClassB objectToBeSaved = new ClassB (1,2,3);
		
		String filename = "saved1.ser"; 
		
		exptclass.serializeObject(objectToBeSaved, filename);
		
		ClassB readObj = deserializeObject(filename);
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
	
	public static ClassB deserializeObject(String filename)  // Needs to be static
	{

		ClassB returnObj = null;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {

			returnObj = (ClassB) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnObj;

	}
	

}

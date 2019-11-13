package Experiments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExperiments {
	
	public static void main (String [] args) throws ParseException
	{	
		Date date =  new Date();
		date = new SimpleDateFormat("dd/MM/yyyy").parse("29/03/1999");  
		System.out.println(date);
	}
}

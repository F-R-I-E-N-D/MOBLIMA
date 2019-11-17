package CineplexClasses;
import java.io.*;
public class Cinema implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ClassType {GOLD, DELUXE, GEMINI, MAX, STANDARD}
	
	private ClassType classtype;
	private int HallID;
	private String name;
	private int numRows;
	private int numHandicappedRows;
	private int [] column; 
	private int [] columnHandicapped;
	
	public Cinema (int hallID, String name, int numRows, int numHandicappedRows, int [] column, int [] columnHandicapped, ClassType classtype)
	{
		this.setHallId(hallID);
		this.setNumRows(numRows);
		this.setColumn(column);
		this.setName(name);
		this.setClasstype(classtype);
		this.setColumnHandicapped(columnHandicapped);
		this.setNumHandicappedRows(numHandicappedRows);
	}
	
	public Cinema (int hall_id)
	{	
		this.setHallId(hall_id);
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int num_rows) {
		this.numRows = num_rows;
	}

	public int [] getColumn() {
		return column;
	}

	public void setColumn(int [] column) {
		this.column = column;
	}
	
	public int getHallId() {
		return HallID;
	}

	public void setHallId(int hall_id) {
		this.HallID = hall_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassType getClasstype() {
		return classtype;
	}

	public void setClasstype(ClassType classtype) {
		this.classtype = classtype;
	}

	public int [] getColumnHandicapped() {
		return columnHandicapped;
	}

	public void setColumnHandicapped(int [] columnHandicapped) {
		this.columnHandicapped = columnHandicapped;
	}

	public int getNumHandicappedRows() {
		return numHandicappedRows;
	}

	public void setNumHandicappedRows(int numHandicappedRows) {
		this.numHandicappedRows = numHandicappedRows;
	}
}

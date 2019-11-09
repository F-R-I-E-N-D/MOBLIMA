package CineplexClasses;

public class Cinema {
	
	public enum ClassType {GOLD, DELUXE, GEMINI, MAX, STANDARD}
	
	private ClassType classtype;
	private int hall_id;
	private String name;
	private int numRows;
	private int [] column; 
	
	public Cinema (int hall_id, String name, int num_rows, int [] column, ClassType classtype)
	{
		this.setHallId(hall_id);
		this.setNumRows(num_rows);
		this.setColumn(column);
		this.setName(name);
		this.setClasstype(classtype);
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
		return hall_id;
	}

	public void setHallId(int hall_id) {
		this.hall_id = hall_id;
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
}

package ControllersBoundaries;

import java.io.Serializable;
import java.util.ArrayList;

<<<<<<< HEAD
=======
import Users.EmployeeUser;

>>>>>>> 7c60bb6176aa7881b279d499e92c3df3cc1ec0ed
public class AdminManager extends Manager implements Serializable{
	//singleton class to facilitate access to the instance from any class

	
	private static final long serialVersionUID = -610865519713835384L;
	private static AdminManager adminManager = null;
	private ArrayList<EmployeeUser> employeeRecord = new ArrayList<EmployeeUser>();
	
	
	private AdminManager() {
	}
	
	public static AdminManager getInstance() {
        if (adminManager == null) {
            adminManager = new AdminManager();
        }
        return adminManager;
    }
	
<<<<<<< HEAD
	public EmployeeUser verifyExistingEmployee(String ID, String pw) {
=======
	public EmployeeUser verifyExistingEmployee(int ID, String pw) {
>>>>>>> 7c60bb6176aa7881b279d499e92c3df3cc1ec0ed
		int i;
		EmployeeUser current;
		System.out.println(employeeRecord);
		
		for(EmployeeUser user: employeeRecord) {
<<<<<<< HEAD
			if(user.loginID.equals(ID) && user.password.equals(pw)) {
=======
			if(user.getLoginID() == ID && user.getPassword().equals(pw)) {
>>>>>>> 7c60bb6176aa7881b279d499e92c3df3cc1ec0ed
				System.out.println("Logged in as Employee");
				return user;
			}
		}
		System.out.println("Incorrect login details.");
		return null;
	}
	
	public void addNewEmployee(EmployeeUser employeeUser) {
		employeeRecord.add(employeeUser);
	}
	
}


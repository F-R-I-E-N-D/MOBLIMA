package ControllersBoundaries;

import java.io.Serializable;
import java.util.ArrayList;

<<<<<<< HEAD
=======
import Users.CustomerUser;

>>>>>>> 7c60bb6176aa7881b279d499e92c3df3cc1ec0ed
public class ReservationManager extends Manager implements Serializable{
	//singleton class to facilitate access to the instance from any class


	private static final long serialVersionUID = 6007327308684037885L;
	private static ReservationManager reservationManager = null;
	private ArrayList<CustomerUser> customerRecord = new ArrayList<CustomerUser>();
	
	private ReservationManager() {
	}
	
	public static ReservationManager getInstance() {
        if (reservationManager == null) {
            reservationManager = new ReservationManager();
        }
        return reservationManager;
    }
	
<<<<<<< HEAD
	public CustomerUser verifyExistingCustomer(String ID, String pw) {
=======
	public CustomerUser verifyExistingCustomer(int ID, String pw) {
>>>>>>> 7c60bb6176aa7881b279d499e92c3df3cc1ec0ed
		int i;
		CustomerUser current;
		System.out.println(customerRecord);
		
		for(CustomerUser user: customerRecord) {
<<<<<<< HEAD
			if(user.loginID.equals(ID) && user.password.equals(pw)) {
=======
			if(user.getLoginID() == ID && user.getPassword().equals(pw)) {
>>>>>>> 7c60bb6176aa7881b279d499e92c3df3cc1ec0ed
				System.out.println("Logged in as Customer");
				return user;
			}
		}
		System.out.println("Incorrect login details.");
		return null;
	}
	
	public void addNewCustomer(CustomerUser customerUser) {
		customerRecord.add(customerUser);
	}
}
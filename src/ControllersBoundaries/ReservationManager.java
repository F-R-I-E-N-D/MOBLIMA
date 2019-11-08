package ControllersBoundaries;

import java.io.Serializable;
import java.util.ArrayList;

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
	
	public CustomerUser verifyExistingCustomer(String ID, String pw) {
		int i;
		CustomerUser current;
		System.out.println(customerRecord);
		
		for(CustomerUser user: customerRecord) {
			if(user.loginID.equals(ID) && user.password.equals(pw)) {
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
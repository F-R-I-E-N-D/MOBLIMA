package ControllersBoundaries;

public class ReservationInterface extends UserInterface{

	private static ReservationInterface resInt = null;
	
	private ReservationInterface() {
	}
	
	public static ReservationInterface getInstance() {
        if (resInt == null) {
            resInt = new ReservationInterface();
        }
        return resInt;
    }

	@Override
	public void print() {
		//fill in options
		System.out.println("... add things for customer to do");
		scanInput();
		
	}

	@Override
	public void scanInput() {
		//fill in options

		int option;
		boolean invalid = true;
		while(invalid) {
			option = onlyInteger("Please choose an option from 1-3.");
			switch(option) {
				case 1:
					
					//System.out.println("Thank you for coming to our cinema, how may we help you?");
					invalid = false;
					break;
				case 2:
					
					//System.out.println("Welcome to the system for employees.");
					invalid = false;
					break;
				case 3:
					//System.out.println("Thank you for using our system!");
					invalid = false;
				default:
					System.out.println("Only options 1-3 are accepted, please try again.");
			}
		}
	}
}

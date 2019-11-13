package Users;

import java.io.Serializable;

public class CustomerUser extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum CustomerType {NORMAL, STUDENT, SENIOR_CITIZEN};
	
	private String username;
	private CustomerType customerType;
	
	public CustomerUser(String username, String password, String full_name, CustomerType customerType) 
	{
		super(password, full_name);
		this.setUsername(username);
		this.setCustomerType(customerType);
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public CustomerType getCustomerType() 
	{
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) 
	{
		this.customerType = customerType;
	}
	
	
}
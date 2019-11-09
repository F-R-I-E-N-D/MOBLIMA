package Users;

import java.io.Serializable;

public class CustomerUser extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int age;
	
	public CustomerUser(int loginID, String password) {
		super(loginID, password);
	}
	
	//for movie purchase verification
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
}
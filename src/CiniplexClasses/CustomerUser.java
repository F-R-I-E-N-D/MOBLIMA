package movie;

public class CustomerUser extends User {
	
	private int age;
	
	public CustomerUser(String loginID, String password) {
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
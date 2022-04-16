package Java_FinalProject;

public class Account implements AccountInterface{
	private String id;
	private String authority; 
	public Account(String id, String authority) {
		this.id = id;
		this.authority = authority;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public String getAuthority() {
		return authority;
	}
	
}//end class

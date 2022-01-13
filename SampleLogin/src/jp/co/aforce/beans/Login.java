package jp.co.aforce.beans;

public class Login implements java.io.Serializable{
	private String loginId;
	private String name;
	private String password;
	
	public String getLoginId() {
		return loginId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setLoginId(String loginId) {
		this.loginId=loginId;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}

}

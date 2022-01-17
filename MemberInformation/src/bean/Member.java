package bean;

public class Member implements java.io.Serializable {
	
	private String member_no;
	private String name;
	private int age;
	private int birth_year;
	private int birth_month;
	private int birth_day;
	
	public String getMember_no() {
		return member_no;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getBirth_year() {
		return birth_year;
	}
	
	public int getBirth_month() {
		return birth_month;
	}
	
	public int getBirth_day() {
		return birth_day;
	}
	
	public void setMember_no(String member_no) {
		this.member_no=member_no;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public void setBirth_year(int birth_year) {
		this.birth_year=birth_year;
	}
	
	public void setBirth_month(int birth_month) {
		this.birth_month=birth_month;
    }
	
	public void setBirth_day(int birth_day) {
		this.birth_day=birth_day;
	}

}

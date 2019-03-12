package autoSubject;
/**
 * 用户类
 * @author HWJ
 *
 */
public class User {
	private String name="";
	private String password="";
	private String grade="";
	User(){
		this.name="";this.password="";this.grade="";
	}
	User(String name,String password){
		this.name=name;this.password=password;
	}
	User(String name,String password,String grade){
		this.name=name;this.password=password;this.grade=grade;
	}
	public void setAll(String name,String password,String grade){
		this.name=name;this.password=password;this.grade=grade;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getGrade() {
		return grade;
	}
}

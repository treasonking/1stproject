package loginVO;

import java.util.Date;

public class LoginVO {
	private String id;
	private String pw;
	private String name;
	private Date birth;
	//»ý¼ºÀÚ
	public LoginVO() {}
	public LoginVO(String id,String name,String pw,Date birth) {
		super();
		this.id=id;
		this.pw=pw;
		this.birth=birth;
		this.name=name;
	}
	//getter setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPwd(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
}

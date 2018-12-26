package test;

/**
 * 用户类
 * @author 王某人
 * @version 创建时间：2018年4月5日
 */
public class User {

	private int id;//用户唯一编号
	private String userName;//用户名
	private String userId;//用户账号
	private String userPassword;//用户密码
	private char sex;//用户性别
	private int age;//用户年龄
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}

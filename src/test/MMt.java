package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MMt {

	private Connection con;
	private String url = "jdbc:sqlserver://localhost:1433;Database=数据库A";
	private String user = "wo";// 管理员
	private String password = "123456";// 密码

	private PreparedStatement ps = null; // (这里也可以使用statement,视情况而定)

	private ResultSet rs = null;

	// 链接
	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	// 查询
	public ResultSet getSelect(String tab) {
		String sql = "select * from " + tab;
		con = getConnection();
		try {
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 
	 * @param tab 要插入的表
	 * @param name 要插入的名字
	 * @param id 要插入的编号
	 */
	public void insert(String tab,String name, int id) {
		String sql="insert into "+tab+" values(?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name); // 设置参数1，name 为小明
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 关闭
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 打印
	 * @param rs
	 */
	public void print(ResultSet rs) {
		String name = null;
		int id = 0;
		try {
			while (rs.next()) {
				name = rs.getString("sname");
				id = rs.getInt("sid");
				System.out.println(name + "\t" + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	/**
	 * 
	 * @param tab 要修改的表
	 * @param key 要修改记录的关键字
	 * @param col	要修改的列
	 * @param value 修改后的值
	 */
	public void change(String tab,String key,int col,String value){
    	String sql = "update "+tab+" set sname=? where sname = '" + key+"'";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(col,value);//向名字字段填入数据
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 删除
	 * @param tab
	 * @param keyName
	 */
	public void delete(String tab,String keyName){
    	String sql = "delete from "+tab+" where sname=?";
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1,keyName);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	
	public static void main(String[] args) {
		MMt m = new MMt();
		m.getConnection();
		m.insert("stu2","ss", 23);
		m.change("stu2","wangweizhen",1,"wwz");
		m.delete("stu2","ss");
		m.print(m.getSelect("stu2"));

	}

}

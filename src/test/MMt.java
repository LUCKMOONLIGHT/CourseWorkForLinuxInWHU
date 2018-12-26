package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MMt {

	private Connection con;
	private String url = "jdbc:sqlserver://localhost:1433;Database=���ݿ�A";
	private String user = "wo";// ����Ա
	private String password = "123456";// ����

	private PreparedStatement ps = null; // (����Ҳ����ʹ��statement,���������)

	private ResultSet rs = null;

	// ����
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

	// ��ѯ
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
	 * @param tab Ҫ����ı�
	 * @param name Ҫ���������
	 * @param id Ҫ����ı��
	 */
	public void insert(String tab,String name, int id) {
		String sql="insert into "+tab+" values(?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name); // ���ò���1��name ΪС��
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �ر�
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
	 * ��ӡ
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
	 * @param tab Ҫ�޸ĵı�
	 * @param key Ҫ�޸ļ�¼�Ĺؼ���
	 * @param col	Ҫ�޸ĵ���
	 * @param value �޸ĺ��ֵ
	 */
	public void change(String tab,String key,int col,String value){
    	String sql = "update "+tab+" set sname=? where sname = '" + key+"'";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(col,value);//�������ֶ���������
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * ɾ��
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

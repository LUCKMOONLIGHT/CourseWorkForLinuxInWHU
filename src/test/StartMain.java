package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StartMain {

	private static Connection conn;
	
	
	
	public static void main(String[] args) {
		
		PreparedStatement ps=null;      //(����Ҳ����ʹ��statement,���������)
	    Connection ct=null;
	    ResultSet rs=null;
	    
		// TODO Auto-generated method stub
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String url="jdbc:sqlserver://localhost:1433;databaseName=test";
		String url2 = "jdbc:sqlserver://localhost:1433;Database=���ݿ�A";
		// String
		// url3="jdbc:sqlserver://localhost//SQLEXPRESS01;Database=master";
		// String url4="jdbc:sqlserver://localhost:1433;DatabaseName=���ݿ�A";
		String user = "wo";// sa��������Ա
		String password = "123456";// ����
		// 2.����
		try {
			conn = DriverManager.getConnection(url2, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//conn = new SelectQuery().getConnection();

		GetInsert();
		GetSelect();

		GetUpdate();
		GetSelect();

		GetDelete();
		GetSelect();
	}

	/*
	 * INSERT
	 */
	public static void GetInsert() {
		if (conn != null) {
			// INSERT
			System.out.println("-----------INSERT------------");
			int x = 1 + (int) (Math.random() * 5000);
			String insert_str = "INSERT INTO stu2 (sname,sid) VALUES ('name_" + x + "','pwd_" + x
					+ "',NEWID())";
			try {
				Statement insertstatement = conn.createStatement();
				int result = insertstatement.executeUpdate(insert_str);
				if (result > 0) {
					System.out.println("��ӳɹ�");
					System.out.println("-----------------------");
				} else {
					System.out.println("���ʧ��");
					System.out.println("-----------------------");
				}
			} catch (Exception e) {
				System.out.println("���ʧ��");
				System.out.println("-----------------------");
				// TODO: handle exception
			}
		} else {
			System.out.println("�������ݿ�����");
			System.out.println("-----------------------");
		}
	}

	/*
	 * SELECT
	 */
	public static void GetSelect() {
		if (conn != null) {

			// SELECT
			System.out.println("-----------SELECT------------");
			String select_str = " SELECT * FROM tb_User ";
			try {
				PreparedStatement selectps = conn.prepareStatement(select_str);
				ResultSet rs = selectps.executeQuery();
				while (rs.next()) {
					String name = rs.getString("UserName");
					String pwd = rs.getString("UserPwd");
					String UserId = rs.getString("UserId");
					System.out.println(name + "\t" + pwd + "\t" + UserId);
				}
				System.out.println("��ѯ�ɹ�");
				System.out.println("-----------------------");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ѯʧ��");
				System.out.println("-----------------------");
			}
		} else {
			System.out.println("�������ݿ�����");
			System.out.println("-----------------------");
		}
	}

	/*
	 * UPDATE
	 */
	public static void GetUpdate() {
		if (conn != null) {
			// UPDATE
			System.out.println("-----------INSERT------------");
			String update_str = "UPDATE tb_User SET UserPwd=UserPwd+'xxxxxxxx' WHERE UserId='fa562573-218a-4205-b67d-ebdfac3f8329'";
			try {
				Statement updatestatement = conn.createStatement();
				int result = updatestatement.executeUpdate(update_str);
				if (result > 0) {
					System.out.println("�޸ĳɹ���");
					System.out.println("-----------------------");
				} else {
					System.out.println("�޸�ʧ��");
					System.out.println("-----------------------");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�޸�ʧ��");
				System.out.println("-----------------------");
			}
		} else {
			System.out.println("�������ݿ�����");
			System.out.println("-----------------------");
		}
	}

	/*
	 * DELETE
	 */
	public static void GetDelete() {
		if (conn != null) {
			// DELETE
			System.out.println("-----------DELETE------------");
			String delete_str = "DELETE tb_User WHERE UserId!='fa562573-218a-4205-b67d-ebdfac3f8329'";
			try {
				Statement deletestatement = conn.createStatement();
				int result = deletestatement.executeUpdate(delete_str);
				if (result > 0) {
					System.out.println("ɾ���ɹ���");
					System.out.println("-----------------------");
				} else {
					System.out.println("ɾ��ʧ��");
					System.out.println("-----------------------");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ɾ��ʧ��");
				System.out.println("-----------------------");
			}
		} else {
			System.out.println("�������ݿ�����");
			System.out.println("-----------------------");
		}
	}

}

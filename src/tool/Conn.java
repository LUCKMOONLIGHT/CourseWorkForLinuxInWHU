package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conn {

	private Connection con;
	private String url = "jdbc:sqlserver://localhost:1433;Database=数据库A";
	private String user = "wo";// 管理员
	private String password = "123456";// 密码
	private ArrayList[] viewList;//景点列表
	private ArrayList[] userList;//用户列表
	private ArrayList[] userScoreList;//用户-景点评分列表
	private ArrayList[] userLikeViewList;//用户喜爱列表
	private ArrayList[] viewScoreList;//景点-用户评分列表
	
	
	private int viewCount=0;//景点数
	private int viewScoreCount=0;//景点评分数
	private int viewScoreCount_user=0;//景点评分中的用户个数
	private int userCount=0;//用户数 
	private int userScroeCount=0;//用户评分数
	private int userScroeCount_view=0;//用户评分中的景点个数
	private PreparedStatement ps = null; // (这里也可以使用statement,视情况而定)
	private ResultSet rs = null;
	ResultSetMetaData data ;
	




	public Conn(String tab,int row){
		
		this.getConnection();
		this.getSelect(tab);
		getViewList(row,this);//景点表
		
		this.getSelect("myUser");//用户表
		getUserList(row,this);
		
		this.getSelect("userScore");//用户-景点评分表
		getUserScoreList(row,this);
		//printUserScoreList();
		
		this.getSelect("userLikeView");//用户喜好表
		getUserLikeViewList(row,this);//20个用户容量
		
		this.getSelect("viewScores");//景点-用户评分表
		getViewScoreList(row,this);
		
		//printViewScoreList();
		
	}
	
	
	// 链接
	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 查询
	public ResultSet getSelect(String tab) {
		String sql = "select * from " + tab;
		try {
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
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
			ps.setString(1, name); 
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
	}

	/**
	 * 打印
	 * @param rs
	 */
	public void print(ResultSet rs) {
		String viewName = null;
		int viewId = 0;
		String viewTitle = null;
		float viewScore;
		float viewPrice;
		String viewReson = null;
		String viewTime = null;
		String viewKind = null;
		String viewDetial = null;
		
		try {
			System.out.println("景点名\t景点编号\t景点标题\t景点评分\t景点价格\t景点理由\t景点时间\t景点种类\t景点详情");
			while (rs.next()) {
				viewName = rs.getString("viewName");
				viewId = rs.getInt("viewId");
				viewTitle = rs.getString("viewTitle");
				viewScore = rs.getFloat("viewScore");
				viewPrice = rs.getFloat("viewprice");
				viewReson = rs.getString("viewReson");
				viewTime = rs.getString("viewTime");
				viewKind = rs.getString("viewKind");
				viewDetial = rs.getString("viewDetail");
				System.out.println(viewName+"\t"+viewId+"\t"+viewTitle+"\t"+
						viewScore+"\t"+viewPrice+"\t"+viewReson+"\t"+viewTime+"\t"+
						viewKind+"\t"+viewDetial);
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
	
	@SuppressWarnings("unchecked")
	private ArrayList[] getViewList(int row,Conn c){
		
		viewList = new ArrayList[row]; 
		rs=c.getSelect("viewS");
		String viewName = null;
		int viewId = 0;
		String viewTitle = null;
		float viewScore;
		float viewPrice;
		String viewReson = null;
		String viewTime = null;
		String viewKind = null;
		String viewDetial = null;
		
		try {
			//System.out.println("景点名\t景点编号\t景点标题\t景点评分\t景点价格\t景点理由\t景点时间\t景点种类\t景点详情");
			int i=0;
			while (rs.next()) {
				viewName = rs.getString("viewName");
				viewCount++;
				viewList[i]= new ArrayList<>();
				viewList[i].add(viewName);
				viewId = rs.getInt("viewId");
				viewList[i].add(viewId);
				viewTitle = rs.getString("viewTitle");
				viewList[i].add(viewTitle);
				viewScore = rs.getFloat("viewScore");
				viewList[i].add(viewScore);
				viewPrice = rs.getFloat("viewprice");
				viewList[i].add(viewPrice);
				viewReson = rs.getString("viewReson");
				viewList[i].add(viewReson);
				viewTime = rs.getString("viewTime");
				viewList[i].add(viewTime);
				viewKind = rs.getString("viewKind");
				viewList[i].add(viewKind);
				viewDetial = rs.getString("viewDetail");
				viewList[i].add(viewDetial);
				viewId = rs.getInt("viewNum");
				viewList[i].add(viewId);
				viewId = rs.getInt("viewLike");
				viewList[i].add(viewId);
				i++;
//				System.out.println(viewName+"\t"+viewId+"\t"+viewTitle+"\t"+
//						viewScore+"\t"+viewPrice+"\t"+viewReson+"\t"+viewTime+"\t"+
//						viewKind+"\t"+viewDetial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return viewList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList[] getUserList(int row,Conn c){
		
		userList = new ArrayList[row]; 
		//ResultSet rs=c.getSelect("user");
		String userName = null;
		int userId = 0;
		String userSex = null;
		int userAge;
		String userNumID = null;
		String userPassword = null;
		String userLove = null;
		
		try {
			int i=0;
			while (rs.next()) {
				userName = rs.getString("userName");
				userCount++;
				userList[i]= new ArrayList<>();
				userList[i].add(userName);
				userId = rs.getInt("userId");
				userList[i].add(userId);
				userSex = rs.getString("userSex");
				userList[i].add(userSex);
				userAge = rs.getInt("userAge");
				userList[i].add(userAge);
				userNumID = rs.getString("userNumID");
				userList[i].add(userNumID);
				userPassword = rs.getString("userPassword");
				userList[i].add(userPassword);
				userLove = rs.getString("userLove");
				userList[i].add(userLove);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList[] getUserScoreList(int row,Conn c){
		
		userScoreList = new ArrayList[row]; 
		String userName = null;
		int score = 0;
		try {
			int i=0;
			int j=0;
			data = rs.getMetaData();
			userScroeCount_view = data.getColumnCount()-1;
			//System.out.println("景点数"+userScroeCount_view);
			while (rs.next()) {
				userName = rs.getString("userName");
				userScroeCount++;
				userScoreList[i]= new ArrayList<>();
				userScoreList[i].add(userName);
				for (j = 2; j <= data.getColumnCount(); j++) {
					score = rs.getInt(data.getColumnName(j));
					userScoreList[i].add(score);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return userScoreList;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList[] getViewScoreList(int row,Conn c){
		
		viewScoreList = new ArrayList[row]; 
		String viewName = null;
		int score = 0;
		try {
			int i = 0;
			int j = 0;
			data = rs.getMetaData();
			//System.out.println("例数："+data.getColumnCount());
			viewScoreCount_user=data.getColumnCount()-1;
			//System.out.println(viewScoreCount_user);
			while (rs.next()) {
				viewName = rs.getString("viewName");
				viewScoreCount++;
				viewScoreList[i]= new ArrayList<>();
				viewScoreList[i].add(viewName);
				for (j = 2; j <= data.getColumnCount(); j++) {
					score = rs.getInt(data.getColumnName(j));
					viewScoreList[i].add(score);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return viewScoreList;
	}
	
	
	private ArrayList[] getUserLikeViewList(int row,Conn c){
		
		userLikeViewList = new ArrayList[row]; 
		String userName = null;
		String userLike = null;
		try {
			int i=0;
			while (rs.next()) {
				userName = rs.getString("userName");
				//userCount++;
				userLikeViewList[i]= new ArrayList<>();
				userLikeViewList[i].add(userName);
				userLike = rs.getString("likeView");
				userLikeViewList[i].add(userLike);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return userLikeViewList;
	}
	
	
	public void printUserList(){
		for (int i = 0; i < userCount; i++) {
			for (int j = 0; j < userList[i].size(); j++) {
				System.out.println(userList[i].get(j));
			}
		}
	}
	
	
	public ArrayList[] getViewList(){
		return viewList;
	}
	
	public ArrayList[] getViewScoreList(){
		return viewScoreList;
	}
	
	public ArrayList[] getUserList(){
		return userList;
	}
	
	public ArrayList[] getUserLikeViewList(){
		return userLikeViewList;
	}
	
	public ArrayList[] getUserScoreList(){
		return userScoreList;
	}
	
	public int getViewCount() {
		return viewCount;
	}


	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public int getUserCount() {
		return userCount;
	}


	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
	public int getUserScroeCount() {
		return userScroeCount;
	}
	public int getViewScroeCount() {
		return viewScoreCount;
	}

	public void setUserScroeCount(int userScroeCount) {
		this.userScroeCount = userScroeCount;
	}
	
	public void printViewList(){
		for (int i = 0; i < viewCount; i++) {
			for (int j = 0; j < viewList[i].size(); j++) {
				System.out.println(viewList[i].get(j));
			}
		}
	}
	
	public void printUserScoreList(){
		for (int i = 0; i < userScroeCount; i++) {
			for (int j = 0; j < userScoreList[i].size(); j++) {
				System.out.print(userScoreList[i].get(j));
			}
			System.out.println();
		}
	}
	
	public void printUserLikeViewList(){
		for (int i = 0; i < userCount; i++) {
			for (int j = 0; j < userLikeViewList[i].size(); j++) {
				System.out.print(userLikeViewList[i].get(j));
			}
			System.out.println();
		}
	}
	
	public void printViewScoreList(){
		for (int i = 0; i < viewScoreCount; i++) {
			for (int j = 0; j < viewScoreList[i].size(); j++) {
				System.out.print(viewScoreList[i].get(j));
			}
			System.out.println();
		}
	}
	
	
	public int getViewScoreCount_user() {
		return viewScoreCount_user;
	}


	public void setViewScoreCount_user(int viewScoreCount_user) {
		this.viewScoreCount_user = viewScoreCount_user;
	}


	public int getUserScroeCount_view() {
		return userScroeCount_view;
	}


	public void setUserScroeCount_view(int userScroeCount_view) {
		this.userScroeCount_view = userScroeCount_view;
	}
}

package tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public class Data {

	private int num = 0; //优先级
	private String userName;//用户名
	private String userNumId;//账号
	private String userPassword;//密码
	private int viewCount=0;//景点数
	private int userCount=0;//用户数
	private int userScoreCount=0;//用户评分数 
	private int viewScoreCount=0;//景点评分数
	
	private int viewScoreCount_user=0;//景点评分中的用户个数
	private int userScroeCount_view=0;//用户评分中的景点个数
	private String testName;
	private String testName1;
	
	
	private Conn con;//数据库链接
	private ArrayList[] viewList;//景点列表
	private ArrayList[] userList;//用户列表
	private ArrayList[] userScoreList;//用户评分列表
	private ArrayList[] viewScoreList;//景点-用户评分列表
	private ArrayList[] userLikeViewList;//用户喜爱列表
	private ArrayList[] userSim;//用户相关系数
	private ArrayList[] viewSim;//景点相关系数

	private ArrayList recommendViewUser;//推荐景点列表
	private ArrayList recommendViewView;//推荐景点列表
	
	public String mainUserLikeView;
	public String userLikeView;
	
	


	public Data() {
		init();
		testName = "C";
		getUserSim(testName);
		
		testName1 = "2";
		getViewSim(testName1);
		//printRecommendViewUser();
		//printRecommendViewView();//核心
		
	}
	
	
	public void printRecommendViewUser(){
		for (int i = 0; i < recommendViewUser.size(); i++) {
			System.out.println(recommendViewUser.get(i)+"号");
		}
	}
	public void printRecommendViewView(){
		System.out.println("项目");
		for (int i = 0; i < recommendViewView.size(); i++) {
			System.out.println(recommendViewView.get(i)+"号");
		}
	}
	
	public void getViewSim(String view){
		computingViewSim(viewScoreList,view);
		sort(viewSim);
		searchSimView(view,num);
		
	}
	public void getUserSim(String user){
		computingSim(userScoreList,user);
		sort(userSim);
		//System.out.println(testName);
		searchSimUserLikeView(testName,num);
		//
		//System.out.println("1");
		while(recommendViewUser.size()==0){
			num++;
			searchSimUserLikeView(testName,num);
		}
		num = 0;
	}
	
	private void searchSimView(String view,int row){
		
		while(row<viewScoreCount-1){
			recommendViewView.add(viewSim[row].get(0));
			row++;
		}
		
	}
	
	
	/**
	 * 随机推荐
	 * @return
	 */
	public ArrayList getRandonRecommendView() {
		if(recommendViewUser.size()==0){
			num++;
			searchSimUserLikeView(testName,num);
			return recommendViewUser;
		}else{
			return recommendViewUser;
		}
	}
	
	
	
	//默认选择最相似的用户
	private void searchSimUserLikeView(String mainUser,int row){
		String name;
//		String mainUserLikeView;
//		String userLikeView;
		String num;
		String view;
		int i;
		int endIndex=0;
		int beginIndex = 0;
		name = ""+userSim[row].get(0);
		i=search(name);
		userLikeView = ""+userLikeViewList[i].get(1);
		//System.out.println(userLikeView);
		i=search(mainUser);
		mainUserLikeView = ""+userLikeViewList[i].get(1);
		//System.out.println(mainUserLikeView);
		
		ArrayList ViewList1= new ArrayList<String>();//相似用户喜欢景点表
		view=userLikeView;
		endIndex = view.indexOf('、');
		beginIndex = 0;
		for (endIndex = view.indexOf('、'); endIndex < view.length();endIndex = view.indexOf('、') ) {
			if(endIndex!=-1){
				num=view.substring(beginIndex, endIndex);
				view=view.substring(endIndex+1, view.length());
				//System.out.println(num);
				ViewList1.add(num);
			}else{
				num=view;
				//System.out.println(num);
				ViewList1.add(num);
				break;
			}
			
		}
		ArrayList ViewList2= new ArrayList<String>();//主用户喜欢景点表
		view=mainUserLikeView;
		endIndex = view.indexOf('、');
		beginIndex = 0;
		for (endIndex = view.indexOf('、'); endIndex < view.length();endIndex = view.indexOf('、') ) {
			if(endIndex!=-1){
				num=view.substring(beginIndex, endIndex);
				view=view.substring(endIndex+1, view.length());
				//System.out.println(num);
				ViewList2.add(num);
			}else{
				num=view;
				//System.out.println(num);
				ViewList2.add(num);
				break;
			}
		}
		for (int j = 0; j < ViewList2.size(); j++) {
			for (int j2 = 0; j2 < ViewList1.size(); j2++) {
				if(ViewList2.get(j).equals(ViewList1.get(j2))){
					ViewList1.remove(j2);
				}
			}
		}
		recommendViewUser = ViewList1;
//		for (int j = 0; j < recommendViewUser.size(); j++) {
//			System.out.print(recommendViewUser.get(j)+"\t");
//		}
	}
	
	private int search(String name){
		for (int i = 0; i < userScoreCount; i++) {
			if(name.equals(""+userLikeViewList[i].get(0))){
				return i;
			}
		}
		return 0;
	}
	
	private void init(){
		
		con = new Conn("viewS",20);
		
		viewList = con.getViewList();//景点
		this.viewCount = con.getViewCount();
		
		userList = con.getUserList();//用户
		this.userCount = con.getUserCount();
		
		userScoreList =con.getUserScoreList();//用户评分
		this.userScoreCount = con.getUserScroeCount();
		
		userLikeViewList = con.getUserLikeViewList();//用户喜爱
		this.setUserLikeViewList();
		
		viewScoreList = con.getViewScoreList();
		this.viewScoreCount = con.getViewScroeCount();
		
		viewScoreCount_user = con.getViewScoreCount_user();
		userScroeCount_view = con.getUserScroeCount_view();
		
		
		setUserInfo(0);//设置第一个用户显示信息
		
		recommendViewUser = new ArrayList<>();
		recommendViewView = new ArrayList<String>();
	}
	
	private void computingViewSim(ArrayList[] viewScoreList,String viewName){
		Map<String, Map<String, Integer>> userPerfMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> pref = new HashMap<String, Integer>();
		for (int i = 0; i < viewScoreCount; i++) {
			Map<String, Integer> pref1 = new HashMap<String, Integer>();
			for (int j = 1; j <= viewScoreCount_user; j++) {
				pref1.put("user"+j, (Integer) viewScoreList[i].get(j));
			}
			if(viewName.equals(""+viewScoreList[i].get(0))){
				pref = pref1;
			}
			userPerfMap.put(""+viewScoreList[i].get(0), pref1);
		}

        Map<String, Double> simUserSimMap = new HashMap<String, Double>();
        //System.out.println("皮尔逊相关系数:");

        viewSim = new ArrayList[viewScoreCount];
        int index = 0;
        for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {
            String name = userPerfEn.getKey();
            if (!viewName.equals(name)) {
                double sim = getUserSimilar(pref, userPerfEn.getValue());
               // System.out.println(viewName+"与" + name + "之间的相关系数:" + sim);
                viewSim[index]= new ArrayList<>();
                viewSim[index].add(name);
                viewSim[index].add(sim);
                index++;
            }
        }
        viewSim[index]= new ArrayList<>();
        viewSim[index].add(viewName);
        viewSim[index].add(0.0001);
//        System.out.println(viewName);
//        System.out.println("index="+index);
	}
	
	
	
	public void setUserInfo(int i){
		this.setUserNumId(""+userList[i].get(4));
		this.setUserName(""+userList[i].get(0));
		this.setUserPassword(""+userList[i].get(5));
		//print();
	}
	
	
	
	void print(){
		System.out.println(userName+","+userPassword);
	}
	
	public ArrayList[] getList() {
		return viewList;
	}
	
	public void setList(ArrayList[] list) {
		this.viewList = list;
	}
	
	public ArrayList[] getUserLikeViewList() {
		return userLikeViewList;
	}

	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	
	/**
	 * 计算用户相似度
	 * @param userScoreList
	 * @param userN
	 */
	private void computingSim(ArrayList[] userScoreList,String userN){
		Map<String, Map<String, Integer>> userPerfMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> pref = new HashMap<String, Integer>();
		for (int i = 0; i < userScoreCount; i++) {
			Map<String, Integer> pref1 = new HashMap<String, Integer>();
			for (int j = 1; j <= userScroeCount_view; j++) {
				pref1.put("view"+j, (Integer) userScoreList[i].get(j));
			}
			if(userN.equals(""+userScoreList[i].get(0))){
				pref = pref1;
			}
			userPerfMap.put(""+userScoreList[i].get(0), pref1);
		}

        Map<String, Double> simUserSimMap = new HashMap<String, Double>();
        //System.out.println("皮尔逊相关系数:");

        userSim = new ArrayList[userScoreCount-1];
        int index = 0;
        for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {
            String name = userPerfEn.getKey();
            if (!userN.equals(name)) {
                double sim = getUserSimilar(pref, userPerfEn.getValue());
                //System.out.println(userN+"与" + name + "之间的相关系数:" + sim);
                userSim[index]= new ArrayList<>();
                userSim[index].add(name);
                userSim[index].add(sim);
                
                index++;
            }
        }
	}
	
	
	public String getTestName1() {
		return testName1;
	}


	public void setTestName1(String testName1) {
		this.testName1 = testName1;
	}
	/**
	 * 排序
	 * @param userSim
	 */
	public void sort(ArrayList[] userSim){
		String s;
		double a;
		double b;
		for (int i = 0; i < userSim.length-1; i++) {
			for (int j = 0; j < userSim.length-1-i; j++) {
				s=""+userSim[j].get(1);
				a=Double.parseDouble( s );
				s=""+userSim[j+1].get(1);
				b=Double.parseDouble( s );
				if(a<b){
					s = ""+userSim[j].get(0);
					userSim[j].set(0, userSim[j+1].get(0));
					userSim[j].set(1, b);
					userSim[j+1].set(0, s);
					userSim[j+1].set(1, a);
				}
			}
		}
	}
	
	public double getUserSimilar(Map<String, Integer> pm1, Map<String, Integer> pm2) {
        int n = 0;// 数量n
        int sxy = 0;// Σxy=x1*y1+x2*y2+....xn*yn
        int sx = 0;// Σx=x1+x2+....xn
        int sy = 0;// Σy=y1+y2+...yn
        int sx2 = 0;// Σx2=(x1)2+(x2)2+....(xn)2
        int sy2 = 0;// Σy2=(y1)2+(y2)2+....(yn)2
        for (Entry<String, Integer> pme : pm1.entrySet()) {
            String key = pme.getKey();
            Integer x = pme.getValue();
            Integer y = pm2.get(key);
            if (x != null && y != null) {
                n++;
                sxy += x * y;
                sx += x;
                sy += y;
                sx2 += Math.pow(x, 2);
                sy2 += Math.pow(y, 2);
            }
        }
        // p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
        double sd = sxy - sx * sy / n;
        double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
        double sim = Math.abs(sm == 0 ? 1 : sd / sm);
        if(sim>1){
        	sim = sim-0.03;
        }
        return sim;
    }
	
	private void setUserLikeViewList() {
		
		
		this.userLikeViewList = userLikeViewList;
	}
	
	public ArrayList[] getUserSim() {
		return userSim;
	}


	public void setUserSim(ArrayList[] userSim) {
		this.userSim = userSim;
	}


	public ArrayList[] getViewSim() {
		return viewSim;
	}


	public void setViewSim(ArrayList[] viewSim) {
		this.viewSim = viewSim;
	}
	
	public ArrayList[] getUserList() {
		return userList;
	}
	
	public void setUserList(ArrayList[] list) {
		this.userList = list;
	}
	
	public ArrayList[] getUserScoreList() {
		return userScoreList;
	}
	
	public void setUserScoreList(ArrayList[] list) {
		this.userScoreList = list;
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
	
	public int getUserScoreCount() {
		return userScoreCount;
	}
	
	public int getViewScoreCount() {
		return viewScoreCount;
	}


	public void setViewScoreCount(int viewScoreCount) {
		this.viewScoreCount = viewScoreCount;
	}
	public void setUserScoreCount(int userCount) {
		this.userScoreCount = userCount;
	}
	
	public ArrayList getRecommendViewUser() {
		return recommendViewUser;
	}


	public void setRecommendViewUser(ArrayList recommendViewUser) {
		this.recommendViewUser = recommendViewUser;
	}


	public ArrayList getRecommendViewView() {
		return recommendViewView;
	}


	public void setRecommendViewView(ArrayList recommendViewView) {
		this.recommendViewView = recommendViewView;
	}
	
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNumId() {
		return userNumId;
	}
	public void setUserNumId(String userId) {
		this.userNumId = userId;
	}
	
}

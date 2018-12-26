package tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public class Data {

	private int num = 0; //���ȼ�
	private String userName;//�û���
	private String userNumId;//�˺�
	private String userPassword;//����
	private int viewCount=0;//������
	private int userCount=0;//�û���
	private int userScoreCount=0;//�û������� 
	private int viewScoreCount=0;//����������
	
	private int viewScoreCount_user=0;//���������е��û�����
	private int userScroeCount_view=0;//�û������еľ������
	private String testName;
	private String testName1;
	
	
	private Conn con;//���ݿ�����
	private ArrayList[] viewList;//�����б�
	private ArrayList[] userList;//�û��б�
	private ArrayList[] userScoreList;//�û������б�
	private ArrayList[] viewScoreList;//����-�û������б�
	private ArrayList[] userLikeViewList;//�û�ϲ���б�
	private ArrayList[] userSim;//�û����ϵ��
	private ArrayList[] viewSim;//�������ϵ��

	private ArrayList recommendViewUser;//�Ƽ������б�
	private ArrayList recommendViewView;//�Ƽ������б�
	
	public String mainUserLikeView;
	public String userLikeView;
	
	


	public Data() {
		init();
		testName = "C";
		getUserSim(testName);
		
		testName1 = "2";
		getViewSim(testName1);
		//printRecommendViewUser();
		//printRecommendViewView();//����
		
	}
	
	
	public void printRecommendViewUser(){
		for (int i = 0; i < recommendViewUser.size(); i++) {
			System.out.println(recommendViewUser.get(i)+"��");
		}
	}
	public void printRecommendViewView(){
		System.out.println("��Ŀ");
		for (int i = 0; i < recommendViewView.size(); i++) {
			System.out.println(recommendViewView.get(i)+"��");
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
	 * ����Ƽ�
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
	
	
	
	//Ĭ��ѡ�������Ƶ��û�
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
		
		ArrayList ViewList1= new ArrayList<String>();//�����û�ϲ�������
		view=userLikeView;
		endIndex = view.indexOf('��');
		beginIndex = 0;
		for (endIndex = view.indexOf('��'); endIndex < view.length();endIndex = view.indexOf('��') ) {
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
		ArrayList ViewList2= new ArrayList<String>();//���û�ϲ�������
		view=mainUserLikeView;
		endIndex = view.indexOf('��');
		beginIndex = 0;
		for (endIndex = view.indexOf('��'); endIndex < view.length();endIndex = view.indexOf('��') ) {
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
		
		viewList = con.getViewList();//����
		this.viewCount = con.getViewCount();
		
		userList = con.getUserList();//�û�
		this.userCount = con.getUserCount();
		
		userScoreList =con.getUserScoreList();//�û�����
		this.userScoreCount = con.getUserScroeCount();
		
		userLikeViewList = con.getUserLikeViewList();//�û�ϲ��
		this.setUserLikeViewList();
		
		viewScoreList = con.getViewScoreList();
		this.viewScoreCount = con.getViewScroeCount();
		
		viewScoreCount_user = con.getViewScoreCount_user();
		userScroeCount_view = con.getUserScroeCount_view();
		
		
		setUserInfo(0);//���õ�һ���û���ʾ��Ϣ
		
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
        //System.out.println("Ƥ��ѷ���ϵ��:");

        viewSim = new ArrayList[viewScoreCount];
        int index = 0;
        for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {
            String name = userPerfEn.getKey();
            if (!viewName.equals(name)) {
                double sim = getUserSimilar(pref, userPerfEn.getValue());
               // System.out.println(viewName+"��" + name + "֮������ϵ��:" + sim);
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
	 * �����û����ƶ�
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
        //System.out.println("Ƥ��ѷ���ϵ��:");

        userSim = new ArrayList[userScoreCount-1];
        int index = 0;
        for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {
            String name = userPerfEn.getKey();
            if (!userN.equals(name)) {
                double sim = getUserSimilar(pref, userPerfEn.getValue());
                //System.out.println(userN+"��" + name + "֮������ϵ��:" + sim);
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
	 * ����
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
        int n = 0;// ����n
        int sxy = 0;// ��xy=x1*y1+x2*y2+....xn*yn
        int sx = 0;// ��x=x1+x2+....xn
        int sy = 0;// ��y=y1+y2+...yn
        int sx2 = 0;// ��x2=(x1)2+(x2)2+....(xn)2
        int sy2 = 0;// ��y2=(y1)2+(y2)2+....(yn)2
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
        // p=(��xy-��x*��y/n)/Math.sqrt((��x2-(��x)2/n)(��y2-(��y)2/n));
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

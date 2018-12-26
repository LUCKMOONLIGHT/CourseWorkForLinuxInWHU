package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import tool.Data;
import tool.Tool;

//猜你喜欢
public class GuessLikePane extends JPanel implements ActionListener {
	
	
	
	
	private LikeJButton likeButton;//喜欢按钮
	private DisLikeJButton disLikeButton;//不喜欢按钮
	private JLabel labelImage;
	private JTextPane1 jTP;
	private JButton left,right;
	private JScrollPane jsp;
	Data data;
	private int[] viewList;
	private Icon[] vImage;//景点图片
	private int index;
	ShowMessagePane mjf1;
	ShowMessagePane mjf2;
	
	private ArrayList[] list;//景点列表
	private ArrayList recommendViewUser;//推荐景点列表、基于用户推荐
	private ArrayList recommendViewView;//推荐景点列表、基于景点推荐
	private ArrayList recommendRandonView;//推荐景点列表、随机
	
	public int viewCount=0;
	public int userCount=0;
	
	
	public GuessLikePane(Tool tool,Data data){
		
		
		this.setLayout(null);
		this.data = data;
		list=data.getList();
		this.viewCount = data.getViewCount();
		this.userCount = data.getUserCount();
		recommendViewUser = data.getRecommendViewUser();
		recommendViewView = data.getRecommendViewView();
		recommendRandonView = data.getRandonRecommendView();
		viewList = new int[viewCount];
		index = 0;
		this.init(tool);
		setViewList(2);//推荐方法选择，1、用户推荐。2、景点相似推荐
		this.add(likeButton);
		this.add(disLikeButton);
		this.add(left);
		this.add(right);
		this.add(labelImage);
		this.add(jsp);
		this.setBackground(Color.white);
		
//		for (int i = 0; i < recommendViewView.size(); i++) {
//			System.out.println(recommendViewView.get(i));
//		}
		
		
	}
	
	private void setViewList(int flag){
		if(flag == 1){//基于用户推荐
			for (int i = 0; i < viewList.length&& i< recommendViewUser.size(); i++) {
				viewList[i] = Integer.parseInt(""+recommendViewUser.get(i));
			}
		}else if(flag == 2){//基于景点推荐
			for (int i = 0; i < viewList.length && i< recommendViewView.size(); i++) {
				//System.out.println(recommendViewView.get(i));
				viewList[i] = Integer.parseInt(""+recommendViewView.get(i));
			}
		}else if(flag == 3){//随机推荐
			for (int i = 0; i < viewList.length && i< recommendRandonView.size(); i++) {
				viewList[i] = Integer.parseInt(""+recommendRandonView.get(i));
			}
		}
	}
	
	private void init(Tool tool){
		
		left = new JButton("左");
		left.setBounds(30, 30, 38, 350);
		left.setIcon(tool.leftImage);
		left.addActionListener(this);
		
		right = new JButton("右");
		right.setBounds(1000, 30, 38, 350);
		right.setIcon(tool.rightImage);
		right.addActionListener(this);
		
		likeButton = new LikeJButton();
		likeButton.setBounds(90, 390, 250, 50);
		
		disLikeButton = new DisLikeJButton(data);
		disLikeButton.setBounds(490, 390, 250, 50);
		
		
		labelImage = new JLabel();
		labelImage.setBounds(80, 20, 360, 360);
		labelImage.setBorder(BorderFactory.createTitledBorder("景点图片"));
		
		vImage = new ImageIcon[viewCount];
		for (int j = 0; j < vImage.length; j++) {
			vImage[j] = new ImageIcon(this.getClass().getClassLoader().getResource("viewImage/"+(j+1)+".jpg"));
		}
		jTP = new JTextPane1();
		reset(index);
		
		
		jsp = new JScrollPane(jTP);
		jsp.getVerticalScrollBar().setUnitIncrement(10);//设置滚动速度
		jsp.setBounds(440, 30, 550, 350);
		jsp.setBorder(BorderFactory.createEtchedBorder());
	}
	
	private void reset(int i){
		if(i==-1){
			i=0;
		}
		labelImage.setIcon(vImage[Integer.parseInt(""+(list[i].get(1)))-1]);
		jTP.reset("", true);
		jTP.setRed_UnderLine_24(""+list[i].get(2),true);//标题
		jTP.setBlack_Bold_20("\t景点名：",false);
		jTP.setBlue_Bold_20(""+list[i].get(0),true);//景点名
		jTP.setBlack_Bold_20("\t景点评分：",false);
		jTP.setRed_Bold_20(""+list[i].get(3)+"分",true);
		jTP.setBlack_Bold_20("\t门票：",false);
		jTP.setRed_Bold_26(""+list[i].get(4)+"元",true);
		jTP.setBlack_Bold_20("\t最佳季节：",false);
		jTP.setBlue_Bold_20(""+list[i].get(6),true);
		jTP.setBlack_Bold_20("\t类型：",false);
		jTP.setBlue_Bold_20(""+list[i].get(7),true);
		jTP.setGreen_Bold_20("\t推荐理由：",false);
		jTP.setBlue_Bold_20(""+list[i].get(5),true);
		jTP.setGreen_Bold_20("\t景点详情：",false);
		jTP.setBlack_Bold_20(""+list[i].get(8),true);
		likeButton.setText(""+list[i].get(9)+"人 喜欢");
		likeButton.counts=Integer.parseInt(""+list[i].get(9));
		likeButton.restButton(Integer.parseInt(""+list[i].get(10)));
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == left){
			if(index == 0 && mjf1 == null){
				mjf1 = new ShowMessagePane("我是有上界的。");
			}else if(index == 0 && mjf1 != null){
				mjf1.setVisible(true);
			}else{
				index =index-1;
				reset(viewList[index]-1);
				jTP.updateUI();
				disLikeButton.restButton();
				//likeButton.i = -1;
			}
		}else if(e.getSource() == right){
			if(index == viewCount-1 && mjf2 == null){
				mjf2 = new ShowMessagePane("我是有底线的。");
			}else if(index == viewCount-1 && mjf2 != null){
				mjf2.setVisible(true);
			}else{
				index++;
				reset(viewList[index]-1);
				jTP.updateUI();
				disLikeButton.restButton();
				//likeButton.i = -1;
			}
		}
		
	}
	
}

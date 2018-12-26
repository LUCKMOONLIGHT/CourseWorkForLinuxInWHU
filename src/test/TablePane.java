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
import javax.swing.JTextField;
import javax.swing.JTextPane;

import tool.Data;
import tool.Tool;

//����ϲ��
public class TablePane extends JPanel implements ActionListener {
	
	private JButton button1;//ϲ����ť
	private JButton button2;//ϲ����ť
	
	
	private DisLikeJButton disLikeButton;//��ϲ����ť
	private JLabel labelImage;
	private JTextField jtf;
	private JTextPane1 jTP;
	private JButton left,right;
	private JScrollPane jsp;
	Data data;
	private int[] viewList;
	//private Icon[] vImage;//����ͼƬ
	private int index;
	ShowMessagePane mjf1;
	ShowMessagePane mjf2;
	
	private int userScoreCount=0;//�û������� 
	private int viewScoreCount=0;//����������
	
	private ArrayList[] list;//�����б�
	private ArrayList recommendViewUser;//�Ƽ������б������û��Ƽ�
	private ArrayList recommendViewView;//�Ƽ������б����ھ����Ƽ�
	private ArrayList recommendRandonView;//�Ƽ������б����
	private ArrayList[] userSim;//�û����ϵ��
	private ArrayList[] viewSim;//�������ϵ��
	
	public int viewCount=0;
	public int userCount=0;
	
	
	public TablePane(Tool tool,Data data){
		
		
		this.setLayout(null);
		this.data = data;
		list=data.getList();
		this.viewCount = data.getViewCount();
		this.userCount = data.getUserCount();
		recommendViewUser = data.getRecommendViewUser();
		recommendViewView = data.getRecommendViewView();
		recommendRandonView = data.getRandonRecommendView();
		viewList = new int[viewCount];
		userScoreCount = data.getUserScoreCount();
		viewScoreCount = data.getViewScoreCount();
		userSim = data.getUserSim();
		index = 0;
		this.init(tool);
		setViewList(1);
//		this.add(likeButton);
//		this.add(disLikeButton);
		this.add(button1);
		this.add(button2);
		//this.add(jtf);
		this.add(jsp);
		this.setBackground(Color.white);
		
//		for (int i = 0; i < recommendViewView.size(); i++) {
//			System.out.println(recommendViewView.get(i));
//		}
		
		
	}
	
	
	
	private void init(Tool tool){
		
		button1 = new JButton("�û������Ƽ�");
		button1.setBounds(20, 120, 155, 40);
		button1.addActionListener(this);
		button1.setFont(tool.songTi);
		
		button2 = new JButton("���������Ƽ�");
		button2.setBounds(20, 190, 155, 40);
		button2.addActionListener(this);
		button2.setFont(tool.songTi);
		
//		likeButton = new LikeJButton();
//		likeButton.setBounds(90, 390, 250, 50);
//		
//		disLikeButton = new DisLikeJButton(data);
//		disLikeButton.setBounds(490, 390, 250, 50);
//		jtf = new JTextField();
//		jtf.setText("A");
//		jtf.setBounds(28, 50, 140, 40);
//		jtf.setHorizontalAlignment(JTextField.CENTER);
//		jtf.setFont(tool.songTi);
		
		
//		labelImage = new JLabel();
//		labelImage.setBounds(80, 20, 360, 360);
//		labelImage.setBorder(BorderFactory.createTitledBorder("����ͼƬ"));
//		
//		vImage = new ImageIcon[viewCount];
//		for (int j = 0; j < vImage.length; j++) {
//			vImage[j] = new ImageIcon(this.getClass().getClassLoader().getResource("viewImage/"+(j+1)+".jpg"));
//		}
		jTP = new JTextPane1();
		//reset(index);
		reset(userSim,userScoreCount-1,1);
		
		jsp = new JScrollPane(jTP);
		jsp.getVerticalScrollBar().setUnitIncrement(10);//���ù����ٶ�
		jsp.setBounds(190, 20, 850, 400);
		jsp.setBorder(BorderFactory.createEtchedBorder());
	}
	
	private void setViewList(int flag){
		if(flag == 1){//�����û��Ƽ�
			for (int i = 0; i < viewList.length&& i< recommendViewUser.size(); i++) {
				viewList[i] = Integer.parseInt(""+recommendViewUser.get(i));
			}
		}else if(flag == 2){//���ھ����Ƽ�
			for (int i = 0; i < viewList.length && i< recommendViewView.size(); i++) {
				//System.out.println(recommendViewView.get(i));
				viewList[i] = Integer.parseInt(""+recommendViewView.get(i));
			}
		}else if(flag == 3){//����Ƽ�
			for (int i = 0; i < viewList.length && i< recommendRandonView.size(); i++) {
				viewList[i] = Integer.parseInt(""+recommendRandonView.get(i));
			}
		}
	}
	
	private void reset(ArrayList[] userSim,int count,int num){
		
		//labelImage.setIcon(vImage[Integer.parseInt(""+(list[i].get(1)))-1]);
		jTP.reset("", true);
		String name;
		jTP.setRed_Bold_20("\n",true);
		if(num==1){
			name = data.getTestName();
			for (int j = 0; j < count; j++) {
				if(j<=1){
					jTP.setRed_Bold_20("\t"+name+"(�û�)��"+userSim[j].get(0)+"(�û�)�����ƶ��ǣ�"+userSim[j].get(1),true);//����
				}else if(j<=3){
					jTP.setBlue_Bold_20("\t"+name+"(�û�)��"+userSim[j].get(0)+"(�û�)�����ƶ��ǣ�"+userSim[j].get(1),true);//����
				}else {
					jTP.setBlack_Bold_20("\t"+name+"(�û�)��"+userSim[j].get(0)+"(�û�)�����ƶ��ǣ�"+userSim[j].get(1),true);//����
				}
			}
			jTP.setBlue_Bold_20("\n\t"+name+"(�û�)ϲ����"+data.mainUserLikeView+"����",true);
			jTP.setBlue_Bold_20("\t"+userSim[0].get(0)+"(�������û�)ϲ����"+data.userLikeView+" �Ⱦ���\n",true);
			jTP.setRed_Bold_26("\t��"+name+"(�û�)�Ƽ��ľ����ǣ�"+recommendViewUser,false);
			jTP.setBlue_Bold_20("�����ų�"+name+"(�û�)ϲ���ľ���",true);
		}else{
			name = data.getTestName1();
			for (int j = 0; j < count; j++) {
				if(j<=4){
					jTP.setRed_Bold_20("\t"+name+"(����)��"+userSim[j].get(0)+"(����)�����ƶ��ǣ�"+userSim[j].get(1),true);//����
				}else if(j<=9){
					jTP.setBlue_Bold_20("\t"+name+"(����)��"+userSim[j].get(0)+"(����)�����ƶ��ǣ�"+userSim[j].get(1),true);//����
				}else {
					jTP.setBlack_Bold_20("\t"+name+"(����)��"+userSim[j].get(0)+"(����)�����ƶ��ǣ�"+userSim[j].get(1),true);//����
				}
			}
			//jTP.setBlue_Bold_20("\n\t"+name+"(�û�)ϲ����"+data.mainUserLikeView+"����",true);
			jTP.setBlue_Bold_20("\n\t��"+name+"�����ƾ����ǣ�"+userSim[0].get(0)+"\n",true);
			jTP.setRed_Bold_26("\t��ĳ�û�ϲ��"+name+"(����)�����Ƽ��ľ����ǣ�"+recommendViewView,true);
		}
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1){
			
			userSim = data.getUserSim();
//			for (int i = 0; i < userScoreCount-1; i++) {
//				System.out.println(userSim[i].get(1));
//			}
			reset(userSim,userScoreCount-1,1);	
			
		}else if(e.getSource() == button2){
			
			viewSim = data.getViewSim();
//			for (int i = 0; i < viewScoreCount-1; i++) {
//				System.out.println(viewSim[i].get(1));
//			}
			reset(viewSim,viewScoreCount-1,2);
			
		}
		
	}
	
}

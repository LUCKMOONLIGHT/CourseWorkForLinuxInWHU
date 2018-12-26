package test;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tool.Data;
import tool.Tool;

public class MyFrame extends JFrame implements ActionListener,FocusListener {

	private int width = 1080;// 窗体宽
	private int height = 640;// 窗体高
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;// 屏幕宽
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;// 屏幕高
	private JLabel logoLabel;// 用户标签
	private JLabel bGLabel;// 背景标签
	private JLabel dateLabel;// 时间标签
	private JLabel userLabel;// 用户标签

	private JButton exit;// 退出按钮
	private JButton mini;// 最小化按钮
	private JButton[] gNB;// 功能按钮组
	private JButton jb;
	private Icon[] gNBImage;
	private int xOld;
	private int yOld;
	private JPanel pan;
	private JPanel pan2;
	private JPanel[] myPanel;
	private JPanel YMDPan;
	private JLabel[][] dayLabel;// 时间标签
	private Color fontColor = new Color(0, 162, 232);
	private int lastIndex = -1;
	private CardLayout card;
	private JTextField search;
	private int firstDay;
	private int countDays;// 该月总天数
	Data data;
	public Tool tool;
	Icon searchImage;
	private ArrayList[] list;
	

	public MyFrame(Data data) {
		
		super();
		this.data = data;
		list=data.getList();
		
		this.setLayout(null);// 空布局
		this.init();// 初始化工作
		this.addSome();
		this.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭方式
		this.frameAddListenner();// 添加监听

		// 设置背景图片
		this.getLayeredPane().add(bGLabel, new Integer(Integer.MIN_VALUE));
		bGLabel.setBounds(0, 0, tool.blackImage.getIconWidth(), tool.blackImage.getIconHeight());
		((JComponent) this.getContentPane()).setOpaque(false);

		this.setUndecorated(true);// 去掉边框
		this.setVisible(true);// 设置可见

	}

	private void init() {
		tool = new Tool();
		
		search = new JTextField("搜索");
		search.setBounds(540, 2, 200, 40);
		search.setHorizontalAlignment(JTextField.CENTER);
		search.addFocusListener(this);
		//search.setFocu;
		
		jb = new JButton();
		jb.setBounds(750, 2, 60, 40);
		jb.addActionListener(this);
		jb.setFocusable(true);
		searchImage =new ImageIcon(this.getClass().getClassLoader().getResource("image/search.jpg"));
		jb.setIcon(searchImage);
		//jb.setMargin(new Insets(0,0,0,0));//设置边距
		jb.setContentAreaFilled(false);//不绘制按钮区域
		//jb.setBorderPainted(false);//不绘制边框
		
		// 面板
		pan = new JPanel();
		pan.setLayout(null);
		pan.setBounds(5, 45, 1069, 130);
		pan.setBackground(Color.white);

		card = new CardLayout();
		
		
		pan2 = new JPanel();
		pan2.setLayout(card);
		pan2.setBounds(5, 175, 1069, 457);
		pan2.setBackground(Color.white);
		
		
		this.initPan();
		this.day();// 绘制日历
		//pan2.add(dateLabel);//添加时间标签
		//myPanel[3].add(jb);
		//pan2.add(YMDPan);//添加日历


		bGLabel = new JLabel(tool.blackImage);// 将背景图放在标签里。
		userLabel = new JLabel("当前用户： "+data.getUserName());
		logoLabel = new JLabel(tool.logoImage);
		logoLabel.setBounds(20, 2, 40, 40);
		userLabel.setFont(tool.kaiTi);
		userLabel.setForeground(tool.bJColor);
		userLabel.setBounds(80, 2, 150, 40);

		// 设置退出按钮
		exit = new JButton();
		exit.setIcon(tool.exitImage);
		exit.setBounds(1035, 2, 40, 40);
		exit.addActionListener(this);

		mini = new JButton();
		mini.setIcon(tool.miniImage);
		mini.setBounds(995, 2, 40, 40);
		mini.addActionListener(this);
	}

	
	private void initPan() {
		gNB = new JButton[3];//7个按钮
		gNBImage = new ImageIcon[7];//7个图片
		myPanel = new JPanel[7];//7个面板
		
		gNBImage[0] = new ImageIcon(this.getClass().getClassLoader().getResource("image/x1.jpg"));
		gNBImage[1] = new ImageIcon(this.getClass().getClassLoader().getResource("image/x2.jpg"));
//		gNBImage[2] = new ImageIcon(this.getClass().getClassLoader().getResource("image/x3.jpg"));
//		gNBImage[3] = new ImageIcon(this.getClass().getClassLoader().getResource("image/x4.jpg"));
//		gNBImage[4] = new ImageIcon(this.getClass().getClassLoader().getResource("image/x5.jpg"));
//		gNBImage[5] = new ImageIcon(this.getClass().getClassLoader().getResource("image/x6.jpg"));
		gNBImage[2] = new ImageIcon(this.getClass().getClassLoader().getResource("image/x7.jpg"));

		//生成面板......ing
		myPanel[0]=new GuessLikePane(tool,data);
		myPanel[1]=new SeasonRecommendation(tool,data);

		myPanel[2]=new TablePane(tool,data);
		
		
		
		
		for (int i = 0; i < gNB.length; i++) {
			
			gNB[i] = new JButton();
			gNB[i].setIcon(gNBImage[i]);
			gNB[i].setFont(tool.songTi);
			gNB[i].setBorder(null);
			gNB[i].addActionListener(this);
			gNB[i].setBounds(130 * i, 0, 130, 130);
			pan.add(gNB[i]);
			pan2.add(myPanel[i],""+i);
		}
		
		// gNB[0].setText("猜你喜欢");
		// gNB[1].setText("当季推荐");
		// gNB[2].setText("精品推荐");
		// gNB[3].setText("旅游资讯");
		// gNB[4].setText("旅游指南");
		// gNB[5].setText("旅行画册");
		// gNB[6].setText("关于");
	}

	/**
	 * 绘制日历
	 */
	private void day() {
		// 日历
		dayLabel = new JLabel[6][7];
		dayLabel[0][0] = new JLabel("星期日");
		dayLabel[0][1] = new JLabel("一");
		dayLabel[0][2] = new JLabel("二");
		dayLabel[0][3] = new JLabel("三");
		dayLabel[0][4] = new JLabel("四");
		dayLabel[0][5] = new JLabel("五");
		dayLabel[0][6] = new JLabel("星期六");
		for (int i = 0; i < dayLabel[0].length; i++) {
			dayLabel[0][i].setFont(tool.songTi);
			dayLabel[0][i].setOpaque(true);
			dayLabel[0][i].setHorizontalAlignment(JLabel.CENTER);
			dayLabel[0][i].setForeground(Color.blue);
			dayLabel[0][i].setBackground(tool.bJColor);
			dayLabel[0][i].setBorder(BorderFactory.createBevelBorder(0));
			dayLabel[0][i].setBounds(80 * i, 0, 80, 50);
		}
		dayLabel[0][0].setBackground(tool.bJColor2);
		dayLabel[0][6].setBackground(tool.bJColor2);
		dayLabel[0][0].setForeground(Color.red);
		dayLabel[0][6].setForeground(Color.red);

		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		switch (month) {

		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			countDays = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			countDays = 30;
			break;
		case 2:
			if (this.isMornDay(year))
				countDays = 29;
			else
				countDays = 28;
			break;
		default:
			countDays = 0;
			break;
		}

		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 0);
		int day = c.get(Calendar.HOUR_OF_DAY);
		int dow = c.get(Calendar.DAY_OF_WEEK);// 获取本月第一天是星期几

		Calendar c1 = Calendar.getInstance();// 可以对每个时间域单独修改

		int year1 = c.get(Calendar.YEAR);

		int month1 = c.get(Calendar.MONTH) + 1;
		//
		int date = c1.get(Calendar.DATE);
		dateLabel = new JLabel(year + " -- " + month + " 月");
		dateLabel.setHorizontalAlignment(JLabel.CENTER);
		dateLabel.setBounds(40, 35, 120, 20);

		// int hour1 = c.get(Calendar.HOUR_OF_DAY);
		//
		// int minute = c.get(Calendar.MINUTE);
		//
		// int second = c.get(Calendar.SECOND);

		// 现在时间
		// System.out.println(year + "/" + month + "/" + date1 + " " + hour1 +
		// ":" + minute + ":" + second);

		int nowDay = 1;
		for (int i = 1; i < dayLabel.length; i++) {
			for (int j = 0; j < dayLabel[i].length; j++) {
				if (i == 1 && j >= dow || i > 1 && nowDay <= countDays) {
					dayLabel[i][j] = new JLabel("" + nowDay);
					nowDay++;
					dayLabel[i][j].setBounds(80 * j, 50 * i, 80, 50);
					dayLabel[i][j].setOpaque(true);
					dayLabel[i][j].setHorizontalAlignment(JLabel.CENTER);
					if (j == 0 || j == 6) {
						dayLabel[i][j].setBackground(tool.bJColor2);
					} else {
						dayLabel[i][j].setBackground(tool.bJColor);
					}
					if (dayLabel[i][j].getText().equals(Integer.toString(date))) {
						dayLabel[i][j].setBackground(Color.green);
						dayLabel[i][j].setText("今天");
						dayLabel[i][j].setForeground(Color.red);
					}

					dayLabel[i][j].setBorder(BorderFactory.createBevelBorder(0));
				} else {
					dayLabel[i][j] = new JLabel("");
					dayLabel[i][j].setBounds(80 * j, 50 * i, 80, 50);
					// dayLabel[i][j].setOpaque(true);
					// dayLabel[i][j].setHorizontalAlignment(JLabel.CENTER);
					// dayLabel[i][j].setBackground(Color.GRAY);
				}
				dayLabel[i][j].setFont(tool.kaiTi);
			}
			if (nowDay > countDays)
				break;
		}

		YMDPan = new JPanel();
		YMDPan.setBorder(BorderFactory.createEtchedBorder());
		YMDPan.setLayout(null);
		YMDPan.setBounds(60, 60, 560, 300);
		// YMDPan.setBackground(Color.red);
		for (int i = 0; i < dayLabel.length; i++) {
			for (int j = 0; j < dayLabel[i].length; j++) {
				YMDPan.add(dayLabel[i][j]);
			}
		}
	}

	// 判断是否是闰年
	private boolean isMornDay(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return true;
		} else {
			return false;
		}

	}

	// 窗体添加组件
	private void addSome() {
		this.add(userLabel);
		this.add(logoLabel);
		this.add(exit);
		//this.add(mini);
		this.add(jb);
		this.add(search);
		this.add(pan);
		this.add(pan2);
	}

	// 窗体添加监听
	private void frameAddListenner() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// 记录鼠标按下时的坐标
				yOld = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				setLocation(xOnScreen - xOld, yOnScreen - yOld);// 设置拖拽后，窗口的位置
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exit) {
			System.exit(1);// 退出
		} else if (e.getSource() == mini) {
			this.setExtendedState(ICONIFIED);// 最小化
		} else if (e.getSource() == gNB[0]) {// 猜你喜欢
			if (lastIndex != -1) {
				gNB[lastIndex].setBorder(null);
			}
			lastIndex = 0;
			gNB[0].setBorder(BorderFactory.createEtchedBorder(tool.bJColor, fontColor));
			card.show(pan2, "0");
		} else if (e.getSource() == gNB[1]) {// 当季推荐
			if (lastIndex != -1) {
				gNB[lastIndex].setBorder(null);
			}
			lastIndex = 1;
			gNB[1].setBorder(BorderFactory.createEtchedBorder(tool.bJColor, fontColor));
			card.show(pan2, "1");
		} else if (e.getSource() == gNB[2]) {// 关于
			if (lastIndex != -1) {
				gNB[lastIndex].setBorder(null);
			}
			lastIndex = 2;
			gNB[2].setBorder(BorderFactory.createEtchedBorder(tool.bJColor, fontColor));
			card.show(pan2, "2");
		}else if(e.getSource() == jb){
			//new MyFrame2(data,tool);
		}
	}

	private void reset(int index) {
		for (int i = 0; i < gNB.length; i++) {
			if (i != index) {
				gNB[i].setBorder(null);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField t=(JTextField)e.getSource();
		if(t.getText().equals("搜索"))
			t.setText("");
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField t=(JTextField)e.getSource();
		if(t.getText().length()==0){
			if(e.getSource()== search){
				t.setText("搜索");
			}
		}
	}
}

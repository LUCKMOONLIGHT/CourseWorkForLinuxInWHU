package test;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import tool.Data;
import tool.Tool;

public class MyFrame4 extends JFrame implements ActionListener {

	private int width = 1080;// 窗体宽
	private int height = 640;// 窗体高
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;// 屏幕宽
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;// 屏幕高
	private JLabel logoLabel;// 用户标签
	private JLabel bGLabel;// 背景标签
	private JLabel dateLabel;// 时间标签
	private JLabel userLabel;// 用户标签

	private JButton exit;// 退出按钮
	private JButton jb[];// 
	private JButton[] gNB;// 功能按钮组
	private Icon[] gNBImage;
	private int xOld;
	private int yOld;
	private JPanel pan;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel panel[];
	
	private JPanel[] myPanel;
	private JPanel YMDPan;
	private JLabel[][] dayLabel;// 时间标签
	private Color fontColor = new Color(0, 162, 232);
	private int lastIndex = -1;
	private CardLayout card;
	private int firstDay;
	private int countDays;// 该月总天数
	JTextArea textArea;
	JTabbedPane jtab;
	public Tool tool;
	public JScrollPane js;

	public MyFrame4(Data data,Tool tool) {
		
		super();
		textArea=new JTextArea();  
		this.tool = tool;
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
		
		
		// 面板
		pan = new JPanel();
		pan.setLayout(null);
		pan.setBounds(5, 45, 1069, 587);
		//pan.setBackground(Color.red);
		
		
		pan2 = new JPanel();
		pan2.setLayout(null);
		pan2.setSize(1069, 1007);
		//pan2.setBounds(0, 0, 100, 200);
		pan2.setBackground(Color.red);
		panel = new JPanel[6];
		JEditorPane jEditorPane1 =new JEditorPane();
		jEditorPane1.setBounds(0, 0, screenWidth, height);
		pan2.add(jEditorPane1);
		js = new JScrollPane();
		js.getVerticalScrollBar().setUnitIncrement(10);//设置滚动速度
		js.setViewportView(pan2);
		js.setBounds(15, 15, 1039/2, 557);
		//js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pan.add(js);
		
		
		this.initPan();
		

		bGLabel = new JLabel(tool.blackImage);// 将背景图放在标签里。
		userLabel = new JLabel("当前用户： 空");
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

		
	}

	
	private void initPan() {
		
		
	}


	

	// 窗体添加组件
	private void addSome() {
		this.add(userLabel);
		this.add(logoLabel);
		this.add(exit);
		this.add(pan);
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
			this.dispose();
		} 
	}

	
}

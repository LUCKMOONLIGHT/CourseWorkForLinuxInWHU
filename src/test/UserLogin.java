package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tool.Data;

public class UserLogin extends JFrame implements ActionListener {

	public Data data;

	private int width = 800;// 窗体宽
	private int height = 500;// 窗体高
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;// 屏幕宽
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;// 屏幕高
	private Icon blackImage, exitImage, miniImage;
	private Icon logoImage;
	private Icon viewImage;// 景点图片

	Icon[] viewImageArray;// 景点图片
	private JButton exit;// 退出按钮
	private JButton mini;// 最小化按钮
	private JButton jb;// 忘记密码?
	private JButton createButton;// 注册
	private JButton loginButton;// 登录
	private int xOld;
	private int yOld;
	private PlayerThread pt; // 播放线程

	private JCheckBox sava;// 记住密码
	private JLabel bGLabel;// 背景标签
	JLabel imageLabel;// 图片标签
	private JLabel UserNameLabel;// 用户名标签
	private JLabel passwordLabel;// 密码标签
	private JLabel titleLabel;// 标题
	private JLabel label;// 会员登录
	private JTextField UserName;// 用户账号框
	private JPasswordField UserPassword;// 用户密码框
	private Font font;
	private Font titleFont;
	private Color fontColor;
	private File file;
	String s;

	public UserLogin() {
		super();
		data = new Data();
		file = new File(".\\" + data.getUserNumId() + ".txt");
		this.setLayout(null);// 空布局
		this.init();// 初始化工作
		this.addSome();
		this.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭方式
		this.frameAddListenner();// 添加监听
		pt.start();
		// 设置背景图片
		this.getLayeredPane().add(bGLabel, new Integer(Integer.MIN_VALUE));
		bGLabel.setBounds(0, 0, blackImage.getIconWidth(), blackImage.getIconHeight());
		((JComponent) this.getContentPane()).setOpaque(false);

		this.setUndecorated(true);// 去掉边框
		this.setVisible(true);// 设置可见
	}

	private void init() {

		//
		fontColor = new Color(0, 162, 232);

		// 设置字体
		font = new Font("楷体", Font.BOLD, 20);
		titleFont = new Font("楷体", Font.BOLD + Font.ITALIC, 28);

		pt = new PlayerThread(this);

		// 添加图片
		viewImageArray = new ImageIcon[6];
		viewImageArray[0] = new ImageIcon(this.getClass().getClassLoader().getResource("image/1.jpeg"));
		viewImageArray[1] = new ImageIcon(this.getClass().getClassLoader().getResource("image/2.jpeg"));
		viewImageArray[2] = new ImageIcon(this.getClass().getClassLoader().getResource("image/3.jpg"));
		viewImageArray[3] = new ImageIcon(this.getClass().getClassLoader().getResource("image/5.jpg"));
		viewImageArray[4] = new ImageIcon(this.getClass().getClassLoader().getResource("image/6.jpg"));
		viewImageArray[5] = new ImageIcon(this.getClass().getClassLoader().getResource("image/view.png"));
		viewImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/view.png"));
		blackImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/blackUserImage.jpg"));
		exitImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/exitImage.jpg"));
		miniImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/miniImage.png"));
		logoImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/logo.jpg"));
		bGLabel = new JLabel(blackImage);// 将背景图放在标签里。

		// 设置退出按钮
		exit = new JButton();
		exit.setIcon(exitImage);
		exit.setBounds(755, 2, 40, 40);
		exit.addActionListener(this);

		// 设置最小化按钮
		mini = new JButton();
		mini.setIcon(miniImage);
		mini.setBounds(715, 2, 40, 40);
		mini.addActionListener(this);

		// 设置忘记密码按钮
		jb = new JButton("找回密码");
		jb.setFont(font);
		jb.setBounds(610, 280, 120, 40);
		jb.setBorder(null);
		jb.setContentAreaFilled(false);
		jb.addActionListener(this);
		jb.setForeground(Color.blue);

		// 登录按钮
		loginButton = new JButton("登 录");
		loginButton.setFont(font);
		loginButton.setBounds(480, 330, 230, 50);
		loginButton.setBorder(null);
		loginButton.addActionListener(this);
		loginButton.setBackground(fontColor);
		loginButton.setForeground(Color.white);

		createButton = new JButton("立即注册");
		createButton.setFont(font);
		createButton.setBounds(610, 400, 120, 40);
		createButton.setBorder(null);
		createButton.setContentAreaFilled(false);
		createButton.addActionListener(this);
		createButton.setForeground(Color.blue);

		// 设置标签
		imageLabel = new JLabel(viewImageArray[5]);
		imageLabel.setBounds(40, 95, 360, 360);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());

		titleLabel = new JLabel("旅游推荐系统 V1.0", JLabel.CENTER);
		titleLabel.setFont(titleFont);
		titleLabel.setIcon(logoImage);
		titleLabel.setBounds(10, 2, 350, 40);
		titleLabel.setForeground(Color.blue);

		label = new JLabel("会员登录", JLabel.CENTER);
		label.setFont(font);
		label.setBounds(520, 100, 140, 40);
		// label.setBorder(BorderFactory.createEtchedBorder());
		label.setForeground(fontColor);

		UserNameLabel = new JLabel("用户名：", JLabel.CENTER);
		UserNameLabel.setFont(font);
		UserNameLabel.setBounds(450, 160, 90, 40);
		UserNameLabel.setForeground(Color.blue);

		passwordLabel = new JLabel("密  码：", JLabel.CENTER);
		passwordLabel.setFont(font);
		passwordLabel.setBounds(450, 220, 90, 40);
		passwordLabel.setForeground(Color.blue);

		// 设置输入框
		UserName = new JTextField();
		UserName.setFont(font);
		UserName.setHorizontalAlignment(JTextField.CENTER);
		UserName.setBounds(550, 160, 190, 40);
		UserName.setSelectedTextColor(Color.BLUE);// 设置用于呈现选定文本的当前颜色。

		UserPassword = new JPasswordField();
		UserPassword.setFont(font);
		UserPassword.setEchoChar('*');
		UserPassword.setHorizontalAlignment(JPasswordField.CENTER);
		UserPassword.setBounds(550, 220, 190, 40);

		// 设置复选框、记住密码
		sava = new JCheckBox("记住密码");
		sava.setBounds(460, 280, 120, 40);
		sava.addActionListener(this);
		sava.setSelected(true);
		sava.setFont(font);
		sava.setOpaque(false);
	}

	public void readFile() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			int i = fis.read();
			s = "";
			while (i != -1) {
				s += (char) i;
				i = fis.read();
			}
			data.setUserNumId(s.substring(0, s.indexOf("#")));
			data.setUserPassword(s.substring(s.indexOf("#") + 1));
			
			UserName.setText(data.getUserNumId());
			UserPassword.setText(data.getUserPassword());
			//System.out.println(data.getUserId() + ",,," + data.getUserPassword());
			
			fis.close();
		} catch (FileNotFoundException e) {
			//JOptionPane.showMessageDialog(null, "文件未找到！");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "文件异常！");
		}
	}

	public void writeFile() {

		FileOutputStream fos = null;

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "文件异常！");
			}
		}
		try {
			fos = new FileOutputStream(file, false);// true代表追加，默认是false
			s = UserName.getText()+"#"+UserPassword.getText();
			fos.write(s.getBytes());//
			fos.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "文件未找到！");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "文件异常！");
		}

	}
	
	private void go(){
		if(UserName.getText().equals("1234")&&UserPassword.getText().equals("5678")){
			this.dispose();
			if(sava.isSelected()){
				this.writeFile();
			}
			new MyFrame(data);
		}else if(UserName.getText().equals("1234")&&!UserPassword.getText().equals("5678")){
			ShowMessagePane sm=new ShowMessagePane("密码错误！");
		}else if(UserName.getText().equals("")&&UserPassword.getText().equals("")){
			ShowMessagePane sm=new ShowMessagePane("请输入账号和用户名！");
			
		}
		
	}
	

	// 添加组件
	private void addSome() {
		this.add(exit);// 退出按钮
		this.add(mini);// 最小化按钮
		this.add(titleLabel);// 标题
		this.add(label);// 会员登录
		this.add(UserNameLabel);// 用户名
		this.add(passwordLabel);// 密码
		this.add(UserName);// 用户输入框
		this.add(UserPassword);// 密码输入框
		this.add(imageLabel);// 图片
		this.add(sava);
		this.add(jb);
		this.add(loginButton);
		this.add(createButton);
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
			System.exit(1);
		} else if (e.getSource() == mini) {
			this.setExtendedState(ICONIFIED);
		} else if (e.getSource() == jb) {
			System.out.println("4444");
		} else if (e.getSource() == loginButton) {
			go();
		}
	}

}

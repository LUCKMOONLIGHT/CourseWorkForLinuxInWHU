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

	private int width = 800;// �����
	private int height = 500;// �����
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;// ��Ļ��
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;// ��Ļ��
	private Icon blackImage, exitImage, miniImage;
	private Icon logoImage;
	private Icon viewImage;// ����ͼƬ

	Icon[] viewImageArray;// ����ͼƬ
	private JButton exit;// �˳���ť
	private JButton mini;// ��С����ť
	private JButton jb;// ��������?
	private JButton createButton;// ע��
	private JButton loginButton;// ��¼
	private int xOld;
	private int yOld;
	private PlayerThread pt; // �����߳�

	private JCheckBox sava;// ��ס����
	private JLabel bGLabel;// ������ǩ
	JLabel imageLabel;// ͼƬ��ǩ
	private JLabel UserNameLabel;// �û�����ǩ
	private JLabel passwordLabel;// �����ǩ
	private JLabel titleLabel;// ����
	private JLabel label;// ��Ա��¼
	private JTextField UserName;// �û��˺ſ�
	private JPasswordField UserPassword;// �û������
	private Font font;
	private Font titleFont;
	private Color fontColor;
	private File file;
	String s;

	public UserLogin() {
		super();
		data = new Data();
		file = new File(".\\" + data.getUserNumId() + ".txt");
		this.setLayout(null);// �ղ���
		this.init();// ��ʼ������
		this.addSome();
		this.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �رշ�ʽ
		this.frameAddListenner();// ��Ӽ���
		pt.start();
		// ���ñ���ͼƬ
		this.getLayeredPane().add(bGLabel, new Integer(Integer.MIN_VALUE));
		bGLabel.setBounds(0, 0, blackImage.getIconWidth(), blackImage.getIconHeight());
		((JComponent) this.getContentPane()).setOpaque(false);

		this.setUndecorated(true);// ȥ���߿�
		this.setVisible(true);// ���ÿɼ�
	}

	private void init() {

		//
		fontColor = new Color(0, 162, 232);

		// ��������
		font = new Font("����", Font.BOLD, 20);
		titleFont = new Font("����", Font.BOLD + Font.ITALIC, 28);

		pt = new PlayerThread(this);

		// ���ͼƬ
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
		bGLabel = new JLabel(blackImage);// ������ͼ���ڱ�ǩ�

		// �����˳���ť
		exit = new JButton();
		exit.setIcon(exitImage);
		exit.setBounds(755, 2, 40, 40);
		exit.addActionListener(this);

		// ������С����ť
		mini = new JButton();
		mini.setIcon(miniImage);
		mini.setBounds(715, 2, 40, 40);
		mini.addActionListener(this);

		// �����������밴ť
		jb = new JButton("�һ�����");
		jb.setFont(font);
		jb.setBounds(610, 280, 120, 40);
		jb.setBorder(null);
		jb.setContentAreaFilled(false);
		jb.addActionListener(this);
		jb.setForeground(Color.blue);

		// ��¼��ť
		loginButton = new JButton("�� ¼");
		loginButton.setFont(font);
		loginButton.setBounds(480, 330, 230, 50);
		loginButton.setBorder(null);
		loginButton.addActionListener(this);
		loginButton.setBackground(fontColor);
		loginButton.setForeground(Color.white);

		createButton = new JButton("����ע��");
		createButton.setFont(font);
		createButton.setBounds(610, 400, 120, 40);
		createButton.setBorder(null);
		createButton.setContentAreaFilled(false);
		createButton.addActionListener(this);
		createButton.setForeground(Color.blue);

		// ���ñ�ǩ
		imageLabel = new JLabel(viewImageArray[5]);
		imageLabel.setBounds(40, 95, 360, 360);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());

		titleLabel = new JLabel("�����Ƽ�ϵͳ V1.0", JLabel.CENTER);
		titleLabel.setFont(titleFont);
		titleLabel.setIcon(logoImage);
		titleLabel.setBounds(10, 2, 350, 40);
		titleLabel.setForeground(Color.blue);

		label = new JLabel("��Ա��¼", JLabel.CENTER);
		label.setFont(font);
		label.setBounds(520, 100, 140, 40);
		// label.setBorder(BorderFactory.createEtchedBorder());
		label.setForeground(fontColor);

		UserNameLabel = new JLabel("�û�����", JLabel.CENTER);
		UserNameLabel.setFont(font);
		UserNameLabel.setBounds(450, 160, 90, 40);
		UserNameLabel.setForeground(Color.blue);

		passwordLabel = new JLabel("��  �룺", JLabel.CENTER);
		passwordLabel.setFont(font);
		passwordLabel.setBounds(450, 220, 90, 40);
		passwordLabel.setForeground(Color.blue);

		// ���������
		UserName = new JTextField();
		UserName.setFont(font);
		UserName.setHorizontalAlignment(JTextField.CENTER);
		UserName.setBounds(550, 160, 190, 40);
		UserName.setSelectedTextColor(Color.BLUE);// �������ڳ���ѡ���ı��ĵ�ǰ��ɫ��

		UserPassword = new JPasswordField();
		UserPassword.setFont(font);
		UserPassword.setEchoChar('*');
		UserPassword.setHorizontalAlignment(JPasswordField.CENTER);
		UserPassword.setBounds(550, 220, 190, 40);

		// ���ø�ѡ�򡢼�ס����
		sava = new JCheckBox("��ס����");
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
			//JOptionPane.showMessageDialog(null, "�ļ�δ�ҵ���");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�ļ��쳣��");
		}
	}

	public void writeFile() {

		FileOutputStream fos = null;

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "�ļ��쳣��");
			}
		}
		try {
			fos = new FileOutputStream(file, false);// true����׷�ӣ�Ĭ����false
			s = UserName.getText()+"#"+UserPassword.getText();
			fos.write(s.getBytes());//
			fos.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "�ļ�δ�ҵ���");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�ļ��쳣��");
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
			ShowMessagePane sm=new ShowMessagePane("�������");
		}else if(UserName.getText().equals("")&&UserPassword.getText().equals("")){
			ShowMessagePane sm=new ShowMessagePane("�������˺ź��û�����");
			
		}
		
	}
	

	// ������
	private void addSome() {
		this.add(exit);// �˳���ť
		this.add(mini);// ��С����ť
		this.add(titleLabel);// ����
		this.add(label);// ��Ա��¼
		this.add(UserNameLabel);// �û���
		this.add(passwordLabel);// ����
		this.add(UserName);// �û������
		this.add(UserPassword);// ���������
		this.add(imageLabel);// ͼƬ
		this.add(sava);
		this.add(jb);
		this.add(loginButton);
		this.add(createButton);
	}

	// ������Ӽ���
	private void frameAddListenner() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// ��¼��갴��ʱ������
				yOld = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				setLocation(xOnScreen - xOld, yOnScreen - yOld);// ������ק�󣬴��ڵ�λ��
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

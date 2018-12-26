package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 自定义窗体，用于展示信息
 * 
 * @author 王某人
 * @version 创建时间：2018年2月25日
 */
public class ShowMessagePane extends JFrame implements FocusListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 250;// 窗体宽
	private int height = 50;// 窗体高
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;// 屏幕宽
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;// 屏幕高
	private JLabel likeLabel;//提示标签
	private Font font;//字体
	
	public ShowMessagePane(String s) {
		super();
		font = new Font("宋体", Font.BOLD, 22);
		likeLabel = new JLabel(s);
		likeLabel.setFont(font);
		likeLabel.setForeground(Color.white);
		likeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(likeLabel);
		this.addFocusListener(this);
		this.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 关闭方式
		this.setUndecorated(true);// 去掉边框
		this.getContentPane().setBackground(new Color(162,162,162));
		this.setVisible(true);// 设置可见
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
}

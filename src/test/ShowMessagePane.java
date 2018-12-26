package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * �Զ��崰�壬����չʾ��Ϣ
 * 
 * @author ��ĳ��
 * @version ����ʱ�䣺2018��2��25��
 */
public class ShowMessagePane extends JFrame implements FocusListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 250;// �����
	private int height = 50;// �����
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;// ��Ļ��
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;// ��Ļ��
	private JLabel likeLabel;//��ʾ��ǩ
	private Font font;//����
	
	public ShowMessagePane(String s) {
		super();
		font = new Font("����", Font.BOLD, 22);
		likeLabel = new JLabel(s);
		likeLabel.setFont(font);
		likeLabel.setForeground(Color.white);
		likeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(likeLabel);
		this.addFocusListener(this);
		this.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// �رշ�ʽ
		this.setUndecorated(true);// ȥ���߿�
		this.getContentPane().setBackground(new Color(162,162,162));
		this.setVisible(true);// ���ÿɼ�
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

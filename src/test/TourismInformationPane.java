package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import tool.Data;
import tool.Tool;

//ÂÃÓÎ×ÊÑ¶
public class TourismInformationPane extends JPanel implements ActionListener {
	
	
	
	
	private LikeJButton likeButton;//Ï²»¶°´Å¥
	private DisLikeJButton disLikeButton;//²»Ï²»¶°´Å¥
	private JLabel labelImage;
	private JTextPane jTP;
	private JButton left,right;
	
	
	
	
	
	
	
	public TourismInformationPane(Tool tool,Data data){
		
		
		this.setLayout(null);
		this.init(tool);
		this.add(likeButton);
		this.add(disLikeButton);
		this.add(left);
		this.add(right);
		this.add(labelImage);
		this.add(jTP);
		this.setBackground(Color.white);
	}
	
	private void init(Tool tool){
		
		left = new JButton("×ó");
		left.setBounds(30, 30, 38, 350);
		left.setIcon(tool.leftImage);
		left.addActionListener(this);
		
		right = new JButton("ÓÒ");
		right.setBounds(1000, 30, 38, 350);
		right.setIcon(tool.rightImage);
		right.addActionListener(this);
		
		likeButton = new LikeJButton();
		likeButton.setBounds(90, 390, 250, 50);
		
		disLikeButton = new DisLikeJButton();
		disLikeButton.setBounds(490, 390, 270, 50);
		
		
		labelImage = new JLabel();
		labelImage.setBounds(80, 20, 350, 360);
		labelImage.setBorder(BorderFactory.createTitledBorder("¾°µãÍ¼Æ¬"));
		
		jTP = new JTextPane();
		
		jTP.setBounds(440, 30, 550, 350);
		jTP.setBorder(BorderFactory.createEtchedBorder());
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == left){
			System.out.println("1");
		}else if(e.getSource() == right){
			System.out.println("2");
		}
		
	}
	
}

package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tool.Data;

public class LikeJButton extends JButton implements ActionListener{

	public int counts;//������
	int i = -1;
	ShowMessagePane mjf;
	private Font font;
	
	ImageIcon image1,image2,image3;
	
	
	public LikeJButton() {
		counts = 514;
		font = new Font("����",Font.BOLD,18);
		setHorizontalAlignment(JButton.LEFT);//���뷽ʽ
		setMargin(new Insets(0,0,0,0));//���ñ߾�
		//setContentAreaFilled(false);//�����ư�ť����
		setBorderPainted(false);//�����Ʊ߿�
		this.setText(this.counts+"�� | ϲ��");
		image1 =new ImageIcon(this.getClass().getClassLoader().getResource("image/jb1.jpg"));
		image2 = new ImageIcon(this.getClass().getClassLoader().getResource("image/jb2.jpg"));
		image3 =new ImageIcon(this.getClass().getClassLoader().getResource("image/jb3.jpg"));
		
		this.setIcon(image1);//����Ĭ��ͼƬ
		this.setRolloverIcon(image2);//������꾭��ͼƬ
		setPressedIcon(image3);//������갴��ͼƬ
		this.addActionListener(this);
		setFont(font);
		setForeground(Color.red);
		setBackground(Color.white);
		
	}
	
	public void restButton(int num){
		if(num == 0){
			i = -1;
			this.setIcon(image1);//����Ĭ��ͼƬ
			this.setRolloverIcon(image2);//������꾭��ͼƬ
		}else{
			i=1;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(i==-1){
			setIcon(image3);//����Ĭ��ͼƬ
			setRolloverIcon(image3);//������꾭��ͼƬ
			i = 1;
			counts++;
			this.setText(this.counts+"�� ϲ��");
		}else if(i == 1 &&  mjf == null){
			mjf = new ShowMessagePane("���Ѿ��޹��ˣ�");
		}else if(i == 1 &&  mjf != null){
			mjf .setVisible(true);;
		}
		
		
	}

}

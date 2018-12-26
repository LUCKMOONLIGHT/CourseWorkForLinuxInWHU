package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tool.Data;

public class DisLikeJButton2 extends JButton implements ActionListener{

	public int counts;//������
	int i = -1;
	ShowMessagePane mjf;
	private Font font;
	ImageIcon image1,image2,image3;
	
	public DisLikeJButton2() {
		counts = 169;
		font = new Font("����",Font.BOLD,18);
		setHorizontalAlignment(JButton.LEFT);//���뷽ʽ
		setMargin(new Insets(0,0,0,0));//���ñ߾�
		//setContentAreaFilled(false);//�����ư�ť����
		setBorderPainted(false);//�����Ʊ߿�
		//this.setText(this.counts+"�� | ��ϲ��");
		image1 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		image2 = new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		image3 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		
		this.setIcon(image1);//����Ĭ��ͼƬ
		this.setRolloverIcon(image2);//������꾭��ͼƬ
		setPressedIcon(image3);//������갴��ͼƬ
		
		
		
		this.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("image/bxh1.jpg")));//����Ĭ��ͼƬ
		this.setRolloverIcon(new ImageIcon(this.getClass().getClassLoader().getResource("image/bxh2.jpg")));//������꾭��ͼƬ
		setPressedIcon(new ImageIcon(this.getClass().getClassLoader().getResource("image/bxh3.jpg")));//������갴��ͼƬ
		this.addActionListener(this);
		setFont(font);
		setForeground(Color.red);
		setBackground(Color.white);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(i==-1){
			setIcon(image3);//����Ĭ��ͼƬ
			setRolloverIcon(image3);//������꾭��ͼƬ
			i = 1;
			counts++;
			this.setText(this.counts+"�� | ��ϲ��");
		}else if(i == 1 &&  mjf == null){
			mjf = new ShowMessagePane("���Ѿ��ȹ��ˣ�");
		}else if(i == 1 &&  mjf != null){
			mjf .setVisible(true);;
		}
		
		
	}

}

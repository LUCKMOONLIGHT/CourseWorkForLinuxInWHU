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

	public int counts;//计数器
	int i = -1;
	ShowMessagePane mjf;
	private Font font;
	ImageIcon image1,image2,image3;
	
	public DisLikeJButton2() {
		counts = 169;
		font = new Font("宋体",Font.BOLD,18);
		setHorizontalAlignment(JButton.LEFT);//对齐方式
		setMargin(new Insets(0,0,0,0));//设置边距
		//setContentAreaFilled(false);//不绘制按钮区域
		setBorderPainted(false);//不绘制边框
		//this.setText(this.counts+"人 | 不喜欢");
		image1 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		image2 = new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		image3 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		
		this.setIcon(image1);//设置默认图片
		this.setRolloverIcon(image2);//设置鼠标经过图片
		setPressedIcon(image3);//设置鼠标按下图片
		
		
		
		this.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("image/bxh1.jpg")));//设置默认图片
		this.setRolloverIcon(new ImageIcon(this.getClass().getClassLoader().getResource("image/bxh2.jpg")));//设置鼠标经过图片
		setPressedIcon(new ImageIcon(this.getClass().getClassLoader().getResource("image/bxh3.jpg")));//设置鼠标按下图片
		this.addActionListener(this);
		setFont(font);
		setForeground(Color.red);
		setBackground(Color.white);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(i==-1){
			setIcon(image3);//设置默认图片
			setRolloverIcon(image3);//设置鼠标经过图片
			i = 1;
			counts++;
			this.setText(this.counts+"人 | 不喜欢");
		}else if(i == 1 &&  mjf == null){
			mjf = new ShowMessagePane("你已经踩过了！");
		}else if(i == 1 &&  mjf != null){
			mjf .setVisible(true);;
		}
		
		
	}

}

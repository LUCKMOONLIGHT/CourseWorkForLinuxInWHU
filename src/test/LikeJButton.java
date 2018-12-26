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

	public int counts;//计数器
	int i = -1;
	ShowMessagePane mjf;
	private Font font;
	
	ImageIcon image1,image2,image3;
	
	
	public LikeJButton() {
		counts = 514;
		font = new Font("宋体",Font.BOLD,18);
		setHorizontalAlignment(JButton.LEFT);//对齐方式
		setMargin(new Insets(0,0,0,0));//设置边距
		//setContentAreaFilled(false);//不绘制按钮区域
		setBorderPainted(false);//不绘制边框
		this.setText(this.counts+"人 | 喜欢");
		image1 =new ImageIcon(this.getClass().getClassLoader().getResource("image/jb1.jpg"));
		image2 = new ImageIcon(this.getClass().getClassLoader().getResource("image/jb2.jpg"));
		image3 =new ImageIcon(this.getClass().getClassLoader().getResource("image/jb3.jpg"));
		
		this.setIcon(image1);//设置默认图片
		this.setRolloverIcon(image2);//设置鼠标经过图片
		setPressedIcon(image3);//设置鼠标按下图片
		this.addActionListener(this);
		setFont(font);
		setForeground(Color.red);
		setBackground(Color.white);
		
	}
	
	public void restButton(int num){
		if(num == 0){
			i = -1;
			this.setIcon(image1);//设置默认图片
			this.setRolloverIcon(image2);//设置鼠标经过图片
		}else{
			i=1;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(i==-1){
			setIcon(image3);//设置默认图片
			setRolloverIcon(image3);//设置鼠标经过图片
			i = 1;
			counts++;
			this.setText(this.counts+"人 喜欢");
		}else if(i == 1 &&  mjf == null){
			mjf = new ShowMessagePane("你已经赞过了！");
		}else if(i == 1 &&  mjf != null){
			mjf .setVisible(true);;
		}
		
		
	}

}

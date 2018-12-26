package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tool.Data;

public class DisLikeJButton extends JButton implements ActionListener,MouseListener{

	public int counts;//计数器
	int i = -1;
	ShowMessagePane mjf;
	private Font font;
	ImageIcon kong5,bianhua,kong0;
	ImageIcon kong4,kong3,kong2,kong1;
	Data data;
	int x=0;
	
	
	
	
	
	public DisLikeJButton(Data data) {
		counts = 169;
		this.data = data;
		font = new Font("宋体",Font.BOLD,18);
		setHorizontalAlignment(JButton.LEFT);//对齐方式
		setMargin(new Insets(0,0,0,0));//设置边距
		//setContentAreaFilled(false);//不绘制按钮区域
		setBorderPainted(false);//不绘制边框
		//this.setText(this.counts+"人 | 不喜欢");
		kong5 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		bianhua = new ImageIcon(this.getClass().getClassLoader().getResource("image/bianhua.jpg"));
		kong0 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong0.jpg"));
		kong1 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong1.jpg"));
		kong2 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong2.jpg"));
		kong3 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong3.jpg"));
		kong4 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong4.jpg"));
		
		this.setIcon(kong5);//设置默认图片
		this.setRolloverIcon(bianhua);//设置鼠标经过图片
		//setPressedIcon(kong0);//设置鼠标按下图片
		
		this.addActionListener(this);
		this.addMouseListener(this);
		setFont(font);
		setForeground(Color.red);
		setBackground(Color.white);
		
	}

	public void restButton(){
		this.setIcon(kong5);//设置默认图片
		this.setRolloverIcon(bianhua);//设置鼠标经过图片
		i = -1;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(i==-1){
			
			if(x<=25){
				setIcon(kong4);//设置默认图片
				setRolloverIcon(kong4);//设置鼠标经过图片
				this.setText(" 1 星 评 价");
			}else if(x>25&&x<=50){
				setIcon(kong3);//设置默认图片
				setRolloverIcon(kong3);//设置鼠标经过图片
				this.setText(" 2 星 评 价");
			}else if(x>50&&x<=75){
				setIcon(kong2);//设置默认图片
				setRolloverIcon(kong2);//设置鼠标经过图片
				this.setText(" 3 星 评 价");
			}else if(x>75&&x<=100){
				setIcon(kong1);//设置默认图片
				setRolloverIcon(kong1);//设置鼠标经过图片
				this.setText(" 4 星 评 价");
			}else if(x>100&&x<=250){
				setIcon(kong0);//设置默认图片
				setRolloverIcon(kong0);//设置鼠标经过图片
				this.setText(" 5 星 评 价");
			}
			
			
//			setIcon(kong0);//设置默认图片
//			setRolloverIcon(kong0);//设置鼠标经过图片
			i = 1;
			counts++;
			//this.setText(this.counts+"人 | 不喜欢");
		}else if(i == 1 &&  mjf == null){
			mjf = new ShowMessagePane("你已经评过分了！");
		}else if(i == 1 &&  mjf != null){
			mjf .setVisible(true);;
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

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

	public int counts;//������
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
		font = new Font("����",Font.BOLD,18);
		setHorizontalAlignment(JButton.LEFT);//���뷽ʽ
		setMargin(new Insets(0,0,0,0));//���ñ߾�
		//setContentAreaFilled(false);//�����ư�ť����
		setBorderPainted(false);//�����Ʊ߿�
		//this.setText(this.counts+"�� | ��ϲ��");
		kong5 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong5.jpg"));
		bianhua = new ImageIcon(this.getClass().getClassLoader().getResource("image/bianhua.jpg"));
		kong0 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong0.jpg"));
		kong1 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong1.jpg"));
		kong2 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong2.jpg"));
		kong3 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong3.jpg"));
		kong4 =new ImageIcon(this.getClass().getClassLoader().getResource("image/kong4.jpg"));
		
		this.setIcon(kong5);//����Ĭ��ͼƬ
		this.setRolloverIcon(bianhua);//������꾭��ͼƬ
		//setPressedIcon(kong0);//������갴��ͼƬ
		
		this.addActionListener(this);
		this.addMouseListener(this);
		setFont(font);
		setForeground(Color.red);
		setBackground(Color.white);
		
	}

	public void restButton(){
		this.setIcon(kong5);//����Ĭ��ͼƬ
		this.setRolloverIcon(bianhua);//������꾭��ͼƬ
		i = -1;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(i==-1){
			
			if(x<=25){
				setIcon(kong4);//����Ĭ��ͼƬ
				setRolloverIcon(kong4);//������꾭��ͼƬ
				this.setText(" 1 �� �� ��");
			}else if(x>25&&x<=50){
				setIcon(kong3);//����Ĭ��ͼƬ
				setRolloverIcon(kong3);//������꾭��ͼƬ
				this.setText(" 2 �� �� ��");
			}else if(x>50&&x<=75){
				setIcon(kong2);//����Ĭ��ͼƬ
				setRolloverIcon(kong2);//������꾭��ͼƬ
				this.setText(" 3 �� �� ��");
			}else if(x>75&&x<=100){
				setIcon(kong1);//����Ĭ��ͼƬ
				setRolloverIcon(kong1);//������꾭��ͼƬ
				this.setText(" 4 �� �� ��");
			}else if(x>100&&x<=250){
				setIcon(kong0);//����Ĭ��ͼƬ
				setRolloverIcon(kong0);//������꾭��ͼƬ
				this.setText(" 5 �� �� ��");
			}
			
			
//			setIcon(kong0);//����Ĭ��ͼƬ
//			setRolloverIcon(kong0);//������꾭��ͼƬ
			i = 1;
			counts++;
			//this.setText(this.counts+"�� | ��ϲ��");
		}else if(i == 1 &&  mjf == null){
			mjf = new ShowMessagePane("���Ѿ��������ˣ�");
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

package tool;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Tool {

	public Font kaiTi,songTi;
	public Color color,bJColor,bJColor2;
	public Icon blackImage, exitImage, miniImage;
	public Icon logoImage;
	
	public Icon leftImage;
	public Icon rightImage;
	
	
	public int nowData;
	public int nowMonth;
	public int totalDays;
	
	public Tool() {
		kaiTi = new Font("¿¬Ìå", Font.BOLD, 18);
		songTi = new Font("ËÎÌå", Font.BOLD, 18);
		bJColor = new Color(239, 228, 176);
		bJColor2 = new Color(162, 244, 255);
		blackImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/blackImage.jpg"));
		exitImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/exitImage.jpg"));
		miniImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/miniImage.png"));
		logoImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/logo.jpg"));
		
		leftImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/left.jpg"));
		rightImage = new ImageIcon(this.getClass().getClassLoader().getResource("image/right.jpg"));
		
		
		
		
	}
}

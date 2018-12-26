package test;

import javax.swing.JOptionPane;

public class PlayerThread extends Thread {

	UserLogin userloginFrame;
	
	public PlayerThread(UserLogin mf) {
		userloginFrame = mf;
	}
	
	@Override
	public void run() {
		int index=0;
		while(true){
			try {
				Thread.sleep(3200);
				userloginFrame.imageLabel.setIcon(userloginFrame.viewImageArray[index]);
				index++;
				if(index==userloginFrame.viewImageArray.length){
					index=0;
				}
				
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "≥Ã–Ú“Ï≥££°");
			}
		}
	
	}
}

package test;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class JTextPane1 extends JTextPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextPane1() {
		// this.setBackground(Color.black);
		this.setEditable(false);
	}

	public void setBlack_Bold_20(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.black);// 设置颜色
		StyleConstants.setFontSize(attrset, 20);// 设置字号
		StyleConstants.setBold(attrset, true);// 加粗
		insert(str, attrset, bool);
	}
	
	public void setGreen_Bold_20(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.green);// 设置颜色
		StyleConstants.setFontSize(attrset, 20);// 设置字号
		StyleConstants.setBold(attrset, true);// 加粗
		insert(str, attrset, bool);
	}

	public void setBlue_Bold_20(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.blue);// 设置颜色
		StyleConstants.setFontSize(attrset, 20);// 设置字号
		StyleConstants.setBold(attrset, true);// 加粗
		insert(str, attrset, bool);
	}

	public void setBlue_Bold_24(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.blue);// 设置颜色
		StyleConstants.setFontSize(attrset, 24);// 设置字号
		StyleConstants.setBold(attrset, true);// 加粗
		insert(str, attrset, bool);
	}

	public void setRed_Bold_20(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.red);// 设置颜色
		StyleConstants.setFontSize(attrset, 20);// 设置字号
		StyleConstants.setBold(attrset, true);// 加粗
		insert(str, attrset, bool);
	}

	public void setRed_Bold_26(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.red);// 设置颜色
		StyleConstants.setFontSize(attrset, 26);// 设置字号
		StyleConstants.setBold(attrset, true);// 加粗
		insert(str, attrset, bool);
	}

	public void setRed_Bold_Italic_20(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.red);// 设置颜色
		StyleConstants.setFontSize(attrset, 20);// 设置字号
		StyleConstants.setBold(attrset, true);// 加粗
		StyleConstants.setItalic(attrset, true);
		insert(str, attrset, bool);
	}

	public void setBlue_Italic_Bold_22(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.blue);
		StyleConstants.setItalic(attrset, true);
		StyleConstants.setFontSize(attrset, 22);
		insert(str, attrset, bool);
	}

	public void setRed_UnderLine_Italic_24(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.red);
		StyleConstants.setUnderline(attrset, true);
		StyleConstants.setItalic(attrset, true);
		StyleConstants.setFontSize(attrset, 24);
		insert(str, attrset, bool);
	}

	public void setRed_UnderLine_24(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, Color.red);
		StyleConstants.setUnderline(attrset, true);
		StyleConstants.setFontSize(attrset, 24);
		insert(str, attrset, bool);
	}

	public void reset(String str, boolean bool) {
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setFontSize(attrset, 24);
		delete("", attrset);
	}

	// 这个方法最主要的用途是将字符串插入到JTextPane中。
	public void insert(String str, AttributeSet attrset, boolean bool) {
		Document docs = this.getDocument();// 利用getDocument()方法取得JTextPane的Document
											// // instance.0
		if (bool == true) {
			str = str + "\n";
		}
		try {
			docs.insertString(docs.getLength(), str, attrset);
		} catch (BadLocationException ble) {
			System.out.println("BadLocationException:" + ble);
		}
	}

	// 这个方法最主要的用途是将字符串插入到JTextPane中。
	public void delete(String str, AttributeSet attrset) {
		Document docs = this.getDocument();// 利用getDocument()方法取得JTextPane的Document
		try {
			docs.remove(0, docs.getLength());
			docs.insertString(0, "", attrset);
		} catch (BadLocationException ble) {
			System.out.println("BadLocationException:" + ble);
		}
	}

	public Component getComponent() {
		return this;
	}

}

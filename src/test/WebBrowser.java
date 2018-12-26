package test;

/*
**网页浏览器主程序
**WebBrowser.java
*/
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class WebBrowser extends JFrame implements HyperlinkListener, ActionListener {

	// 建立工具栏用来显示地址栏
	JToolBar bar = new JToolBar();

	// 建立网页显示界面
	JTextField jurl = new JTextField(60); // 地址栏
	JEditorPane jEditorPane1 = new JEditorPane();// 网页框
	JScrollPane scrollPane = new JScrollPane(jEditorPane1); // 滚动条

	JWindow window = new JWindow(WebBrowser.this); // 使用指定的所有者框架创建窗口

	Toolkit toolkit = Toolkit.getDefaultToolkit();// 获取默认工具包

	// 建立工具栏中的按钮组件
	JLabel label = new JLabel("地址");
	JButton button = new JButton("转向");
	Box adress = Box.createHorizontalBox(); // 创建一个从左到右显示其组件

	public WebBrowser() {
		setTitle("网页浏览器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 为jEditorPane1添加事件侦听
		jEditorPane1.addHyperlinkListener(this);
		Container contentPane = getContentPane(); // 实例化容器
		// 设置大小
		scrollPane.setPreferredSize(new Dimension(100, 500));
		contentPane.add(scrollPane, BorderLayout.SOUTH);
		contentPane.add(bar, BorderLayout.CENTER);

		adress.add(label);
		adress.add(jurl);
		adress.add(button);
		bar.add(adress);

		button.addActionListener(this);
		jurl.addActionListener(this);
	}

	/**
	 ** 实现监听器接口的actionPerformed函数
	 */
	public void actionPerformed(ActionEvent e) {
		String url = "";
		// 点击转向按钮
		if (e.getSource() == button) {
			// 获得地址栏的内容
			url = jurl.getText();
			// url不为“”，并且以“http://”开头
			if (url.length() > 0 && url.startsWith("http://")) {
				try {
					// JEditorPane组件显示url的内容链接
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					// 重新布局
					jEditorPane1.revalidate();
				} catch (Exception ex) {
					// 如果链接显示失败，则弹出选择对话框“无法打开该搜索页”
					JOptionPane.showMessageDialog(WebBrowser.this, "无法打开该搜索页", "网页浏览器", JOptionPane.ERROR_MESSAGE);
				}
			}
			// url不为“”，并且不以“http://”开头
			else if (url.length() > 0 && !url.startsWith("http://")) {
				// 在url前面添加“http://”
				url = "http://" + url;
				try {
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					jEditorPane1.revalidate();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(WebBrowser.this, "无法打开该搜索页", "网页浏览器", JOptionPane.ERROR_MESSAGE);
				}
			}
			// 没有输入url，即url为空
			else if (url.length() == 0) {
				JOptionPane.showMessageDialog(WebBrowser.this, "请输入链接地址", "网页浏览器", JOptionPane.ERROR_MESSAGE);
			}

		}
		// 输入地址后点击回车
		else if (e.getSource() == jurl) {
			url = jurl.getText();
			if (url.length() > 0 && url.startsWith("http://")) {
				try {
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					jEditorPane1.revalidate();
					jurl.setMaximumSize(jurl.getPreferredSize());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(WebBrowser.this, "无法打开该搜索页", "网页浏览器", JOptionPane.ERROR_MESSAGE);
				}
			} else if (url.length() > 0 && !url.startsWith("http://")) {
				url = "http://" + url;
				try {
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					jEditorPane1.revalidate();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(WebBrowser.this, "无法打开该搜索页", "网页浏览器", JOptionPane.ERROR_MESSAGE);
				}
			} else if (url.length() == 0) {
				JOptionPane.showMessageDialog(WebBrowser.this, "请输入链接地址", "网页浏览器", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 ** 实现监听器接口的hyperlinkUpdate函数
	 */
	public void hyperlinkUpdate(HyperlinkEvent e) {
		try {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				jEditorPane1.setPage(e.getURL());
		} catch (Exception ex) {
			ex.printStackTrace(System.err);// ex.printStackTrace打印出异常，但是它还将显示出更深的调用信息。
		}
	}

	/* 生成一个IE对象 */
	public static void main(String[] args) {
		WebBrowser webBrowser = new WebBrowser();
		webBrowser.pack();
		webBrowser.setVisible(true);
	}
}

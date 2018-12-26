package test;

/*
**��ҳ�����������
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

	// ����������������ʾ��ַ��
	JToolBar bar = new JToolBar();

	// ������ҳ��ʾ����
	JTextField jurl = new JTextField(60); // ��ַ��
	JEditorPane jEditorPane1 = new JEditorPane();// ��ҳ��
	JScrollPane scrollPane = new JScrollPane(jEditorPane1); // ������

	JWindow window = new JWindow(WebBrowser.this); // ʹ��ָ���������߿�ܴ�������

	Toolkit toolkit = Toolkit.getDefaultToolkit();// ��ȡĬ�Ϲ��߰�

	// �����������еİ�ť���
	JLabel label = new JLabel("��ַ");
	JButton button = new JButton("ת��");
	Box adress = Box.createHorizontalBox(); // ����һ����������ʾ�����

	public WebBrowser() {
		setTitle("��ҳ�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ΪjEditorPane1����¼�����
		jEditorPane1.addHyperlinkListener(this);
		Container contentPane = getContentPane(); // ʵ��������
		// ���ô�С
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
	 ** ʵ�ּ������ӿڵ�actionPerformed����
	 */
	public void actionPerformed(ActionEvent e) {
		String url = "";
		// ���ת��ť
		if (e.getSource() == button) {
			// ��õ�ַ��������
			url = jurl.getText();
			// url��Ϊ�����������ԡ�http://����ͷ
			if (url.length() > 0 && url.startsWith("http://")) {
				try {
					// JEditorPane�����ʾurl����������
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					// ���²���
					jEditorPane1.revalidate();
				} catch (Exception ex) {
					// ���������ʾʧ�ܣ��򵯳�ѡ��Ի����޷��򿪸�����ҳ��
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸�����ҳ", "��ҳ�����", JOptionPane.ERROR_MESSAGE);
				}
			}
			// url��Ϊ���������Ҳ��ԡ�http://����ͷ
			else if (url.length() > 0 && !url.startsWith("http://")) {
				// ��urlǰ����ӡ�http://��
				url = "http://" + url;
				try {
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					jEditorPane1.revalidate();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸�����ҳ", "��ҳ�����", JOptionPane.ERROR_MESSAGE);
				}
			}
			// û������url����urlΪ��
			else if (url.length() == 0) {
				JOptionPane.showMessageDialog(WebBrowser.this, "���������ӵ�ַ", "��ҳ�����", JOptionPane.ERROR_MESSAGE);
			}

		}
		// �����ַ�����س�
		else if (e.getSource() == jurl) {
			url = jurl.getText();
			if (url.length() > 0 && url.startsWith("http://")) {
				try {
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					jEditorPane1.revalidate();
					jurl.setMaximumSize(jurl.getPreferredSize());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸�����ҳ", "��ҳ�����", JOptionPane.ERROR_MESSAGE);
				}
			} else if (url.length() > 0 && !url.startsWith("http://")) {
				url = "http://" + url;
				try {
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false); // add by copy editor :)
					jEditorPane1.revalidate();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸�����ҳ", "��ҳ�����", JOptionPane.ERROR_MESSAGE);
				}
			} else if (url.length() == 0) {
				JOptionPane.showMessageDialog(WebBrowser.this, "���������ӵ�ַ", "��ҳ�����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 ** ʵ�ּ������ӿڵ�hyperlinkUpdate����
	 */
	public void hyperlinkUpdate(HyperlinkEvent e) {
		try {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				jEditorPane1.setPage(e.getURL());
		} catch (Exception ex) {
			ex.printStackTrace(System.err);// ex.printStackTrace��ӡ���쳣��������������ʾ������ĵ�����Ϣ��
		}
	}

	/* ����һ��IE���� */
	public static void main(String[] args) {
		WebBrowser webBrowser = new WebBrowser();
		webBrowser.pack();
		webBrowser.setVisible(true);
	}
}

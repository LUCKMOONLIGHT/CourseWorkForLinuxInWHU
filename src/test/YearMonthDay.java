package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class YearMonthDay {

	public static void main(String[] args) {
	
		Date date = new Date();
		// format������������ָ����ʱ���ʽ��ʽ��ʱ���
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd"); // ����ĸ�ʽ�����Լ�����
		// format()������������ʽ��ʱ��ķ���
		String times = from.format(date);
		System.out.println(times);
		
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�   

		int year = c.get(Calendar.YEAR);  

		 int month = c.get(Calendar.MONTH);   

		int date2 = c.get(Calendar.DAY_OF_MONTH);    

		int hour = c.get(Calendar.HOUR_OF_DAY);   

		int minute = c.get(Calendar.MINUTE);   

		int second = c.get(Calendar.SECOND);  
		
		System.out.println(year + "/" + month + "/"  +date2 + ":" +minute + ":" + second);  
	}
}

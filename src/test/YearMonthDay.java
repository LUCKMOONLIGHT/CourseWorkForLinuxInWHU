package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class YearMonthDay {

	public static void main(String[] args) {
	
		Date date = new Date();
		// format对象是用来以指定的时间格式格式化时间的
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd"); // 这里的格式可以自己设置
		// format()方法是用来格式化时间的方法
		String times = from.format(date);
		System.out.println(times);
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   

		int year = c.get(Calendar.YEAR);  

		 int month = c.get(Calendar.MONTH);   

		int date2 = c.get(Calendar.DAY_OF_MONTH);    

		int hour = c.get(Calendar.HOUR_OF_DAY);   

		int minute = c.get(Calendar.MINUTE);   

		int second = c.get(Calendar.SECOND);  
		
		System.out.println(year + "/" + month + "/"  +date2 + ":" +minute + ":" + second);  
	}
}

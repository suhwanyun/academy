package academy.group5.util;

import java.util.Calendar;
import java.util.Date;

public class MyDate {
	/** 해당 날짜의 23시 59분 59초로 설정 */
	public Date getEndDate(Calendar cal){
		return getEndDate(cal.getTime());
	}
	/** 해당 날짜의 23시 59분 59초로 설정 */
	public Date getEndDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return cal.getTime();
	}
	
	/** 해당 날짜의 23시 59분 59초로 설정 */
	public Date getStartDate(Calendar cal){
		return getStartDate(cal.getTime());
	}
	/** 해당 날짜의 0시 0분 0초로 설정 */
	public Date getStartDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal.getTime();
	}
	
	/** 오늘 0시 0분 0초 */
	public Date getToday(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal.getTime();
	}
	
	/** 오늘 23시 59분 59초 */
	public Date getTodayEnd(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return cal.getTime();
	}
}

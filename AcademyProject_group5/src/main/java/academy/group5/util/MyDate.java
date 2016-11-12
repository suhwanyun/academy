package academy.group5.util;

import java.util.Calendar;
import java.util.Date;

import academy.group5.dto.LectureTime;

public class MyDate {
	/** 해당 날짜의 23시 59분 59초로 설정 */
	public Date getEndDate(Calendar cal){
		if(cal == null){
			return null;
		}
		return getEndDate(cal.getTime());
	}
	/** 해당 날짜의 23시 59분 59초로 설정 */
	public Date getEndDate(Date date){
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return cal.getTime();
	}
	
	/** 해당 날짜의 23시 59분 59초로 설정 */
	public Date getStartDate(Calendar cal){
		if(cal == null){
			return null;
		}
		return getStartDate(cal.getTime());
	}
	/** 해당 날짜의 0시 0분 0초로 설정 */
	public Date getStartDate(Date date){
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal.getTime();
	}
	
	/** 오늘 0시 0분 0초 */
	public Date getTodayStart(){
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

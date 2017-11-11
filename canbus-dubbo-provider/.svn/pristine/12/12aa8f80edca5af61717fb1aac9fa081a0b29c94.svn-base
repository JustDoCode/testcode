package com.cpgps.canbus.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 * 日期工具类
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
	private static final Object object = new Object();
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * @描述 —— 格式化日期对象
     */
    public static Date date2date(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String str = sdf.format(date);
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
        return date;
    }
 
    /**
     * @描述 —— sql时间对象转换成字符串
     */
    public static String timestamp2string(Timestamp timestamp, String formatStr) {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        strDate = sdf.format(timestamp);
        return strDate;
    }
    /**
     * @描述 ——时间对象转换成字符串
     */
    public static String timestamp2string(Date date, String formatStr) {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        strDate = sdf.format(date);
        return strDate;
    }
    /**
     * @描述 —— 字符串转换成时间对象
     */
    public static Date string2date(String dateString, String formatStr) {
        Date formateDate = null;
        DateFormat format = new SimpleDateFormat(formatStr);
        try {
            formateDate = format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return formateDate;
    }
 
    /**
     * @描述 —— Date类型转换为Timestamp类型
     */
    public static Timestamp date2timestamp(Date date) {
        if (date == null)
            return null;
        return new Timestamp(date.getTime());
    }
 
    /**
     * @param time
     * @描述 —— 指定时间距离当前时间的中文信息
     */
    public static String getLnow(long time) {
        Calendar cal = Calendar.getInstance();
        long timel = cal.getTimeInMillis() - time;
        if (timel / 1000 < 60) {
            return "1分钟以内";
        } else if (timel / 1000 / 60 < 60) {
            return timel / 1000 / 60 + "分钟前";
        } else if (timel / 1000 / 60 / 60 < 24) {
            return timel / 1000 / 60 / 60 + "小时前";
        } else {
            return timel / 1000 / 60 / 60 / 24 + "天前";
        }
    }
    
    /**
     * @描述 —— 计算两个时间相差几天几小时几分几秒
     */
	public static String dateDiff(String startTime, String endTime, String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			long sec = diff % nd % nh % nm / ns;// 计算差多少秒
			return day + "天" + hour + "时" + min + "分" + sec + "秒";
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
     * @描述 —— 计算两个时间相差几天几小时几分几秒
     */
	public static String dateDiff(Date startTime, Date endTime) {
		// 按照传入的格式生成一个simpledateformate对象
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		// 获得两个时间的毫秒时间差异
		diff = endTime.getTime() - startTime.getTime();
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		long sec = diff % nd % nh % nm / ns;// 计算差多少秒
		return day + "天" + hour + "时" + min + "分" + sec + "秒";
	}
	
	/**
	 * 指定时间增加/减少指定小时
	 * @param date	指定时间
	 * @param hour	增加或减少的小时数
	 * @return
	 */
	public static Date dateAddHour (Date date,int hour) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.HOUR, hour);
		date = calendar.getTime(); 
		return date;
	}
    
    public static String long2str(String dateFormat,long millSec){
    	 SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
         Date date= new Date(millSec);
         return sdf.format(date);
    }
 // 时间戳转换成字符串
    public static String getstrtime(long datestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(new Date(datestamp * 1000L));
        return dateStr;
    }
    public static Date long2date(String dateFormat,long millSec){
        Date date= new Date(millSec);
        return date;
   }
    
    public static String DateToString(Date date, String pattern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(pattern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}
    
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
    /**
     * 获取指定日期的起始时间
     * @param date
     * @return
     */
    public static Date getDayStartTime(Date date) {  
    	  Calendar calendar = new GregorianCalendar();
          calendar.setTime(date);
          calendar.set(Calendar.HOUR_OF_DAY,0);
          calendar.set(Calendar.MINUTE,0);
          calendar.set(Calendar.SECOND,0);
          calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();  
    }  
    /**
     * 获取指定日期的结束时间
     * @param date
     * @return
     */
    public static Date getDayEndTime(Date date) {  
    	 Calendar calendar = new GregorianCalendar();
         calendar.setTime(date);
         calendar.set(Calendar.HOUR_OF_DAY, 23);  
         calendar.set(Calendar.MINUTE, 59);  
         calendar.set(Calendar.SECOND, 59);  
        calendar.set(Calendar.MILLISECOND, 999);  
        return calendar.getTime();  
    }  
    public static String getDayStartTimeStr() {  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return DateToString(todayStart.getTime(),"yyyy-MM-dd HH:mm:ss:SSS");  
    }  
  
    public static String getDayEndTimeStr() {  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return DateToString(todayEnd.getTime(),"yyyy-MM-dd HH:mm:ss:SSS");  
    }  
    /**
     * 获取一个月以前的时间
     * @param date
     * @return
     */
    public static Date getLastMonthDayByDate(Date date) {  
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Long date2TimeStamp(Date date){
        try {
            return date.getTime()/1000;
        } catch (Exception e) {
        	logger.error("时间转换异常",e);
            return new Date().getTime()/1000;
        }
    }
    /**
     * 获取昨天的时间
     * @param date
     * @return
     */
    public static Date getYesterdayTimeByCalendar() {  
    	Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }  
    public static void main(String[] args) {
    	System.out.println("----------------------"+12/3*4);
    	 Date date=new Date();
         Calendar calendar = new GregorianCalendar();
         calendar.setTime(date);
         calendar.set(Calendar.HOUR_OF_DAY,0);
         calendar.set(Calendar.MINUTE,0);
         calendar.set(Calendar.SECOND,0);
         calendar.set(Calendar.MILLISECOND,0);
         System.out.println("开始时间："+calendar.getTime());
         calendar.set(Calendar.HOUR_OF_DAY,23);
         calendar.set(Calendar.MINUTE,59);
         calendar.set(Calendar.SECOND,59);
         calendar.set(Calendar.MILLISECOND,999);
         System.out.println("结束时间："+calendar.getTime());
		System.out.println(getDayStartTimeStr());
	}
    /**
	 * 处理时间转换 秒转换成时分秒
	 */
	public static String formatSeconds(long seconds) {
		String timeStr = seconds + "秒";
		if (seconds > 60) {
			long second = seconds % 60;
			long min = seconds / 60;
			timeStr = min + "分" + second + "秒";
			if (min > 60) {
				min = (seconds / 60) % 60;
				long hour = (seconds / 60) / 60;
				timeStr = hour + "小时" + min + "分" + second + "秒";
			}
		}
		return timeStr;
	}
	public static long getTimestampByDateStr(String overSpeedStartTime) {
		
		return 0;
	}

	public static String getstrtime(String datestamp, String format) {
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
	        String dateStr = simpleDateFormat.format(new Date(Long.parseLong(datestamp) * 1000L));
	        return dateStr;
		
	}
}
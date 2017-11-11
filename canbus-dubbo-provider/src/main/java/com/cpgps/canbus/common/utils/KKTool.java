package com.cpgps.canbus.common.utils;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class KKTool {
    static Logger log = LoggerFactory.getLogger(KKTool.class);
	final static char BLANK_CHAR = 0x00;
	
	final static String EMPTY_STR = "";
	final static byte[] ZERO_BYTES = new byte[0];
	final static ChannelBuffer EMPTY_BUFFER = ChannelBuffers.buffer(0);
	
	final static byte BYTE_23 = 0x23;
    final static int BYTE_ff = 0xff;
    final static int BYTE_fc = 0xfc;
	
    /*数据的大小端标识*/
    public static final byte BIT_BIGENDIAN = 0;
    public static final byte BIT_LITTLEENDIAN = 1;
    
    /*时间*/
    public static final int MILLISECOND = 1;
    public static final int SECOND_MILLISECONDS = 1000 * MILLISECOND;
    public static final int MINUTE_MILLISECONDS = 60 * SECOND_MILLISECONDS;
    public static final int HOUR_MILLISECONDS = 60 * MINUTE_MILLISECONDS;
    public static final int DAY_MILLISECONDS = 24 * HOUR_MILLISECONDS;
    
    /**
     * 字节转整型
     * @param b
     * @return
     */
    public static int byteToInt(byte b) {
        return b >= 0 ? b : 256 + b;
    }

    /**
     * 截取字节数组转成字符串
     * 示例:0x10 0x12 0x01  返回  161801
     *      0xA0 0xB1 0x12  返回  16017718
     * @param data        字节数组
     * @param startIndex  截取开始的位置
     * @param len         截取的长度
     * @return
     */
    public static String bytesToString(byte[] data, int startIndex, int len) {
        StringBuilder ret = new StringBuilder();
        byte b = 0;
        for (int i = startIndex; (i < data.length) && (i < len); i ++) {
            b = (byte)data[i];
            ret.append(b);
        }
        return ret.toString();
    }



    /**
     * 截取指定开始位置指定长度的字节数组转成字符串
     * @param data          字节数组
     * @param startIndex    截取开始位置
     * @param len           截取长度
     * @return
     */
    public static String getString(byte[] data, int startIndex, int len) {
        if (startIndex < 0 || startIndex >= data.length)
            return "";
        int retStrLen = (startIndex + len <= data.length ? len : data.length - startIndex);
        byte[] tmp = new byte[retStrLen];
        System.arraycopy(data, startIndex, tmp, 0, retStrLen);
        return new String(tmp);
    }

    /**
     * 获取字节数组中从指定位置开始的指定长度的字节数组
     * @param data
     * @param startIndex
     * @param len
     * @return
     */
    public static byte[] getByteAry(byte[] data, int startIndex, int len) {
        byte[] buf = null;
        if (startIndex < 0 || startIndex >= data.length)
            return null;
        int realLen = (startIndex + len <= data.length ? len : data.length - startIndex);
        buf = new byte[realLen];
        System.arraycopy(data, startIndex, buf, 0, realLen);
        return buf;
    }

    /**
     * 获取日期时间 数组存放格式 yymmddhhnnss
     * 不正常情况返回当前时间
     * @param data
     * @param startIndex
     * @return
     */
    public static Date getDateTime(byte[] data, int startIndex) {
        if (startIndex < 0 || startIndex + 6 > data.length)
            return new Date();
        int year = KKTool.byteToInt(data[startIndex ++]) + 2000;
        int month = KKTool.byteToInt(data[startIndex ++]);
        int day = KKTool.byteToInt(data[startIndex ++]);
        int hour = KKTool.byteToInt(data[startIndex ++]);
        int minute = KKTool.byteToInt(data[startIndex ++]);
        int second = KKTool.byteToInt(data[startIndex ++]);
        Calendar c = new GregorianCalendar(year, month - 1, day, hour, minute, second);
        return c.getTime();
    }
    
    /**
     * 返回格式: yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String getFormatDateTime(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    public static String getFormatDate(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);	
    }
    
    /**
     * 字节转对应的十六进制字符串
     * @param data
     * @return
     */
    public static String byteToHexStr(byte data) {
    	String s = Integer.toHexString(data & 0xFF); 
    	return (s.length() == 2 ? s : "0" + s).toUpperCase();
//        int intValue = byteToInt(data);
//        return (intValue > 15? Integer.toHexString(intValue):"0" + Integer.toHexString(intValue));
    }

    /**
     * 字节数组转对应的十六进制字符串
     * @param data
     * @return 十六进制字符串，数组长度为空时返回空字符串""
     */
    public static String byteArrayToHexStr(byte[] data) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < data.length; i ++) {
            result.append(byteToHexStr(data[i]));
        }
        return result.toString();
    }

    /**
     * 字节数组按指定开始位置、指定长度转成十六进制字符串
     * @param data  字节数组
     * @param startIndex 开始位置
     * @param len 需转换的长度
     * @return 十六进制字符串，开始位置、长度指定不合法返回空字符串""
     */
    public static String byteArrayToHexStr(byte[] data, int startIndex, int len) {
        if(startIndex < 0 || startIndex >= data.length)
            return "";
        if (len < 0)
            return "";
        len = (startIndex + len <= data.length ? len : data.length - startIndex);
        byte[] buf = new byte[len];
        System.arraycopy(data, startIndex, buf, 0, len);
        return byteArrayToHexStr(buf);
    }
    
    public static String channelBufferToHexStr(ChannelBuffer channelBuffer) {
    	return byteArrayToHexStr(channelBuffer.array());
    }
    
    public static String getCurrFormatDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }
    public static String getCurrFormatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        return sdf.format(now);
    }

    public static boolean isDateBeforeToday(String date) {
    	String today = getFormatDateTime(new Date(), "yyyyMMdd");
    	return date.compareTo(today) <= 0;
    }
    
    public static boolean deleteFile(String filePath) {
    	if (filePath == null || filePath.equals(""))
    		return false;
    	
    	File file = new File(filePath);
    	if (file.exists())
    		return file.delete();
    	return false;
    }
    
    public static boolean renameFile(String oldFileName, String newFileName) {
    	if (oldFileName == null || oldFileName.equals("") || newFileName == null || newFileName.equals(""))
    		return false;
    	
    	File oFile = new File(oldFileName);
    	File nFile = new File(newFileName);
    	return oFile.renameTo(nFile);
    }
    
    /**
     * 返回昨天的标准格式字符串yyyyMMdd
     * @return
     */
    public static String getYesterdayFormatDate() {
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.DAY_OF_YEAR, -1);
    	return getFormatDate(c.getTime());
    }
    
    /**
     * 返回基于当天时间偏差的标准日期格式<p>
     * 示例：daysOffset = -1, 则返回昨天的标准格式的字符串
     * @param daysOffset
     * @param format
     * @return
     */
    public static String getFormatDate(int daysOffset, String format) {
    	Calendar c = Calendar.getInstance();
    	if (daysOffset != 0) {
    		c.add(Calendar.DAY_OF_YEAR, daysOffset);
    	}
    	return getFormatDateTime(c.getTime(), format);
    }
    


    public static void printLog(Object object) {
    	System.out.println(object);
    }
    
    /**
     * 字节数组拷贝
     * @param dst 目标数组
     * @param src 源数组
     * @param dstIndex 目标数组的开始拷贝位置
     * @return 拷贝是否成功 
     */
    public static boolean copyBytes(byte[] dst, byte[] src, int dstIndex) {
    	if (dst == null || src == null || dstIndex < 0 || dstIndex + src.length > dst.length)
    		return false;
    	try {
    		System.arraycopy(src, 0, dst, dstIndex, src.length);
    	} catch (Exception e) {
			return false;
		}
    	return true;
    }
    
    /**
     * 获取定长格式化字符串，不足补指定字符
     * 超过长度则截取
     * @param srcStr
     * @param len
     * @param fillChar
     * @param isFillLeft
     * @return
     */
    public static String getFormatStr(String srcStr, int len, String fillChar, boolean isFillLeft) {
    	if (srcStr.length() == len)
    		return srcStr;
    	
    	if (srcStr.length() > len) {//超过长度则截取
    		if(isFillLeft)
    			return srcStr.substring(srcStr.length() - len);
    		else
    			return srcStr.substring(0, len);
    	}
    	
    	StringBuilder sBuilder = null;
    	if (isFillLeft) {//左补
    		sBuilder = new StringBuilder();
			for (int i = 0; i < len - srcStr.length(); i ++) {
				sBuilder.append(fillChar);
			}    	
			sBuilder.append(srcStr);
    	} else {//右补
	    	sBuilder = new StringBuilder(srcStr);
			for (int i = 0; i < len - srcStr.length(); i ++) {
				sBuilder.append(fillChar);
			}
    	}
    	return sBuilder.toString();
    }
    
    public static char[] getFormatCharAry(String srcStr, int len) {
    	return getFormatStr(srcStr, len, "0", true).toCharArray();
    }


    /**
     * 根据毫秒值返回固定格式的时间字符串
     * 格式：yyyyMMdd HHmmss
     * @param d
     * @return
     */
    public static String getFormatDatetime(long d) {
        Date date = new Date(d);
        return getFormatDateTime(date, "yyyyMMdd HHmmss");
    }
    /**
     * check two array content is same
     * @param b1
     * @param b2
     * @return
     */
    public static boolean isArrayDataSame(byte[] b1, byte[] b2) {
    	if (b1 == null || b2 == null) {
    		return false;
    	}
    	
    	if (b1 == b2) {
    		return true;
    	}
    	if (b1.length != b2.length) {
    		return false;
    	}
    	
    	for (int i = 0; i < b1.length; i ++) {
    		if (b1[i] != b2[i]) {
    			return false;
    		}
    	}
    	return true;
    }

    
    /**
     * GBK编码的字节转成字符串
     * @param gbkBytes
     * @return
     */
    public static String toGBKStr(byte[] gbkBytes) {
    	if (gbkBytes != null) {
    		return new String(gbkBytes, Charset.forName("GBK"));
    	}
    	return EMPTY_STR;
    }
    
    /**
     * GBK编码的字符串转成字节数组
     * @param gbkStr
     * @return
     */
    public static byte[] toGBKBytes(String gbkStr) {
    	if (gbkStr != null) {
    		return gbkStr.getBytes(Charset.forName("GBK"));
    	}
    	return ZERO_BYTES;  
    }
    

    /**
     * 获取转义前的buffer
     * 数据内容进行转义判断，转义规则如下：
     * a)若数据内容中有出现字符0x23 0x23的在一起，需替换为字符0xff 0xfc；
     * @param channelBuffer
     * @return
     */
    public static ChannelBuffer getUnescapedBuffer(ChannelBuffer channelBuffer) {
    	if (channelBuffer != null) {
            log.debug("chanelBuffer:"+channelBuffer.writerIndex());
    		ChannelBuffer retBuffer = ChannelBuffers.buffer(channelBuffer.readableBytes());
    		byte b1 = 0;
    		byte b2 = 0;
    		//去掉头尾标识
    		for (int i = channelBuffer.readerIndex(); i < channelBuffer.writerIndex(); i++) {
    			b1 = channelBuffer.getByte(i);
    			if (b1 == BYTE_23) {
    				b2 = channelBuffer.getByte(i + 1);
    				if (b2 == BYTE_23) {
    					retBuffer.writeByte(BYTE_ff);
    					retBuffer.writeByte(BYTE_fc);
                        i++;
    				}
    			}else {
                    retBuffer.writeByte(b1);
                }
    		}
            log.debug("[" + retBuffer.readableBytes() + "] " + KKTool.channelBufferToHexStr(retBuffer));
    		return retBuffer;
    	} 
    	return EMPTY_BUFFER;
    }
    


    

    /**
     * 根据UTC时间获取日期
     * @param utc
     * @return
     */
    public static Date getDateFromUTC(long utc) {
    	return getCalendarFromUTC(utc).getTime();
    }
    
    
    /**
     * 根据UTC时间获取日历
     * @param utc
     * @return
     */
    public static Calendar getCalendarFromUTC(long utc) {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(utc * 1000);
    	return c;
    }
    
    
    /**
     * 由Date格式时间获取UTC格式时间
     * @param date	
     * @return
     */
    public static long getUTC(Date date) {
		return date.getTime()/1000;
	}
    
    
    /**
     * 获取当前时间的UTC格式时间
     * @return
     */
    public static long getCurrUTC() {
    	return new Date().getTime()/1000;
    }
    
    /**
     * 字符串转反向数组<br>
     * 长度为奇数时，前补0
     * 示例:<br>
     * "123456", 返回 byte[]{56, 34, 12}<br>
     * "12345",	 返回 byte[]{45, 23, 01}<br>
     * 
     * 参数不合法返回长度为0的字节数据
     * @param src	传入参数必须为数字
     * @return	
     */
    public static byte[] strToRevertAry(String src) {
    	if (src == null || src.length() == 0) {
    		return new byte[0];
    	}
    	
    	if (src.length() % 2 == 1) {
    		src = "0" + src;
    	}
    	byte[] buf = new byte[src.length()/2];
    	

		try {
			for(int i = 0; i < buf.length; i ++) {
				buf[buf.length - 1 - i] = (byte)(Integer.parseInt(src.substring(2 * i, 2 * i + 2), 10)); 
			}
		} catch (Exception e) {
			return new byte[0];
		}
    	return buf;
    }

    /**
     * BCC（异或校验）法
     * @param datas
     * @return
     */
    public static byte getBCCXor(byte[] datas){
        byte temp=datas[0];
        for (int i = 1; i <datas.length; i++) {
            temp ^=datas[i];
        }
        return temp;
    }

    public static String leftAddZero(byte num){
        return num < 10 ? (0+""+num) : (num+"");
    }
    public static String fillYear(byte year){
        if(year>70){
            return "19"+year;
        }else{
            return year < 10 ? ("200"+year) : ("20"+year);
        }
    }
    public static String getCalanderStr(byte[] data){
        return fillYear(data[0])+"-"+leftAddZero(data[1])+"-"+leftAddZero(data[2])+" "+leftAddZero(data[3])+":"+leftAddZero(data[4])+":"+leftAddZero(data[5]);
    }

    public static byte[] int2Bytes(int num) {
        byte[] byteNum = new byte[4];
        for (int ix = 0; ix < 4; ++ix) {
            int offset = 32 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static int bytes2Int(byte[] byteNum) {
        int num = 0;
        for (int ix = 0; ix < 4; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    public static byte int2OneByte(int num) {
        return (byte) (num & 0x000000ff);
    }

    public static int oneByte2Int(byte byteNum) {
        //针对正数的int
        return byteNum > 0 ? byteNum : (128 + (128 + byteNum));
    }

    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Long date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
            return new Date().getTime()/1000;
        }
    }
    public static void main(String[] args) {
    }
    
}
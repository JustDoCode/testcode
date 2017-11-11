/**
 * 
 */
package com.cpgps.canbus.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cpgps.canbus.repository.entity.can.UserAnalysisEntity;

/**
 * @author wangshuai01@e6yun.com 2017年5月17日
 */
@Component
@Scope("prototype")
public class CanbusDataUtil {
	static Logger logger = LoggerFactory.getLogger(CanbusDataUtil.class);
	private long zdsd;// 最大速度
	private long zdzs;// 最大转速
	private long lczdz;// 里程最大值
	private long lczxz;// 里程最小值
	private long zyhzxz;// 起始油耗(总油耗最小值)
	private long zyhzdz;// 结束油耗(总油耗最大值)
	private long gpslczdz;// gps里程最大值
	private long gpslczxz;// gps里程最小值
	private long gpszyhzxz;// gps起始油耗(总油耗最小值)
	private long gpszyhzdz;// gps结束油耗(总油耗最大值)
	private long yblczdz;// 仪表里程最大值
	private long yblczxz;// 仪表里程最小值
	private long ybzyhzxz;// 仪表起始油耗(总油耗最小值)
	private long ybzyhzdz;// 仪表结束油耗(总油耗最大值)
	private long yxsjzxz;// 起始运行时间(运行时间最小值)
	private long yxsjzdz;// 结束运行时间(运行时间最大值)
	List<Long> sumList = new ArrayList<Long>();// 驾驶循环的时间
	long fdjyxsj;// 发动机运行总时间

	/**
	 * 最大速度
	 * 
	 * @param x
	 * @return
	 */
	public long getZdsd(long x) {
		zdsd = Math.max(x, zdsd);
		return zdsd;
	}

	/**
	 * 最大转速
	 * 
	 * @param x
	 * @return
	 */
	public long getZdzs(long x) {
		zdzs = Math.max(x, zdzs);
		return zdzs;
	}

	/**
	 * 里程最大值
	 * 
	 * @param x
	 * @return
	 */
	public long getLczdz(long x) {
		if(x<0) return lczdz;
		lczdz = Math.max(x, lczdz);
		return lczdz;
	}

	/**
	 * 里程最小值
	 * 
	 * @param x
	 * @return
	 */
	public long getLczxz(long x) {
		if(x<0) return lczxz;
		if (lczxz == 0) lczxz = x;
		lczxz = Math.min(x, lczxz);
		return lczxz;
	}
	/**
	 * GPS里程最大值
	 * 
	 * @param x
	 * @return
	 */
	public long getGpsLczdz(long x) {
		if(x<0) return gpslczdz;
		gpslczdz = Math.max(x, gpslczdz);
		return gpslczdz;
	}

	/**
	 * GPS里程最小值
	 * 
	 * @param x
	 * @return
	 */
	public long getGpsLczxz(long x) {
		if (gpslczxz == 0)
			gpslczxz = x;
		if(x<0) return gpslczxz;
		gpslczxz = Math.min(x, gpslczxz);
		return gpslczxz;
	}
	/**
	 * 仪表里程最大值
	 * 
	 * @param x
	 * @return
	 */
	public long getYbLczdz(long x) {
		if(x<10) return yblczdz;
		yblczdz = Math.max(x, yblczdz);
		return yblczdz;
	}

	/**
	 * 仪表里程最小值
	 * 
	 * @param x
	 * @return
	 */
	public long getYbLczxz(long x) {
		if(x<10) return yblczxz;
		if (yblczxz == 0)
			yblczxz = x;
		yblczxz = Math.min(x, yblczxz);
		return yblczxz;
	}

	/**
	 * 起始油耗(总油耗最小值)
	 * 
	 * @param x
	 * @return
	 */
	public long getZyhzxz(long x) {
		if(x<10) return zyhzxz;
		if (zyhzxz == 0)
			zyhzxz = x;
		zyhzxz = Math.min(x, zyhzxz);
		return zyhzxz;
	}

	/**
	 * 结束油耗(总油耗最大值)
	 * 
	 * @param x
	 * @return
	 */
	public long getZyhzdz(long x) {
		zyhzdz = Math.max(x, zyhzdz);
		return zyhzdz;
	}
	
	/**
	 * GPS起始油耗(总油耗最小值)
	 * 
	 * @param x
	 * @return
	 */
	public long getGpsZyhzxz(long x) {
		if (gpszyhzxz == 0)
			gpszyhzxz = x;
		gpszyhzxz = Math.min(x, gpszyhzxz);
		return gpszyhzxz;
	}

	/**
	 * GPS结束油耗(总油耗最大值)
	 * 
	 * @param x
	 * @return
	 */
	public long getGpsZyhzdz(long x) {
		gpszyhzdz = Math.max(x, gpszyhzdz);
		return gpszyhzdz;
	}
	/**
	 * 仪表起始油耗(总油耗最小值)
	 * 
	 * @param x
	 * @return
	 */
	public long getYbZyhzxz(long x) {
		if (ybzyhzxz == 0)
			ybzyhzxz = x;
		ybzyhzxz = Math.min(x, ybzyhzxz);
		return ybzyhzxz;
	}

	/**
	 * 仪表结束油耗(总油耗最大值)
	 * 
	 * @param x
	 * @return
	 */
	public long getYbZyhzdz(long x) {
		ybzyhzdz = Math.max(x, ybzyhzdz);
		return ybzyhzdz;
	}

	/**
	 * 结束运行时间(运行时间最大值)
	 * 
	 * @param x
	 * @return
	 */
	public long getYxsjzdz(long x) {
		yxsjzdz = Math.max(x, yxsjzdz);
		return yxsjzdz;
	}

	/**
	 * 开始运行时间(运行时间最小值)
	 * 
	 * @param x
	 * @return
	 */
	public long getYxsjzxz(long x) {
		if (yxsjzxz == 0)
			yxsjzxz = x;
		if(x< 0) return yxsjzxz;
		yxsjzxz = Math.min(x, yxsjzxz);
		return yxsjzxz;
	}

	/**
	 * 求差值
	 * 
	 * @param args
	 */
	public long getDifference(long m, long n) {

		return Math.abs(m - n);

	}

	/**
	 * 累加
	 * 
	 * @param args
	 */
	public long getSum(long n) {

		return n++;

	}

	/**
	 * 除法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public Double div(double a, double b) {
		if (b == 0)
			return 0.0;
		double x = a / b;
		return x;
	}

	/**
	 * @param 发动机运行时间（求一个时间段内
	 *            发动机的时间区间）
	 * @return
	 */
	public long getFdjyxsj(long n) {
		fdjyxsj = 0;
		int size = sumList.size();
		if (size < 2) {
			sumList.add(size, n);
		} else {
			if (n > sumList.get(size - 1)) {
				sumList.remove(size - 1);
				sumList.add(size - 1, n);
			} else {
				sumList.add(size, n);
			}
		}
		for (int i = 1; i < sumList.size(); i++) {
			fdjyxsj += sumList.get(i);
		}
		fdjyxsj = fdjyxsj - sumList.get(0);
		return fdjyxsj;
	}

	/**
	 * 空档滑行占比
	 * 
	 * @param xcsj
	 * @param kdhxsj
	 * @return
	 */
	public double getKdhxzb(double xcsj, double kdhxsj) {
		if (xcsj <= 0)
			return 0;
		double kdhxsjzb = (kdhxsj / xcsj) * 100;
		return kdhxsjzb;

	}

	/**
	 * 获取时间占比
	 * 
	 * @param xcsj
	 * @param kdhxsj
	 * @return
	 */
	public double getSjzb(double total, double n) {
		if (total <= 0)
			return 0;
		double kdhxsjzb = (n / total) * 100;
		return kdhxsjzb;

	}
	
	/**
	 * 获取时间占用百分比
	 */
	public double getPercentage(double total,double n){
		if (total <= 0)
			return 0;
		double percentage = (n / total);
		return percentage;
	}

	/**
	 * 平均速度
	 * 
	 * @param zlc
	 * @param xcsj
	 * @return
	 */
	public double getPjsd(long a, long b) {

		if (b <= 0)
			return 0.0;
		return ((double) a / 1000) / ((double) b / 3600);
	}

	/**
	 * 获取最大值
	 * 
	 * @param dataList
	 * @return
	 */
	private int getDataMaxValue(List<Integer> dataList) {
		int max;
		max = dataList.get(0);
		for (Integer dataValue : dataList) {// 判断最小值
			if (dataValue > max) {
				max = dataValue;
			}
		}
		return max;
	}

	/**
	 * 获取最小值
	 * 
	 * @param dataList
	 * @return
	 */
	private int getDataMinValue(List<Integer> dataList) {
		int min;
		min = dataList.get(0);
		for (Integer dataValue : dataList) {
			if (dataValue < min) {
				min = dataValue;
			}
		}
		return min;
	}

	/**
	 * 获取平均值
	 * 
	 * @param dataList
	 * @return
	 */
	private int getDataAvgValue(List<Integer> dataList) {
		int sum = 0;
		for (Integer dataValue : dataList) {
			sum += dataValue;
		}
		int avg = sum / dataList.size();
		return avg;
	}

	/**
	 * 求和
	 * 
	 * @param dataList
	 * @return
	 */
	private int getDataSumValue(List<Integer> dataList) {
		int sum = 0;
		for (Integer dataValue : dataList) {
			sum += dataValue;
		}
		return sum;
	}

	
	/**
	 * 经济评分
	 * 
	 * @return
	 */
	public int getJjpf(UserAnalysisEntity userAnalysisEntity) {

		return 0;
	}

	/**
	 * 安全评分
	 * 
	 * @return
	 */
	public int getAqpf(UserAnalysisEntity userAnalysisEntity) {

		return 0;
	}

	/**
	 * 统计综合评分
	 * 
	 * @param jjpf经济评分
	 * @param aqpf安全评分
	 * @return
	 */
	public int getZhpf(int jjpf, int aqpf) {

		return 0;
	}

	/**
	 * 四舍五入保留两位小数
	 */
	public static double getFormatNumber(double f) {
		BigDecimal b = new BigDecimal(f);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}

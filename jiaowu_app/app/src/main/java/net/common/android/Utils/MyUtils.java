package net.common.android.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 * Created by Administrator on 2017/6/13.
 */

public class MyUtils {
	/**
	 * 获得当前的时间
	 * @return 当前的时间
	 */
	public static String getCurrentTime(){
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		dateFormat.applyPattern("yyyy年MM月dd日 HH:mm:ss");
		return dateFormat.format(new Date());
	}


	public static String long2String(long time){
		Date date = new Date();
		date.setTime(time);
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		dateFormat.applyPattern("yyyy年MM月dd日 HH:mm:ss");
		return dateFormat.format(date);
	}
}

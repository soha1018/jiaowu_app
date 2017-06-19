package net.common.android.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 存放Sp的类
 * Created by Administrator on 2017/6/15.
 */

public class SpUtils {

	private static SharedPreferences sp;

	public static void putString(Context context, String key, String values) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putString(key, values).commit();
	}

	public static String getString(Context context, String key, String values) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getString(key,values);
	}
}

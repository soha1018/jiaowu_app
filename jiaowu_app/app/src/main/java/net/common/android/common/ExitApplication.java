package net.common.android.common;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

/**
 * 没用
 */
public class ExitApplication extends Application {
	private List<Activity> activityList = new LinkedList();
	private static ExitApplication instance;

	private ExitApplication() {
	}

	// 单例模式中获取唯一的ExitApplication实例
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;

	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish

	public void exit(Context context) {

		for (Activity activity : activityList) {
			activity.finish();
		}
		
		ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE );

        activityMgr.restartPackage(context.getPackageName());

		System.exit(0);

	}

}

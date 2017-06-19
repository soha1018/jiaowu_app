/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android
 * FileNmae:StartActivity.java
 */
package net.common.android;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import net.cangshengwang.android.mobile2.R;
import net.common.android.common.Constants;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.common.SystemHelper;
import net.common.android.handler.RemoteDataHandler;
import net.common.android.handler.RemoteDataHandler.Callback;
import net.common.android.model.ResponseData;
import net.common.android.ui.mystore.LoginActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * 第一个广告界面，通过旋转，缩放，淡入淡出的方式显示出来
 */
public class StartActivity extends Activity{
	
	private MyApp myApp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_view);
		initBmob();
		
		myApp = (MyApp) StartActivity.this.getApplication();
		myApp.addActivity(this);
		//start new activity

		LinearLayout iv = (LinearLayout) findViewById(R.id.iv_start);
		initAnimation(iv);


	}

	private void initBmob() {
//		Bmob.initialize(this,"3a72664134e917a809f7efac1714ad44");

		BmobConfig config =new BmobConfig.Builder(this)
		////设置appkey
		.setApplicationId("3a72664134e917a809f7efac1714ad44")
		//请求超时时间（单位为秒）：默认15s
		.setConnectTimeout(30)
		//文件分片上传时每片的大小（单位字节），默认512*1024
		.setUploadBlockSize(1024*1024)
		//文件的过期时间(单位为秒)：默认1800s
		.setFileExpiration(2500)
		.build();
		Bmob.initialize(config);
	}

	private void initAnimation(LinearLayout iv) {
		// 旋转动画
		RotateAnimation animRotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animRotate.setDuration(1000);// 动画时间
		animRotate.setFillAfter(true);// 保持动画结束状态

		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1600);
		alphaAnimation.setFillAfter(true);

		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(1600);
		scaleAnimation.setFillAfter(true);

		AnimationSet set = new AnimationSet(true);

		set.addAnimation(animRotate);
		set.addAnimation(alphaAnimation);
		set.addAnimation(scaleAnimation);

		iv.startAnimation(set);

		set.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Intent it=new Intent();
				it.setClass(StartActivity.this,LoginActivity.class);
				startActivity(it);
				StartActivity.this.finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
	}

}

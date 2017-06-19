package net.common.android.ui.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.cangshengwang.android.mobile2.R;
import net.common.android.common.Constants;
import net.common.android.common.HttpHelper;
import net.common.android.common.MyApp;
import net.common.android.common.SystemHelper;
import net.common.android.handler.RemoteDataHandler;
import net.common.android.handler.RemoteDataHandler.Callback;
import net.common.android.model.ResponseData;
import net.common.android.ui.Activity.ExercisesActivity;
import net.common.android.ui.Activity.FeedBackMessageActivity;
import net.common.android.ui.Activity.QueryStudentSignInActivity;
import net.common.android.ui.Activity.SendFeedBackActivity;
import net.common.android.ui.Activity.ReleaseExercisesActivity;
import net.common.android.ui.Activity.SendNotifiActivity;
import net.common.android.ui.Activity.StudentSignInActivity;
import net.common.android.ui.mystore.ChengjiActivity;
import net.common.android.ui.mystore.ChengjiListViewActivity;
import net.common.android.ui.mystore.LoginActivity;
import net.common.android.ui.type.RoomListViewActivity;
import net.common.android.ui.type.KechengListViewActivity;
import net.common.android.ui.type.QuesListViewActivity;
import net.common.android.ui.type.TypeListViewActivity;

import newcourse.CourseActivity;
import newcourse.CourseActivity2;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录已经主界面的以后根据学生还是老师判断显示的内容
 */
public class HomeActivity extends Activity {
	private static final String TAG = "HomeActivity";
	private LinearLayout ll_point;
	private ArrayList<View> arrayList;
	private ArrayList<ImageView> imageViews;
	private Timer timer;
	private MyApp myApp;
	private LayoutInflater HeadlayoutInflater;
	private ViewPager viewPager;
	private int i;
	private int count;
	private TextView textHomeSearch;
	private ScrollView myScrollView;
	private ListView listRecommendGoods;
	private LinearLayout linearLayoutCategory0;
	private LinearLayout linearLayoutCategory1;
	private LinearLayout linearLayoutCategory2;
	private LinearLayout linearLayoutCategory3;
	private LinearLayout linearLayoutCategory4;
	private LinearLayout linearLayoutCategory5;
	private LinearLayout linearLayoutCategory6;
	private LinearLayout linearLayoutCategory7;
	private LinearLayout linearLayoutCategory8;
	private LinearLayout linearLayoutCategory9;
	private LinearLayout linearLayoutCategory10;
	private LinearLayout linearLayoutCategory11;
	private LinearLayout circle_layout;
	private Button buttonSeeAll;
	private Button buttonClickSign;
	private Button buttonHomeLogin;
	private LinearLayout layoutstudent;
	private LinearLayout layoutstudent02;
	private LinearLayout layoutteacher;
	private LinearLayout linearLayoutHomeLogin;
	private LinearLayout layoutstudent03;
	private LinearLayout ll_qiandao;
	private LinearLayout ll_xiti;
	private LinearLayout ll_notification;
	private LinearLayout ll_student;
	private LinearLayout ll_message;
	private LinearLayout ll_student_signin;
	private LinearLayout ll_student_xiti;
	private long time;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initView();

		RegisterBroadcastReceiver();

	/*	RegisterBroadcastReceiver();

		  buttonClickSign.setOnClickListener(new OnClickListener() {

		  @Override public void onClick(View v) { if(myApp.getLoginKey() ==null
		            || myApp.getLoginKey().equals("") ||
		            myApp.getLoginKey().equals("null")){ //未登录
		            Toast.makeText(HomeActivity.this, "未登录，请先登录",
		            Toast.LENGTH_SHORT).show(); return; }else{ //已登录
		            clickToSign(); } } });
		 */
		/**
		 * buttonHomeLogin.setOnClickListener(new OnClickListener() {
		 *
		 * @Override public void onClick(View v) { Intent intent = new
		 *           Intent(HomeActivity.this, LoginActivity.class);
		 *           HomeActivity.this.startActivity(intent); } });
		 *
		 *           linearLayoutHomeLogin.setOnClickListener(new
		 *           OnClickListener() {
		 * @Override public void onClick(View v) { Intent intent = new
		 *           Intent(HomeActivity.this, LoginActivity.class);
		 *           HomeActivity.this.startActivity(intent); } });
		 **/

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i(TAG, "onKeyDown: ");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Log.i(TAG, "onKeyDown: 按下了返回键");
			exit();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		if ((System.currentTimeMillis() - time) > 2000) {
			Toast.makeText(myApp, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			time = System.currentTimeMillis();
		} else {
			finish();
		}
	}

	private void initView() {
		myApp = (MyApp) HomeActivity.this.getApplication();

		textHomeSearch = (TextView) findViewById(R.id.textHomeSearch);
		myScrollView = (ScrollView) findViewById(R.id.myScrollView);
		linearLayoutCategory0 = (LinearLayout) findViewById(R.id.linearLayoutCategory0);
		linearLayoutCategory1 = (LinearLayout) findViewById(R.id.linearLayoutCategory1);
		linearLayoutCategory2 = (LinearLayout) findViewById(R.id.linearLayoutCategory2);
		linearLayoutCategory3 = (LinearLayout) findViewById(R.id.linearLayoutCategory3);
		linearLayoutCategory4 = (LinearLayout) findViewById(R.id.linearLayoutCategory4);
		linearLayoutCategory5 = (LinearLayout) findViewById(R.id.linearLayoutCategory5);
		linearLayoutCategory6 = (LinearLayout) findViewById(R.id.linearLayoutCategory6);
		linearLayoutCategory7 = (LinearLayout) findViewById(R.id.linearLayoutCategory7);
		linearLayoutCategory8 = (LinearLayout) findViewById(R.id.linearLayoutCategory8);
		linearLayoutCategory9 = (LinearLayout) findViewById(R.id.linearLayoutCategory9);
		linearLayoutCategory10 = (LinearLayout) findViewById(R.id.linearLayoutCategory10);
		linearLayoutCategory11 = (LinearLayout) findViewById(R.id.linearLayoutCategory11);
		linearLayoutHomeLogin = (LinearLayout) findViewById(R.id.linearLayoutHomeLogin);
		ll_qiandao = (LinearLayout) findViewById(R.id.ll_qiandao);
		ll_xiti = (LinearLayout) findViewById(R.id.ll_xiti);
		ll_notification = (LinearLayout) findViewById(R.id.ll_notification);
		ll_student_signin = (LinearLayout) findViewById(R.id.ll_student_signin);
		ll_student_xiti = (LinearLayout) findViewById(R.id.ll_student_xiti);
		ll_message = (LinearLayout) findViewById(R.id.ll_message);

		// loadingHomeData();

		layoutstudent = (LinearLayout) findViewById(R.id.layoutstudent);
		layoutteacher = (LinearLayout) findViewById(R.id.layoutteacher);
		layoutstudent02 = (LinearLayout) findViewById(R.id.layoutstudent02);
		layoutstudent03 = (LinearLayout) findViewById(R.id.layoutstudent03);
		ll_student = (LinearLayout) findViewById(R.id.ll_student);

		if (myApp.getLoginStatus().equals("0")) {
			layoutstudent.setVisibility(View.VISIBLE);
			layoutstudent02.setVisibility(View.VISIBLE);
			ll_student.setVisibility(View.VISIBLE);
			layoutstudent03.setVisibility(View.GONE);
			layoutteacher.setVisibility(View.GONE);


		} else {
			layoutteacher.setVisibility(View.VISIBLE);
			ll_student.setVisibility(View.GONE);
			layoutstudent.setVisibility(View.GONE);
			layoutstudent02.setVisibility(View.GONE);
			layoutstudent03.setVisibility(View.VISIBLE);
		}
		loadingHomeData();
		CategoryOnClickListener categoryLister = new CategoryOnClickListener();

		linearLayoutCategory0.setOnClickListener(categoryLister);
		linearLayoutCategory1.setOnClickListener(categoryLister);
		linearLayoutCategory2.setOnClickListener(categoryLister);
		linearLayoutCategory3.setOnClickListener(categoryLister);
		linearLayoutCategory4.setOnClickListener(categoryLister);
		linearLayoutCategory5.setOnClickListener(categoryLister);
		linearLayoutCategory6.setOnClickListener(categoryLister);
		linearLayoutCategory7.setOnClickListener(categoryLister);
		linearLayoutCategory8.setOnClickListener(categoryLister);
		linearLayoutCategory9.setOnClickListener(categoryLister);
		linearLayoutCategory10.setOnClickListener(categoryLister);
		linearLayoutCategory11.setOnClickListener(categoryLister);
		ll_qiandao.setOnClickListener(categoryLister);
		ll_xiti.setOnClickListener(categoryLister);
		ll_notification.setOnClickListener(categoryLister);
		ll_student_signin.setOnClickListener(categoryLister);
		ll_student_xiti.setOnClickListener(categoryLister);
		ll_message.setOnClickListener(categoryLister);
	}



	public void clickToSign() {
		// TODO Auto-generated method stub
		String url = Constants.URL_SIGN;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("key", myApp.getLoginKey());
		RemoteDataHandler.asyncPost2(url, params, new Callback() {
			@Override
			public void dataLoaded(ResponseData data) {
				if (data.getCode() == HttpStatus.SC_OK) {
					String json = data.getJson();
					JSONObject obj2;
					try {
						obj2 = new JSONObject(json);
						String flag = obj2.getString("flag");
						if (flag.equals("1")) {
							Toast.makeText(HomeActivity.this, "签到成功",
									Toast.LENGTH_SHORT).show();
							;
						} else if (flag.equals("2")) {
							Toast.makeText(HomeActivity.this, "上午已签到",
									Toast.LENGTH_SHORT).show();
							;
						} else if (flag.equals("3")) {
							Toast.makeText(HomeActivity.this, "下午已签到",
									Toast.LENGTH_SHORT).show();
							;
						} else if (flag.equals("4")) {
							Toast.makeText(HomeActivity.this, "今日已签到",
									Toast.LENGTH_SHORT).show();
							;
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}

				} else {
					Toast.makeText(HomeActivity.this, "数据加载失败，请稍后重试",
							Toast.LENGTH_SHORT).show();
					;
				}
			}
		});

	}

	public void loadingHomeData() {
		initHeadImage();
	}

	private BroadcastReceiver homeBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action == Constants.APP_BORADCASTRECEIVER) {
				linearLayoutHomeLogin.setVisibility(View.GONE);
			}
		}
	};

	public void RegisterBroadcastReceiver() {
		IntentFilter homeIntentFilter = new IntentFilter();
		homeIntentFilter.addAction(Constants.APP_BORADCASTRECEIVER);
		HomeActivity.this.registerReceiver(homeBroadcastReceiver,
				homeIntentFilter);
	}

	public class CategoryOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Bundle b = new Bundle();
			Intent intent = null;
			switch (v.getId()) {
				case R.id.linearLayoutCategory0:// 农业生物信息

					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								TypeListViewActivity.class);
						b.putInt("type", 0);
						intent.putExtras(b);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory1:// 分析软件和工具
					//课程管理
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								KechengListViewActivity.class);
						b.putInt("type", 1);
						intent.putExtras(b);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}

					break;
				case R.id.linearLayoutCategory2:// 成功案例
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								RoomListViewActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory3:// 农业生物信息
					//班级课表
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								CourseActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory4:
					//发送反馈
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								SendFeedBackActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}

					break;
				case R.id.linearLayoutCategory5:// 成功案例
					//上传成绩
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								ChengjiActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory6:// 农业生物信息

					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								QuesListViewActivity.class);
						b.putInt("type", 1);
						intent.putExtras(b);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory7:// 分析软件和工具
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								ChengjiListViewActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}

					break;
				case R.id.linearLayoutCategory8:// 成功案例
					//课表信息
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this,
								CourseActivity2.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory9:// 成功案例
					if (myApp.getLoginKey() != "") {
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory10:// 成功案例
					if (myApp.getLoginKey() != "") {
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.linearLayoutCategory11:// 成功案例
					if (myApp.getLoginKey() != "") {
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;

				case R.id.ll_qiandao:
					//查看签到
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this, QueryStudentSignInActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.ll_xiti:
					//发布习题
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this, ReleaseExercisesActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.ll_notification:
					//老师发送通知
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this, SendNotifiActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;

				case R.id.ll_student_signin:
					//学生的签到
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this, StudentSignInActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.ll_student_xiti:
					//查看习题
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this, ExercisesActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
				case R.id.ll_message:
					//学生的消息
					if (myApp.getLoginKey() != "") {
						intent = new Intent(HomeActivity.this, FeedBackMessageActivity.class);
					} else {
						intent = new Intent(HomeActivity.this, LoginActivity.class);
					}
					break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	public void initHeadImage() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		ll_point = (LinearLayout) findViewById(R.id.ll_point);
		initPagerChild();
		int sw = SystemHelper.getScreenInfo(HomeActivity.this).widthPixels;
		int sh = SystemHelper.getScreenInfo(HomeActivity.this).heightPixels;
		int h = 250;
		RelativeLayout.LayoutParams childLinerLayoutParames = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, h);
		viewPager.setLayoutParams(childLinerLayoutParames);

		viewPager.setAdapter(new ViewPagerAdapter(arrayList));
		draw_Point(0);// 默认首次进入

		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						int index = viewPager.getCurrentItem();
						if (index == arrayList.size() - 1)
							index = 0;
						else
							index++;
						viewPager.setCurrentItem(index);

					}
				});
			}
		}, 5000, 5000);

		/***
		 * viewpager PageChangeListener
		 */
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				draw_Point(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	public int initGetW(String result) {
		int w = 250;
		Bitmap bitmap;
		try {
			bitmap = HttpHelper.downloadBitmap(result);
			w = bitmap.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w;
	}

	public int initGetH(String result) {
		int h = 250;
		Bitmap bitmap;
		try {
			bitmap = HttpHelper.downloadBitmap(result);
			h = bitmap.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return h;
	}

	public void initPagerChild() {
		arrayList = new ArrayList<View>();
		ImageView imageView0 = new ImageView(HomeActivity.this);
		imageView0.setScaleType(ScaleType.FIT_XY);
		imageView0.setImageResource(R.drawable.banner01);

		ImageView imageView1 = new ImageView(HomeActivity.this);
		imageView1.setScaleType(ScaleType.FIT_XY);
		imageView1.setImageResource(R.drawable.banner02);

		ImageView imageView2 = new ImageView(HomeActivity.this);
		imageView2.setScaleType(ScaleType.FIT_XY);
		imageView2.setImageResource(R.drawable.banner03);

		ImageView imageView3 = new ImageView(HomeActivity.this);
		imageView3.setScaleType(ScaleType.FIT_XY);
		imageView3.setImageResource(R.drawable.banner04);


		arrayList.add(imageView0);
		arrayList.add(imageView1);
		arrayList.add(imageView2);
		arrayList.add(imageView3);
		initPoint();
	}

	public void initPoint() {
		imageViews = new ArrayList<ImageView>();
		ImageView imageView;

		for (i = 0; i < 4; i++) {
			imageView = new ImageView(this);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 10;
			layoutParams.rightMargin = 10;
			ll_point.addView(imageView, layoutParams);

			imageViews.add(imageView);
		}
	}

	/***
	 * 更新选中点
	 *
	 * @param index
	 */
	public void draw_Point(int index) {
		for (int i = 0; i < imageViews.size(); i++) {
			imageViews.get(i).setImageResource(R.drawable.point_gray);
		}
		imageViews.get(index).setImageResource(R.drawable.point_red);
	}

	/***
	 * 对图片处理
	 *
	 * @author zhangjia
	 */
	public Bitmap getBitmap(Bitmap bitmap, int width) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scale = (float) width / w;
		// 保证图片不变形.
		matrix.postScale(scale, scale);
		// w,h是原图的属性.
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	public class ViewPagerAdapter extends PagerAdapter {
		// 界面列表
		private List<View> views;

		public ViewPagerAdapter(List<View> views) {
			this.views = views;
		}

		// 销毁arg1位置的界面
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		// 获得当前界面数
		@Override
		public int getCount() {
			if (views != null) {
				// 返回一个比较大的数字
				return views.size();
			}
			return 0;
		}

		// 初始化arg1位置的界面
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1));
			return views.get(arg1);
		}

		// 判断是否由对象生成界面
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return (arg0 == arg1);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		count = 1;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (homeBroadcastReceiver != null) {
			unregisterReceiver(homeBroadcastReceiver);
		}
	}

}

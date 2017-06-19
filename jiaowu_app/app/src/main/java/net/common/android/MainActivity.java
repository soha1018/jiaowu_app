/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android
 * FileNmae:MainActivity.java
 */
package net.common.android;


import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import net.cangshengwang.android.mobile2.R;
import net.common.android.common.Constants;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.common.SystemHelper;
import net.common.android.ui.home.HomeActivity;
import net.common.android.ui.mystore.MyStoreActivity;
import net.common.android.ui.type.KechengListViewActivity;
import net.common.android.ui.widget.MyOutDialog;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Tab 底部菜单处理
 *
 * @author KingKong·HE
 * @Time 2014-1-6 上午11:24:17
 */
public class MainActivity extends TabActivity {
	/**
	 * tab标签名
	 */
	public final static String TAB_TAG_HOME = "home";
	public final static String TAB_TAG_TYPE = "type";
	public final static String TAB_TAG_MYSTORE = "mystore";
	public final static String TAB_TAG_CART = "cart";
	private int eventsize;

	private TabHost tabHost;

	/**
	 * 启动每个操作项的Intent
	 */
	private Intent homeIntent;
	private Intent typeIntent;
	private Intent mystoreIntent;
	private Intent cartIntent;

	/**
	 * 界面上的各个单选按钮
	 */
	private RadioButton btn_home;
	private RadioButton btn_type;
	private RadioButton btn_mystore;
	private RadioButton btn_cart;
	private static final int NOTIFICATION_FLAG = 1;
	private MyApp myApp;
	private int oldButton;
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myApp = (MyApp) MainActivity.this.getApplication();
		myApp.addActivity(this);


		if (!myApp.isIsCheckLogin()) {
			myApp.setLoginKey("");
		}

		homeIntent = new Intent(this, HomeActivity.class);
		mystoreIntent = new Intent(this, MyStoreActivity.class);

		tabHost = this.getTabHost();
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_HOME).setIndicator(TAB_TAG_HOME).setContent(homeIntent));
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_TYPE).setIndicator(TAB_TAG_TYPE).setContent(typeIntent));
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_MYSTORE).setIndicator(TAB_TAG_MYSTORE).setContent(mystoreIntent));
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_CART).setIndicator(TAB_TAG_CART).setContent(cartIntent));

		////////////////////// find View ////////////////////////////
		btn_home = (RadioButton) this.findViewById(R.id.main_tab_home);
		btn_type = (RadioButton) this.findViewById(R.id.main_tab_type);
		btn_mystore = (RadioButton) this.findViewById(R.id.main_tab_mystore);
		btn_cart = (RadioButton) this.findViewById(R.id.main_tab_cart);

		MyRadioButtonClickListener listener = new MyRadioButtonClickListener();
		btn_home.setOnClickListener(listener);
		btn_type.setOnClickListener(listener);
		btn_mystore.setOnClickListener(listener);
		btn_cart.setOnClickListener(listener);

		oldButton = R.id.main_tab_home;
		myApp.setTabHost(tabHost);
		myApp.setMyStoreButton(btn_mystore);
//		myApp.setTypeButton(btn_type);
		myApp.setCartButton(btn_cart);
		myApp.setHomeButton(btn_home);
	/*	if(!myApp.getLoginKey().equals("")){
			loadingGoodsListData();
			
		    // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。  
	        NotificationManager manager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);  
	        
	        // 创建一个PendingIntent，和Intent类似，不同的是由于不是马上调用，需要在下拉状态条出发的activity，所以采用的是PendingIntent,即点击Notification跳转启动到哪个Activity  
	        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,  
	                new Intent(this, MainActivity.class), 0);  
	        // 下面需兼容Android 2.x版本是的处理方式  
	        // Notification notify1 = new Notification(R.drawable.message,  
	        // "TickerText:" + "您有新短消息，请注意查收！", System.currentTimeMillis());  
	        Notification notify1 = new Notification();  
	        notify1.icon = R.drawable.ic_launcher_wish;  
	        notify1.tickerText = "检测到你有"+eventsize+"件备忘需要处理，请及时查看";  
	        notify1.when = System.currentTimeMillis();  
	        notify1.setLatestEventInfo(this, "备忘录提醒通知",  
	        		 "检测到你有"+eventsize+"件备忘需要处理，请及时查看", pendingIntent);  
	        notify1.number = 1;  
	        notify1.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。  
	        // 通过通知管理器来发起通知。如果id不同，则每click，在statu那里增加一个提示  
	        manager.notify(NOTIFICATION_FLAG, notify1);  
		}*/

	}


	class MyRadioButtonClickListener implements View.OnClickListener {
		public void onClick(View v) {
			RadioButton btn = (RadioButton) v;
			switch (btn.getId()) {
				case R.id.main_tab_home:
					oldButton = R.id.main_tab_home;
					tabHost.setCurrentTabByTag(TAB_TAG_HOME);
					break;
				case R.id.main_tab_type:
					oldButton = R.id.main_tab_type;
					tabHost.setCurrentTabByTag(TAB_TAG_TYPE);
					break;
				case R.id.main_tab_mystore:

					tabHost.setCurrentTabByTag(TAB_TAG_MYSTORE);

					break;
				case R.id.main_tab_cart:
					tabHost.setCurrentTabByTag(TAB_TAG_CART);
					break;
			}
		}
	}


}

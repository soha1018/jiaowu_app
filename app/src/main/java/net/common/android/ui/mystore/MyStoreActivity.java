package net.common.android.ui.mystore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import net.cangshengwang.android.mobile2.R;
import net.common.android.MainActivity;
import net.common.android.adapter.SubmenuListViewAdapter;
import net.common.android.common.Constants;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.common.MySrcAsynaTask;
import net.common.android.handler.RemoteDataHandler;
import net.common.android.handler.RemoteDataHandler.Callback;
import net.common.android.model.Login;
import net.common.android.model.MyStore;
import net.common.android.model.ResponseData;
import net.common.android.ui.custom.MyListView;
import net.common.android.ui.home.HomeActivity;
import net.common.android.ui.more.AboutActivity;
import net.common.android.ui.type.KechengListViewActivity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 没用
 */
public class MyStoreActivity extends Activity {

	private MyListView mystoreListView;
	private ImageView imageMyAvator;
	private TextView textMyUserName;
	private TextView textMyPoint;
	private TextView textMyPredepoit;
	private SubmenuListViewAdapter adapter;
	private MyApp myApp;
	private Button buttonLoingOut;
	private Button buttonRegistered;
	private LinearLayout linearLayoutYesLogin;
	private LinearLayout linearLayoutNOLogin;
	private Button buttonLoginSubmit;
	private ScrollView SView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_mystore);

		myApp = (MyApp) MyStoreActivity.this.getApplication();

		mystoreListView = (MyListView) findViewById(R.id.mystoreListView);
		imageMyAvator = (ImageView) findViewById(R.id.imageMyAvator);
		textMyUserName = (TextView) findViewById(R.id.textMyUserName);
		textMyPoint = (TextView) findViewById(R.id.textMyPoint);
		textMyPredepoit = (TextView) findViewById(R.id.textMyPredepoit);
		buttonLoingOut = (Button) findViewById(R.id.buttonLoingOut);
		linearLayoutYesLogin = (LinearLayout) findViewById(R.id.linearLayoutYesLogin);
		linearLayoutNOLogin = (LinearLayout) findViewById(R.id.linearLayoutNOLogin);
		buttonLoginSubmit = (Button) findViewById(R.id.buttonLoginSubmit);
		buttonRegistered = (Button) findViewById(R.id.buttonRegistered);
		SView = (ScrollView) findViewById(R.id.SView);

		// if(myApp.getLoginKey() ==null || myApp.getLoginKey().equals("") ||
		// myApp.getLoginKey().equals("null")){
		// Intent intent= new Intent(MyStoreActivity.this,LoginActivity.class);
		// MyStoreActivity.this.startActivity(intent);
		// }

		// Toast.makeText(MyStoreActivity.this, "此页显示店铺列表信息",
		// Toast.LENGTH_LONG).show();

		loadingMyUserData();

		mystoreListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long id) {
				Intent intent = null;
				switch ((int) id) {
				case R.drawable.mystore_order:
					if (myApp.getLoginKey() == null
							|| myApp.getLoginKey().equals("")
							|| myApp.getLoginKey().equals("null")) {
						intent = new Intent(MyStoreActivity.this,
								LoginActivity.class);
						MyStoreActivity.this.startActivity(intent);
						return;
					}
					intent = new Intent(MyStoreActivity.this,
							MainActivity.class);
					break;
				case R.drawable.mystore_address:
					if (myApp.getLoginKey() == null
							|| myApp.getLoginKey().equals("")
							|| myApp.getLoginKey().equals("null")) {
						intent = new Intent(MyStoreActivity.this,
								LoginActivity.class);
						MyStoreActivity.this.startActivity(intent);
						return;
					}
					intent = new Intent(MyStoreActivity.this, TBActivity.class);
					break;
				/*
				 * case R.drawable.mystore_collect: if(myApp.getLoginKey()
				 * ==null || myApp.getLoginKey().equals("") ||
				 * myApp.getLoginKey().equals("null")){ intent= new
				 * Intent(MyStoreActivity.this,LoginActivity.class);
				 * MyStoreActivity.this.startActivity(intent); return ; } intent
				 * = new
				 * Intent(MyStoreActivity.this,MeetBookListViewActivity.class);
				 * break;
				 */
				/*	*/
				/*
				 * case R.drawable.mystore_history: if(myApp.getLoginKey()
				 * ==null || myApp.getLoginKey().equals("") ||
				 * myApp.getLoginKey().equals("null")){ intent= new
				 * Intent(MyStoreActivity.this,LoginActivity.class);
				 * MyStoreActivity.this.startActivity(intent); return ; } intent
				 * = new Intent(MyStoreActivity.this,MessageAddActivity.class);
				 * break;
				 */

				default:
					break;
				}
				if (intent != null) {
					MyStoreActivity.this.startActivity(intent);
				}
			}
		});
		buttonLoingOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loginOut();
			}
		});
		buttonLoginSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyStoreActivity.this,
						LoginActivity.class);
				MyStoreActivity.this.startActivity(intent);
			}
		});
		buttonRegistered.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyStoreActivity.this,
						RegisteredActivity.class);
				MyStoreActivity.this.startActivity(intent);
			}
		});
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
			}
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		registerBoradcastReceiver();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Constants.APP_BORADCASTRECEIVER)) {
				loadingMyUserData();
			}
		}
	};

	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constants.APP_BORADCASTRECEIVER);
		// 注册广播
		MyStoreActivity.this.registerReceiver(mBroadcastReceiver,
				myIntentFilter);
	}

	public void loginOut() {
		String url = Constants.URL_LOGIN_OUT;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("key", myApp.getLoginKey());
		params.put("username", myApp.getLoginName());
		params.put("client", "android");
		myApp.setLoginKey("");
		myApp.setLoginName("");
		// myApp.getTabHost().setCurrentTab(1);
		// myApp.getTypeButton().setChecked(true);

		linearLayoutNOLogin.setVisibility(View.VISIBLE);
		linearLayoutYesLogin.setVisibility(View.GONE);
		buttonLoingOut.setVisibility(View.GONE);

		Toast.makeText(MyStoreActivity.this, "成功退出！", Toast.LENGTH_SHORT)
				.show();

	}

	public void loadingMyUserData() {
		String key = myApp.getLoginKey();

		if (key != null && key != "") {
			textMyUserName.setText(myApp.getLoginName());

			textMyPoint.setText("0");
			textMyPredepoit.setText("0");
		} else {
			linearLayoutNOLogin.setVisibility(View.VISIBLE);
			linearLayoutYesLogin.setVisibility(View.GONE);
			buttonLoingOut.setVisibility(View.GONE);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		SView.smoothScrollTo(0, 20);
		if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("")
				|| myApp.getLoginKey().equals("null")) {
			linearLayoutNOLogin.setVisibility(View.VISIBLE);
			linearLayoutYesLogin.setVisibility(View.GONE);
			buttonLoingOut.setVisibility(View.GONE);
		} else {
			linearLayoutYesLogin.setVisibility(View.VISIBLE);
			linearLayoutNOLogin.setVisibility(View.GONE);
			buttonLoingOut.setVisibility(View.VISIBLE);
		}

		ArrayList<HashMap<String, Object>> datas = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put(SubmenuListViewAdapter.TAG_ITEM_TEXT,
				this.getString(R.string.myorder));
		map1.put(SubmenuListViewAdapter.TAG_ITEM_ICON,
				Integer.valueOf(R.drawable.mystore_order));
		datas.add(map1);

		/*
		 * HashMap<String, Object> map3 = new HashMap<String, Object>();
		 * map3.put(SubmenuListViewAdapter.TAG_ITEM_TEXT,
		 * this.getString(R.string.mygoodssearch));
		 * map3.put(SubmenuListViewAdapter.TAG_ITEM_ICON,
		 * Integer.valueOf(R.drawable.mystore_collect)); datas.add(map3);
		 */
	
		if (myApp.getLoginStatus().equals("1")) {
			HashMap<String, Object> map4 = new HashMap<String, Object>();
			map4.put(SubmenuListViewAdapter.TAG_ITEM_TEXT,
					this.getString(R.string.mycart));
			map4.put(SubmenuListViewAdapter.TAG_ITEM_ICON,
					Integer.valueOf(R.drawable.mystore_address));
			datas.add(map4);
		
		}

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * HashMap<String, Object> map5 = new HashMap<String, Object>();
		 * map5.put(SubmenuListViewAdapter.TAG_ITEM_TEXT,
		 * this.getString(R.string.mystore_collection_text));
		 * map5.put(SubmenuListViewAdapter.TAG_ITEM_ICON,
		 * Integer.valueOf(R.drawable.mystore_history)); datas.add(map5);
		 */

		adapter = new SubmenuListViewAdapter(MyStoreActivity.this, datas);
		mystoreListView.setAdapter(adapter);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			myApp.getTabHost().setCurrentTab(0);
			myApp.getHomeButton().setChecked(true);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}

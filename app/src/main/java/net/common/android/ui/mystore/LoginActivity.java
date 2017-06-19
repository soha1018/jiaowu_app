/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.ui.mystore
 * FileNmae:LoginActivity.java
 */
package net.common.android.ui.mystore;


import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.NoticeBean;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.model.Login;
import net.common.android.ui.Activity.StudentMessageActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * 登陆的界面
 */
public class LoginActivity extends Activity {
	private static final String TAG = "LoginActivity";
	private EditText editLoginName;
	private EditText editLoginPassWord;
	private Button buttonLoginSubmit;
	private CheckBox checkboxMyAuto;
	private MyApp myApp;
	private Button buttonBack;
	private Button buttonRegister;
	private Button buttonFindpwd;
	private String tabFlag;

	private RadioGroup status = null;
	private RadioButton student = null;
	private RadioButton teacher = null;
	private int statusnum;
	private Button btn_network;
	private EditText et_net;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);

		initView();

		this.status
				.setOnCheckedChangeListener(new OnCheckedChangeListenerImp());

		// checkboxMyAuto.setChecked(true);

		buttonLoginSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String loginName = editLoginName.getText().toString();
				String loginPassword = editLoginPassWord.getText().toString();
				boolean isCheck = checkboxMyAuto.isChecked();

				if (TextUtils.isEmpty(loginName)) {
					Toast.makeText(myApp, "请输入用户名", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(loginPassword)) {
					Toast.makeText(myApp, "请输入密码", Toast.LENGTH_SHORT).show();
					return;
				}


				infoLoginCheck(loginName, loginPassword, isCheck);
			}
		});

		buttonBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginActivity.this.finish();

			}
		});
		//注册账号
		buttonRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						RegisteredActivity.class);
				LoginActivity.this.startActivity(intent);
				LoginActivity.this.finish();
			}
		});
/*
		buttonFindpwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						FindPwdActivity.class);
				LoginActivity.this.startActivity(intent);
				LoginActivity.this.finish();

			}
		});*/
		//初始化数据
		initData();
	}

	private void initData() {

	}

	/**
	 * 初始化View
	 */
	private void initView() {
		myApp = (MyApp) LoginActivity.this.getApplication();
		tabFlag = LoginActivity.this.getIntent().getStringExtra("tabFlag");
		editLoginName = (EditText) findViewById(R.id.editLoginName);
		editLoginPassWord = (EditText) findViewById(R.id.editLoginPassWord);
		buttonLoginSubmit = (Button) findViewById(R.id.buttonLoginSubmit);
		checkboxMyAuto = (CheckBox) findViewById(R.id.checkboxMyAuto);
		buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonRegister = (Button) findViewById(R.id.buttonRegister);
		myApp.setIsCheckLogin(true);
		checkboxMyAuto.setChecked(myApp.isIsCheckLogin());


		status = (RadioGroup) findViewById(R.id.status);
		student = (RadioButton) findViewById(R.id.student);
		teacher = (RadioButton) findViewById(R.id.teacher);


	}

	private class OnCheckedChangeListenerImp implements OnCheckedChangeListener {
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if (LoginActivity.this.student.getId() == checkedId) {
				statusnum = 0;
			} else if (LoginActivity.this.teacher.getId() == checkedId) {
				statusnum = 1;
			}
		}
	}

	public void infoLoginCheck(String loginName, String loginPassword,
							   final boolean isCheck) {
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("正在登陆");
		dialog.setMessage("登录中，请稍等。。。");
		dialog.show();
		String url = HttpUtil.URL_LOGIN;
		String query = "";
		query += "user.username=" + loginName + "&";
		query += "user.password=" + loginPassword;

		url += query;

		Log.i(TAG, "infoLoginCheck: url地址是：" + url);
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Log.i(TAG, "onSuccess: 结果是：" + responseInfo.result);
				String result = responseInfo.result;
				Login login = Login.newInstanceList(result);
				if (login == null) {
					Toast.makeText(myApp, "没有这个用户，请先注册", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
					return;
				}
				myApp.setLoginKey(login.getKey());
				myApp.setLoginName(login.getUsername());
				myApp.setLoginStatus(login.getStatus());
				myApp.setLoginStudetno(login.getStudentno());
				myApp.setBanji(login.getBanji());
				Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

				dialog.dismiss();

				Log.i(TAG, "onSuccess: 职位是：" + login.getStatus());
				if (login.getStatus().equals("0")) {
					//监听数据库发生改变的时候
					listenerDatabases();
				}
				//跳转界面
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
						InputMethodManager.HIDE_NOT_ALWAYS);
				Intent intent = new Intent(LoginActivity.this,
						MyStoreActivity.class);
				LoginActivity.this.startActivity(intent);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Log.i(TAG, "onFailure: " + msg);
				dialog.dismiss();
				Toast.makeText(myApp, "错误信息：" + msg, Toast.LENGTH_SHORT).show();
			}
		});
	}


	/**
	 * 监听数据库发生改变的时候
	 */
	private void listenerDatabases() {
		Log.i(TAG, "listenerDatabases: 进来了数据");

		BmobQuery<NoticeBean> query = new BmobQuery<NoticeBean>();
		query.findObjects(new FindListener<NoticeBean>() {
			@Override
			public void done(List<NoticeBean> list, BmobException e) {
				NoticeBean bean = list.get(0);
				if (bean!=null && bean.status == 0){
					Log.i(TAG, "listenerDatabases: " + bean.name);
					NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
					Notification.Builder builder = new Notification.Builder(getApplicationContext());
					builder.setTicker("老师发来了通知");
					builder.setContentTitle("发送人：" + bean.name);
					builder.setContentText("内容：" + bean.content);
					builder.setSmallIcon(R.drawable.ic_launcher);
					builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS);
					Intent intent = new Intent(getApplicationContext(), StudentMessageActivity.class);
					intent.putExtra("bean", bean);
					PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123
							, intent, PendingIntent.FLAG_CANCEL_CURRENT);
					builder.setContentIntent(pendingIntent);
					Notification notification = builder.getNotification();
					nm.notify(124, notification);
				}
			}
		});

	}
}

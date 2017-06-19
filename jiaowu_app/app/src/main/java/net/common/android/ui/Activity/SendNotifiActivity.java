package net.common.android.ui.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.NoticeBean;
import net.common.android.Utils.MyUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 发送通知的界面
 * Created by Administrator on 2017/6/14.
 */

public class SendNotifiActivity extends Activity implements View.OnClickListener {
	/**
	 * 请输入标题
	 */
	private EditText et_nitifi_title;
	/**
	 * 请输入内容
	 */
	private EditText et_nitifi_content;
	/**
	 * 请输入班级
	 */
	private EditText et_nitifi_grade;
	/**
	 * 发送通知
	 */
	private Button btn_nitifi;
	/**
	 * 2017年6月14日12:18:38
	 */
	private TextView tv_notifi_time;
	/**
	 * 请输入您的名字
	 */
	private EditText et_nitifi_user;
	private Spinner sp_send;
	private String[] timeArray;
	private Handler handler = new Handler();
	private long time;
	private String title;
	private String content;
	private String user;
	private String grade;
	private int timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_notifi);
		initView();
		initData();


	}

	private void initData() {
		//设置当前的时间
		tv_notifi_time.setText(MyUtils.getCurrentTime());

		timeArray = new String[]{"立即发送","5分钟后","一小时后","两小时后"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeArray);
		sp_send.setAdapter(adapter);

		sp_send.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (position){
					case 0:
						timer = 0;
						time =System.currentTimeMillis() + 200;
						break;
					case 1:
						timer = 300000;
						time = System.currentTimeMillis() + 300000;
						break;
					case 2:
						timer = 3600000;
						time = System.currentTimeMillis() + 3600000;
						break;
					case 3:
						timer = 7200000;
						time = System.currentTimeMillis() + 7200000;
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	private void initView() {
		et_nitifi_title = (EditText) findViewById(R.id.et_nitifi_title);
		et_nitifi_content = (EditText) findViewById(R.id.et_nitifi_content);
		et_nitifi_grade = (EditText) findViewById(R.id.et_nitifi_grade);
		btn_nitifi = (Button) findViewById(R.id.btn_nitifi);
		btn_nitifi.setOnClickListener(this);
		tv_notifi_time = (TextView) findViewById(R.id.tv_notifi_time);
		et_nitifi_user = (EditText) findViewById(R.id.et_nitifi_user);
		sp_send = (Spinner) findViewById(R.id.sp_send);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_nitifi:
				//发送消息
				senMessage();
				break;
		}
	}

	/**
	 * 给学生发送通知
	 */
	private void senMessage() {
		isNull();
	}

	private void isNull() {
		title = et_nitifi_title.getText().toString();
		content = et_nitifi_content.getText().toString();
		user = et_nitifi_user.getText().toString();
		grade = et_nitifi_grade.getText().toString();
		if (TextUtils.isEmpty(title)) {
			Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(content)) {
			Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(user)) {
			Toast.makeText(this, "请输入您的姓名", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(grade)) {
			Toast.makeText(this, "请输入班级", Toast.LENGTH_SHORT).show();
			return;
		}


		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				final ProgressDialog dialog = new ProgressDialog(SendNotifiActivity.this);
				dialog.setTitle("发送通知");
				dialog.setMessage("正在发送通知，请稍等。。。");
				NoticeBean bean = new NoticeBean();
				bean.name = user;
				bean.status = 0;
				bean.title = title;
				bean.content = content;
				bean.grade = Integer.parseInt(grade);
				bean.time = time;
				bean.save(new SaveListener<String>() {
					@Override
					public void done(String s, BmobException e) {
						if (e==null){
							Toast.makeText(SendNotifiActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
							dialog.dismiss();
							finish();
						}else {
							Toast.makeText(SendNotifiActivity.this, "发送失败，请重新尝试", Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						}
					}
				});

			}
		}, timer);
	}
}

package net.common.android.ui.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.FeedBackBean;
import net.common.android.Utils.MyUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 发送反馈的界面
 * Created by Administrator on 2017/6/14.
 */

public class SendFeedBackActivity extends Activity implements View.OnClickListener {
	/**
	 * 请输入标题
	 */
	private EditText et_feed_title;
	/**
	 * 请输入至少10个字以上的描述
	 */
	private EditText et_nitifi_content;
	/**
	 * 2017年6月14日14:43:31
	 */
	private TextView tv3;
	/**
	 * 请输入标题
	 */
	private EditText et_feed_user;
	/**
	 * 提交
	 */
	private Button btn_feed_commit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

		initView();

	}

	private void initView() {
		et_feed_title = (EditText) findViewById(R.id.et_feed_title);
		et_nitifi_content = (EditText) findViewById(R.id.et_nitifi_content);
		tv3 = (TextView) findViewById(R.id.tv3);
		et_feed_user = (EditText) findViewById(R.id.et_feed_user);
		btn_feed_commit = (Button) findViewById(R.id.btn_feed_commit);
		btn_feed_commit.setOnClickListener(this);

		tv3.setText(MyUtils.getCurrentTime());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_feed_commit:
				sendFeedBack();
				break;
		}
	}

	/**
	 * 发送反馈
	 */
	private void sendFeedBack() {
		String title = et_feed_title.getText().toString();
		String content = et_nitifi_content.getText().toString();
		String user = et_feed_user.getText().toString();
		if (TextUtils.isEmpty(title)) {
			Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
		}
		if (TextUtils.isEmpty(content)) {
			Toast.makeText(this, "请输入您要反馈的内容", Toast.LENGTH_SHORT).show();
		}
		if (TextUtils.isEmpty(user)) {
			Toast.makeText(this, "请输入您要反馈的人", Toast.LENGTH_SHORT).show();
		}

		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("发送反馈");
		dialog.setMessage("正在发送反馈，请稍等。。。");
		dialog.show();

		FeedBackBean bean = new FeedBackBean();
		bean.name = user;
		bean.content = content;
		bean.title = title;
		bean.time = System.currentTimeMillis();
		bean.save(new SaveListener<String>() {
			@Override
			public void done(String s, BmobException e) {
				if (e == null) {
					dialog.dismiss();
					Toast.makeText(SendFeedBackActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
					finish();
				} else {
					dialog.dismiss();
					Toast.makeText(SendFeedBackActivity.this, "发布失败,请重试", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}

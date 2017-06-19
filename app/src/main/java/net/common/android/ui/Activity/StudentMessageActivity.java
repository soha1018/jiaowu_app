package net.common.android.ui.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.NoticeBean;
import net.common.android.Utils.MyUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 学生接收消息的界面
 * Created by Administrator on 2017/6/14.
 */

public class StudentMessageActivity extends Activity {


	/**
	 * 通知
	 */
	private TextView text_title;
	/**
	 * 发布者:习大大
	 */
	private TextView text_user;
	/**
	 * 发布内容：今天下午开会请所有学生到阶梯教室集合
	 */
	private TextView text_content;
	/**
	 * 发布时间：2017年6月14日13:29:49
	 */
	private TextView text_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_messgae_item);
		initView();

		initAdapter();

	}

	private void initAdapter() {
		NoticeBean bean = (NoticeBean) getIntent().getSerializableExtra("bean");

		if (bean != null) {
			bean.setValue("status",1);
			bean.update(new UpdateListener() {
				@Override
				public void done(BmobException e) {

				}
			});
			text_title.setText(bean.title);
			text_content.setText(bean.content);
			text_user.setText(bean.name);
			text_time.setText(MyUtils.long2String(bean.time));
		}
	}

	private void initView() {
		text_title = (TextView) findViewById(R.id.text_title);
		text_user = (TextView) findViewById(R.id.text_user);
		text_content = (TextView) findViewById(R.id.text_content);
		text_time = (TextView) findViewById(R.id.text_time);
	}
}

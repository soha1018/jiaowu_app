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
import net.common.android.Bean.ExerciserBean;
import net.common.android.Utils.MyUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 发布习题的界面
 * Created by Administrator on 2017/6/13.
 */

public class ReleaseExercisesActivity extends Activity implements View.OnClickListener {
	/**
	 * 发布习题
	 */
	private TextView title;
	/**
	 * 请输入标题
	 */
	private EditText et_exercises_title;
	/**
	 * 请输入内容
	 */
	private EditText et_exercises_content;
	/**
	 * 当前时间：2017年6月13日15:09:56
	 */
	private TextView tv_time;
	/**
	 * 请输入您的姓名
	 */
	private EditText et_exercises_name;
	/**
	 * 发布
	 */
	private Button btn_release;
	private EditText et_exercises_grade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release_exercises);
		initView();
	}

	private void initView() {
		title = (TextView) findViewById(R.id.title);
		et_exercises_title = (EditText) findViewById(R.id.et_exercises_title);
		et_exercises_content = (EditText) findViewById(R.id.et_exercises_content);
		et_exercises_grade = (EditText) findViewById(R.id.et_exercises_grade);
		tv_time = (TextView) findViewById(R.id.tv_time);
		et_exercises_name = (EditText) findViewById(R.id.et_exercises_name);
		btn_release = (Button) findViewById(R.id.btn_release);
		btn_release.setOnClickListener(this);
		//设置当前的时间
		tv_time.setText(MyUtils.getCurrentTime());

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_release:
				release();
				break;
		}
	}

	private void release() {
		String title = et_exercises_title.getText().toString();
		String content = et_exercises_content.getText().toString();
		String name = et_exercises_name.getText().toString();
		String grade = et_exercises_grade.getText().toString();

		if (TextUtils.isEmpty(title)) {
			Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(content)) {
			Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(this, "请输入您的姓名", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(grade)) {
			Toast.makeText(this, "请输入班级", Toast.LENGTH_SHORT).show();
			return;
		}

		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("发布习题");
		dialog.setMessage("发布中，请稍等。。。");
		dialog.show();
		ExerciserBean bean = new ExerciserBean();
		bean.title = title;
		bean.name = name;
		bean.content = content;
		bean.time = System.currentTimeMillis();
		bean.grade = Integer.valueOf(grade);
		bean.save(new SaveListener<String>() {
			@Override
			public void done(String s, BmobException e) {
				if (e == null) {
					dialog.dismiss();
					Toast.makeText(ReleaseExercisesActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
					finish();
				} else {
					dialog.dismiss();
					Toast.makeText(ReleaseExercisesActivity.this, "发布失败，错误信息："+s, Toast.LENGTH_SHORT).show();
					finish();
				}
			}
		});

	}
}

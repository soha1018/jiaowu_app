package net.common.android.ui.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.ExerciserBean;

/**
 * 提交作业的界面
 * Created by Administrator on 2017/6/13.
 */

public class TaskActivity extends Activity  {
	/**
	 * 标题
	 */
	private TextView tv_task_title;
	/**
	 * 内容碍事打算打打打打算的第三大
	 */
	private TextView tv_task_content;
	/**
	 * 请输入答案
	 */
	private EditText et_task;
	/**
	 * 提交作业
	 */
	private Button btn_commit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		initView();
		initData();

	}

	private void initData() {
		ExerciserBean bean = (ExerciserBean) getIntent().getSerializableExtra("list");
		if (bean!=null){
			tv_task_title.setText(bean.title);
			tv_task_content.setText("   "+bean.content);
		}

	}

	private void initView() {
		tv_task_title = (TextView) findViewById(R.id.tv_task_title);
		tv_task_content = (TextView) findViewById(R.id.tv_task_content);
	}

}

package net.common.android.ui.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.SignBean;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 学生签到的界面
 * Created by Administrator on 2017/6/13.
 */

public class StudentSignInActivity extends Activity implements View.OnClickListener {
	private ImageView iv_signin;
	/**
	 * 请输入姓名
	 */
	private EditText et_student_user;
	/**
	 * 请输入班级
	 */
	private EditText et_student_id;
	/**
	 * 签到
	 */
	private Button btn_signin;
	/**
	 * 请输入班级
	 */
	private EditText et_student_grade;
	private static final String TAG = "StudentSignInActivity";
	private Spinner sp_course;
	private String[] array;
	private int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_signin);

		initView();

	}

	private void initView() {
		iv_signin = (ImageView) findViewById(R.id.iv_signin);
		et_student_user = (EditText) findViewById(R.id.et_student_user);
		et_student_id = (EditText) findViewById(R.id.et_student_id);
		btn_signin = (Button) findViewById(R.id.btn_signin);
		btn_signin.setOnClickListener(this);
		et_student_grade = (EditText) findViewById(R.id.et_student_grade);
		sp_course = (Spinner) findViewById(R.id.sp_course);

		array = new String[]{"c语言","JAVA","C++","c#","汇编语言程序设计&实验","算法分析与设计",
		"数据库系统","操作系统","计算机网络","软件工程","信息安全","人工智能"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
		sp_course.setAdapter(adapter);

		sp_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				pos = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_signin:
				//往数据库插入数据
				insertSignin();
				break;
		}
	}

	/**
	 * 往数据库插入数据
	 */
	private void insertSignin() {
		String name = et_student_user.getText().toString().trim();
		String grade = et_student_grade.getText().toString().trim();
		String number = et_student_id.getText().toString().trim();
		if (TextUtils.isEmpty(name)){
			Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(grade)){
			Toast.makeText(this, "请输入班级", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(number)){
			Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
			return;
		}


		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("签到");
		dialog.setMessage("正在签到，请稍等。。。");
		dialog.show();

		final SignBean bean = new SignBean();
		bean.name = name;
		bean.course = array[pos];
		bean.grade = Integer.parseInt(grade);
		bean.number = Long.parseLong(number);

		bean.save(new SaveListener<String>() {
			@Override
			public void done(String s, BmobException e) {
				if (e == null) {
					dialog.dismiss();
					Toast.makeText(StudentSignInActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
					finish();
				}else {
					dialog.dismiss();
					Toast.makeText(StudentSignInActivity.this, "错误信息："+s, Toast.LENGTH_SHORT).show();
				}

			}
		});
/*		long sign = sqlEngine.insertSign(name,Integer.parseInt(grade), Integer.parseInt(number),array[pos]);
		Log.i(TAG, "insertSignin: sign的值是："+sign);
		if (sign>0){
			Toast.makeText(this, "签到成功！", Toast.LENGTH_SHORT).show();
			finish();
		}else {
			Toast.makeText(this, "今日已经签到", Toast.LENGTH_SHORT).show();
			finish();
		}*/


	}
}

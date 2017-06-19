/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.cangshengwang.android.ui.mystore
 * FileNmae:RegisteredActivity.java
 */
package net.common.android.ui.mystore;


import net.cangshengwang.android.mobile2.R;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


/**
 * 提交的界面
 */
public class AnswerActivity extends Activity {
	private EditText editTitle;
	private ImageView imageBack;
	private Button buttonSend;
	private MyApp myApp;
	private String userid;
	private String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_view4);
		myApp = (MyApp) AnswerActivity.this.getApplication();
		editTitle = (EditText) findViewById(R.id.editTitle);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		buttonSend = (Button) findViewById(R.id.buttonSend);
		id= getIntent().getExtras().getString("id");
		buttonSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String title = editTitle.getText().toString();
				if(title.equals("") || title==null){
					Toast.makeText(AnswerActivity.this, "不能为空", Toast.LENGTH_SHORT).show();;
					return ;
				}
				
				SendData(title);
			}
		});
		imageBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AnswerActivity.this.finish();
			}
		});
	}
	
	public void SendData(String title){
		String url = HttpUtil.URL_ANSWERADD;
		String query ="";
		query+="answer.answer="+title+"&";
		query+="answer.username="+myApp.getLoginName()+"&";
		query+="answer.ques_id="+id;


		
		url+=query;


		HttpUtils utils = new HttpUtils();
		utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				JSONObject obj;
				try {
					obj = new JSONObject(result);
					String arrlist = obj.optString("jsonString");
					if(arrlist!="" && !arrlist.equals("arrlist") && arrlist!=null && !arrlist.equals("[]")){

						if(arrlist.equals("1")){
							Toast.makeText(AnswerActivity.this, "恭喜，提交成功", Toast.LENGTH_SHORT).show();;
							AnswerActivity.this.finish();
						}else{
							Toast.makeText(AnswerActivity.this, "抱歉，提交失败", Toast.LENGTH_SHORT).show();;
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(myApp, "错误信息："+msg, Toast.LENGTH_SHORT).show();
			}
		});
			
			
				
		/*		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        */
		
	}

}

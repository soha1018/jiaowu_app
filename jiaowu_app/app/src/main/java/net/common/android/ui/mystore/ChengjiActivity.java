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
 * 上传成绩界面
 */
public class ChengjiActivity extends Activity {
	private EditText editStudentNo;
	private EditText editLession;
	private EditText editScore;
	private ImageView imageBack;
	private Button buttonSend;
	private MyApp myApp;
	private String userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_view);
		myApp = (MyApp) ChengjiActivity.this.getApplication();
		editStudentNo = (EditText) findViewById(R.id.editStudentNo);
		editLession = (EditText) findViewById(R.id.editLession);
		editScore = (EditText) findViewById(R.id.editScore);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		buttonSend = (Button) findViewById(R.id.buttonSend);
		buttonSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String studentno = editStudentNo.getText().toString();
				String lession = editLession.getText().toString();
				String score = editScore.getText().toString();
				if (studentno.equals("") || studentno == null) {
					Toast.makeText(ChengjiActivity.this, "学号不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (lession.equals("") || lession == null) {
					Toast.makeText(ChengjiActivity.this, "课程不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (score.equals("") || score == null) {
					Toast.makeText(ChengjiActivity.this, "成绩不能为空", Toast.LENGTH_SHORT).show();
					return;
				}

				SendData(studentno, lession, score);
			}
		});
		imageBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ChengjiActivity.this.finish();
			}
		});
	}

	public void SendData(String studentno, String lession, String score) {
		String url = HttpUtil.URL_CHENGJIADD;
		String query = "";
		query += "chengji.studentno=" + studentno + "&";
		query += "chengji.lession=" + lession + "&";
		query += "chengji.score=" + score;

	
		
		
		/*try {
			query=URLEncoder.encode(query , "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/


		url += query;
		/*HashMap<String, String> params = new HashMap<String,String>();
		params.put("username", username);
		params.put("password", password);
		params.put("sex", sex);
		params.put("city", city);
		params.put("birthday", birthday);
		params.put("signature", signature);
		params.put("nickname", nickname);*/

		HttpUtils utils = new HttpUtils();
		utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				JSONObject obj;
				try {
					obj = new JSONObject(result);
					String arrlist = obj.optString("jsonString");
//					JSONObject obj = new JSONObject(json);
					if (arrlist != "" && !arrlist.equals("arrlist") && arrlist != null && !arrlist.equals("[]")) {
//						Login login=Login.newInstanceList(arrlist);
//						myApp.setLoginKey(login.getKey());
//						myApp.setLoginName(login.getUsername());

						if (arrlist.equals("1")) {
							Toast.makeText(ChengjiActivity.this, "恭喜，数据上传成功", Toast.LENGTH_SHORT).show();
							ChengjiActivity.this.finish();
						} else {
							Toast.makeText(ChengjiActivity.this, "抱歉，数据上传失败", Toast.LENGTH_SHORT).show();
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(myApp, "错误信息：" + msg, Toast.LENGTH_SHORT).show();
			}
		});
				
		/*		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        */

	}

}

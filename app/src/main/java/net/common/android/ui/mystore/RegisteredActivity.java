/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.cangshengwang.android.ui.mystore
 * FileNmae:RegisteredActivity.java
 */
package net.common.android.ui.mystore;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;

import net.cangshengwang.android.mobile2.R;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * 注册的界面
 */
public class RegisteredActivity extends Activity {
    private EditText editUserName;
    private EditText editPassword;
    private EditText editqq;
    private EditText editphone;
    private EditText editbanji;
    private ImageView imageBack;
    private Button buttonSend;

    private RadioGroup status = null;
    private RadioButton student = null;
    private RadioButton teacher = null;
    private int statusnum;

    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_view);
        myApp = (MyApp) RegisteredActivity.this.getApplication();
        editUserName = (EditText) findViewById(R.id.editUserName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editqq = (EditText) findViewById(R.id.editqq);
        editphone = (EditText) findViewById(R.id.editphone);
        editbanji = (EditText) findViewById(R.id.editbanji);
        imageBack = (ImageView) findViewById(R.id.imageBack);
        buttonSend = (Button) findViewById(R.id.buttonSend);

        status = (RadioGroup) findViewById(R.id.status);
        student = (RadioButton) findViewById(R.id.student);
        teacher = (RadioButton) findViewById(R.id.teacher);

        this.status
                .setOnCheckedChangeListener(new OnCheckedChangeListenerImp());

        buttonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUserName.getText().toString();
                String password = editPassword.getText().toString();
                String qq = editqq.getText().toString();
                String banji = editbanji.getText().toString();
                String phone = editphone.getText().toString();
                if (username.equals("") || username == null) {
                    Toast.makeText(RegisteredActivity.this, "用户名不能为空",
                            Toast.LENGTH_SHORT).show();
                    ;
                    return;
                }
                if (password.equals("") || password == null) {
                    Toast.makeText(RegisteredActivity.this, "密码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (banji.equals("") || banji == null) {
                    Toast.makeText(RegisteredActivity.this, "不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                SendData(username, password, qq, phone, banji);
            }
        });
        imageBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisteredActivity.this.finish();
            }
        });
    }

    private class OnCheckedChangeListenerImp implements OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (RegisteredActivity.this.student.getId() == checkedId) {
                statusnum = 0;
            } else if (RegisteredActivity.this.teacher.getId() == checkedId) {
                statusnum = 1;
            }
        }
    }

    public void SendData(String username, String password, String qq,
                         String phone, String banji) {
        String url = HttpUtil.URL_REGISTER;
        String query = "";
        query += "user.username=" + username + "&";
        query += "user.password=" + password + "&";
        query += "user.qqnum=" + qq + "&";
        query += "user.status=" + statusnum + "&";
        query += "user.banji=" + banji + "&";
        query += "user.phone=" + phone;

		/*
         * try { query=URLEncoder.encode(query , "utf-8"); } catch
		 * (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		 */

        url += query;
		/*
		 * HashMap<String, String> params = new HashMap<String,String>();
		 * params.put("username", username); params.put("password", password);
		 * params.put("sex", sex); params.put("city", city);
		 * params.put("birthday", birthday); params.put("signature", signature);
		 * params.put("nickname", nickname);
		 */

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                JSONObject obj;
                try {
                    obj = new JSONObject(result);
                    String arrlist = obj.optString("jsonString");
                    // JSONObject obj = new JSONObject(json);
                    if (!arrlist.equals("") && !arrlist.equals("arrlist")&& !arrlist.equals("[]")) {
                        // Login login=Login.newInstanceList(arrlist);
                        // myApp.setLoginKey(login.getKey());
                        // myApp.setLoginName(login.getUsername());

                        if (arrlist.equals("1")) {
                            Toast.makeText(RegisteredActivity.this, "恭喜，注册成功",
                                    Toast.LENGTH_SHORT).show();
                            ;
                            Intent intent = new Intent(RegisteredActivity.this,
                                    MyStoreActivity.class);
                            RegisteredActivity.this.startActivity(intent);
                        } else {
                            Toast.makeText(RegisteredActivity.this, "抱歉，注册失败",
                                    Toast.LENGTH_SHORT).show();
                            ;
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(myApp, "错误信息："+s, Toast.LENGTH_SHORT).show();
            }
        });


				/*
				 * InputMethodManager imm =
				 * (InputMethodManager)getSystemService(
				 * Context.INPUT_METHOD_SERVICE);
				 * imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
				 * InputMethodManager.HIDE_NOT_ALWAYS);
				 */


    }

}

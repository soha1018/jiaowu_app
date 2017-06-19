/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.cangshengwang.android.ui.mystore
 * FileNmae:RegisteredActivity.java
 */
package net.common.android.ui.mystore;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;


import net.common.android.common.HttpUtil;
import net.common.android.model.ResultObject;
import net.common.android.ui.custom.StackBarChart01View;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * 没用
 */
public class TBActivity extends Activity {
	private StackBarChart01View barChart3D01View;
	private List<Map<String, Object>> oList=new ArrayList<Map<String,Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getdata();
		barChart3D01View=new StackBarChart01View(TBActivity.this,oList);
		FrameLayout content = new FrameLayout(TBActivity.this);
		content.setBackgroundColor(Color.parseColor("#ffffff"));
		// 缩放控件放置在FrameLayout的上层，用于放大缩小图表
		FrameLayout.LayoutParams frameParm = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		frameParm.gravity = Gravity.BOTTOM | Gravity.RIGHT;

		/*
		 * //缩放控件放置在FrameLayout的上层，用于放大缩小图表 mZoomControls = new
		 * ZoomControls(this); mZoomControls.setIsZoomInEnabled(true);
		 * mZoomControls.setIsZoomOutEnabled(true);
		 * mZoomControls.setLayoutParams(frameParm);
		 */

		// 图表显示范围在占屏幕大小的90%的区域内
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int scrWidth = (int) (dm.widthPixels * 0.9);
		int scrHeight = (int) (dm.heightPixels * 0.9);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				scrWidth, scrHeight);

		// 居中显示
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		// 图表view放入布局中，也可直接将图表view放入Activity对应的xml文件中
		final RelativeLayout chartLayout = new RelativeLayout(TBActivity.this);

		
			chartLayout.addView(barChart3D01View, layoutParams);

			// 增加控件
			((ViewGroup) content).addView(chartLayout);
			setContentView(content);
	}
	public void getdata(){
		
		
		String url = HttpUtil.URL_GETDATA;

		HttpUtils utils = new HttpUtils();
		utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				try {
					JSONObject obj = new JSONObject(result);
					String arrlist = obj.optString("jsonString");
//					JSONObject obj = new JSONObject(json);
					if(arrlist!="" && !arrlist.equals("arrlist") && arrlist!=null && !arrlist.equals("[]")){

						oList=ResultObject.newInstanceList(arrlist);

					}else{
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(TBActivity.this, "错误信息："+msg, Toast.LENGTH_SHORT).show();
			}
		});

	}


}

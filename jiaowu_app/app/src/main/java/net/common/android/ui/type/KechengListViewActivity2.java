/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.ui.type
 * FileNmae:GoodsListViewActivity.java
 */
package net.common.android.ui.type;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import net.cangshengwang.android.mobile2.R;
import net.common.android.adapter.KechengListViewAdapter;
import net.common.android.common.Constants;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.handler.RemoteDataHandler;
import net.common.android.handler.RemoteDataHandler.Callback;
import net.common.android.model.Kecheng;
import net.common.android.model.ResponseData;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * 取消选课的界面
 */
public class KechengListViewActivity2 extends Activity implements
		OnScrollListener {

	private KechengListViewAdapter adapter;
	private String keyword;
	private ArrayList<Kecheng> lists;
	private ListView goodsListView;
	private int type;
/*	private EditText textHomeSearch;
	private ImageButton imageSearch;*/
	private MyApp myApp;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list2);
		myApp = (MyApp) KechengListViewActivity2.this.getApplication();
		goodsListView = (ListView) findViewById(R.id.goodsListView);
		/*textHomeSearch = (EditText) findViewById(R.id.textHomeSearch);
		imageSearch = (ImageButton) findViewById(R.id.imageSearch);*/
		lists = new ArrayList<Kecheng>();
		adapter = new KechengListViewAdapter(KechengListViewActivity2.this);
		goodsListView.setAdapter(adapter);
		goodsListView.setSelection(0);
		loadingGoodsListData(type,null);
		
		goodsListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub

				new AlertDialog.Builder(KechengListViewActivity2.this)
						.setTitle("提示")
						.setMessage("是否进行退选该课程")
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}
								})
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										tuixuan(lists.get(arg2).getId()+"");
									}
								}).show();
				return false;
			}
		});

	}
	public void tuixuan(String id) {
		String url = HttpUtil.URL_DELXUANKE+"xuanke.kechengid="+id+"&xuanke.userid="+myApp.getLoginKey();

		HttpUtils utils = new HttpUtils();
		utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				try {
					JSONObject obj = new JSONObject(result);
					String arrlist = obj.optString("jsonString");
					// JSONObject obj = new JSONObject(json);
					if (arrlist != "" && !arrlist.equals("arrlist")
							&& arrlist != null && !arrlist.equals("[]")) {

						if(arrlist.equals("1")){
							Toast.makeText(KechengListViewActivity2.this, "操作成功",
									Toast.LENGTH_SHORT).show();
							finish();
						}else{
							Toast.makeText(KechengListViewActivity2.this, "操作失败",
									Toast.LENGTH_SHORT).show();
						}
					} else {
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(myApp, "错误信息："+msg, Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void loadingGoodsListData(int type,String searchtext) {
		String url = HttpUtil.URL_MEETROOMLIST2+"?userid="+myApp.getLoginKey();

		HttpUtils utils = new HttpUtils();
		utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				try {
					JSONObject obj = new JSONObject(result);
					String arrlist = obj.optString("jsonString");
					if (!arrlist.equals("") && !arrlist.equals("arrlist") && !arrlist.equals("[]")) {

						ArrayList<Kecheng> goods_list = Kecheng
								.newInstanceList(arrlist);
						lists.addAll(goods_list);
						adapter.setTypeList(goods_list);
						adapter.notifyDataSetChanged();
					} else {
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(KechengListViewActivity2.this, "错误信息：" + msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}

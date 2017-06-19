/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.ui.type
 * FileNmae:GoodsListViewActivity.java
 */
package net.common.android.ui.mystore;

import java.io.IOException;
import java.util.ArrayList;


import net.cangshengwang.android.mobile2.R;
import net.common.android.adapter.ArticleListViewAdapter;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.model.ArticleList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
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
 * 选课的数据界面
 */
public class ChengjiListViewActivity extends Activity implements OnScrollListener {

	private ListView goodsListView;
	private ArticleListViewAdapter adapter;
	private String keyword;
	private ArrayList<ArticleList> lists;
	private int b;
	private MyApp myApp;
//	private EditText textHomeSearch;
//	private ImageButton imageSearch; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_list);
		goodsListView = (ListView) findViewById(R.id.goodsListView);
//		textHomeSearch = (EditText) findViewById(R.id.textHomeSearch);
//		imageSearch = (ImageButton) findViewById(R.id.imageSearch);
		lists= new ArrayList<ArticleList>();
		adapter =  new ArticleListViewAdapter(ChengjiListViewActivity.this);
		goodsListView.setAdapter(adapter);
		goodsListView.setSelection(0);
		
		myApp = (MyApp) ChengjiListViewActivity.this.getApplication();
		loadingGoodsListData();
		
		
/*		goodsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				MemberList bean = (MemberList) goodsListView.getItemAtPosition(arg2);
				if(bean != null){
					Intent intent =new Intent(MessageListViewActivity.this,MessageActivity.class);
					intent.putExtra("userid", bean.getId());
					MessageListViewActivity.this.startActivity(intent);
				}
			}
		});*/
	}
	


	
	
	
	public void loadingGoodsListData(){
		
	
		String url = HttpUtil.URL_CHENGJILIST+"studentno="+myApp.getLoginStudetno();


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

                        ArrayList<ArticleList> goods_list=ArticleList.newInstanceList(arrlist);
                        lists.addAll(goods_list);
                        adapter.setTypeList(goods_list);
                        adapter.notifyDataSetChanged();
                    }else{
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(ChengjiListViewActivity.this, "错误信息：" + msg, Toast.LENGTH_SHORT).show();
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

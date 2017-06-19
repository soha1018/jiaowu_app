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
import net.common.android.adapter.QuesListViewAdapter;
import net.common.android.common.Constants;
import net.common.android.common.HttpUtil;
import net.common.android.handler.RemoteDataHandler;
import net.common.android.handler.RemoteDataHandler.Callback;
import net.common.android.model.QuesList;
import net.common.android.model.ResponseData;
import net.common.android.ui.mystore.AnswerActivity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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
import android.net.Uri;
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
 * 在线评教的界面
 */
public class QuesListViewActivity extends Activity implements OnScrollListener {

	private ListView goodsListView;
	private QuesListViewAdapter adapter;
	private String keyword;
	private ArrayList<QuesList> lists;
	private EditText textHomeSearch;
	private ImageButton imageSearch; 
	private String shop_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ques_list);
		keyword = QuesListViewActivity.this.getIntent().getStringExtra("keyword");
		goodsListView = (ListView) findViewById(R.id.goodsListView);
		textHomeSearch = (EditText) findViewById(R.id.textHomeSearch);
		imageSearch = (ImageButton) findViewById(R.id.imageSearch);
		lists= new ArrayList<QuesList>();
		adapter =  new QuesListViewAdapter(QuesListViewActivity.this);
		goodsListView.setAdapter(adapter);
		goodsListView.setSelection(0);
		
			loadingGoodsListData();
		
//		loadingGoodsListData(gc_id,gc_type,order,goods_type);
//		loadingGoodsListData(null);
		
	/*	imageSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String searchtext = textHomeSearch.getText().toString();
				
				loadingGoodsListData(searchtext);
				
			}
		});*/
		
		goodsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				QuesList bean = (QuesList) goodsListView
						.getItemAtPosition(arg2);
				
				 //用intent启动拨打电话  
                Intent intent = new Intent(QuesListViewActivity.this,AnswerActivity.class);
                Bundle b = new Bundle();
                b.putString("id", bean.getId());
                intent.putExtras(b);
                startActivity(intent); 
				
				
			}
		});
	}
	
	public void loadingGoodsListData(){
	
		String url = HttpUtil.URL_QUESLST;

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

						ArrayList<QuesList> goods_list=QuesList.newInstanceList(arrlist);
						lists.addAll(goods_list);
						adapter.setGoodsLists(goods_list);
						adapter.notifyDataSetChanged();
					}else{
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(QuesListViewActivity.this, "错误信息"+msg, Toast.LENGTH_SHORT).show();
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

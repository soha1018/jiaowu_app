/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.ui.type
 * FileNmae:GoodsDetailsActivity.java
 */
package net.common.android.ui.type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


import net.cangshengwang.android.mobile2.R;
import net.common.android.adapter.GoodsDetailsGalleryAdapter;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.ui.custom.MyListView;
import net.common.android.ui.mystore.LoginActivity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * 没用
 */
public class GoodsDetailsActivity extends Activity {
	private Gallery goodsDetailsGallery;
	private TextView textGoodsDetailsName;
	private TextView textGoodsDetailsPrice;
	private TextView textGoodsDetailsMarketPrice;
	private TextView textGoodsDetailsStorage;
	private TextView textGoodsDetailsWeb;
	private TextView textGoodsPrice;
	private TextView textauthor;
	private TextView texttype;
	private MyListView listViewManSongRules;
	private LinearLayout linearLayoutSpecView;
//	private GoodsDetailCommentsListViewAdapter gooddetailcommentsAdapter;
	private ScrollView SView;
	private LinearLayout linearlayoutManSong;
	private LinearLayout linearLayoutStorage;
	private LinearLayout linearLayoutNumber;
	private LinearLayout linearLayoutBuy;
//	private ArrayList<CommentsList> lists = new ArrayList<CommentsList>();
	private ImageButton imageBackButton;
	private ImageButton imageButtonFavoritesAdd;
	private MyApp myApp;
	private Button buttonAddComments;
	private Button buttonNowBuy;
	private GoodsDetailsGalleryAdapter adapter;
	private int GoodsNumberCount =1;
	private int upper_limit = 0;
	private int goods_kucun = 0;
	private int goods_default_buynum = 1;
	private String goods_isbuy;
	private MyListView commentslistViewOrder;
	private int productpoint;
	private int memberpoint;
	private String jingdian_id;
	private String ticketid;
	private String jingdianzuobiao;
	private String jingdian;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_details_view);
		myApp =  (MyApp) GoodsDetailsActivity.this.getApplication();
		
		textauthor = (TextView) findViewById(R.id.textauthor);
		texttype = (TextView) findViewById(R.id.texttype);
		
		textGoodsPrice = (TextView) findViewById(R.id.textGoodsPrice);
		textGoodsDetailsName = (TextView) findViewById(R.id.textGoodsDetailsName);
		textGoodsDetailsPrice = (TextView) findViewById(R.id.textGoodsDetailsPrice);
		textGoodsDetailsMarketPrice = (TextView) findViewById(R.id.textGoodsDetailsMarketPrice);
		textGoodsDetailsWeb = (TextView) findViewById(R.id.textGoodsDetailsWeb);
		linearLayoutSpecView =(LinearLayout) findViewById(R.id.linearLayoutSpecView);
		listViewManSongRules = (MyListView) findViewById(R.id.listViewManSongRules);
		linearlayoutManSong = (LinearLayout) findViewById(R.id.linearlayoutManSong);
		linearLayoutStorage = (LinearLayout) findViewById(R.id.linearLayoutStorage);
		imageBackButton = (ImageButton) findViewById(R.id.imageBackButton);
		buttonAddComments = (Button) findViewById(R.id.buttonAddComments);
		buttonNowBuy = (Button) findViewById(R.id.buttonNowBuy);
		imageButtonFavoritesAdd = (ImageButton) findViewById(R.id.imageButtonFavoritesAdd);
		SView = (ScrollView) findViewById(R.id.SView);
		commentslistViewOrder =(MyListView)findViewById(R.id.goodsCommentsListView);
//		gooddetailcommentsAdapter = new GoodsDetailCommentsListViewAdapter(GoodsDetailsActivity.this);
//		commentslistViewOrder.setAdapter(gooddetailcommentsAdapter);
		goodsDetailsGallery = (Gallery) findViewById(R.id.goodsDetailsGallery);
		adapter = new GoodsDetailsGalleryAdapter(GoodsDetailsActivity.this);
		goodsDetailsGallery.setAdapter(adapter);
		
		jingdian_id = getIntent().getExtras().getString("bioid");
		
		
		loadingGoodsDetailData(jingdian_id);
		

		if (type!=null){
			if(type.equals("小卖部信息")||type.equals("食堂信息")){
				textGoodsPrice.setVisibility(View.VISIBLE);
			}else{
				textGoodsPrice.setVisibility(View.GONE);
			}
		}

		imageBackButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GoodsDetailsActivity.this.finish();
			}
		});
		
	
		
		
		buttonNowBuy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isLogin()) {
				/*	Intent intent = new Intent(GoodsDetailsActivity.this,LocationOverlayDemo.class);
					intent.putExtra("zuobiao", jingdianzuobiao);
					GoodsDetailsActivity.this.startActivity(intent);*/
				} else {
					goToLogin();
				}
				
			}
		});
	/*	buttonAddComments.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isLogin()) {
					Intent intent = new Intent(GoodsDetailsActivity.this,CommentsActivity.class);
					intent.putExtra("jingdianid", jingdian_id);
					GoodsDetailsActivity.this.startActivity(intent);
				} else {
					goToLogin();
				}
				
			}
		});*/
		
		if(GoodsNumberCount <= goods_default_buynum){
			GoodsNumberCount = goods_default_buynum;
		}
		
	}
	
	

	public void shopAddCart(String goods_id){
	
		
		String url = HttpUtil.URL_TICKETBUY;
		String query ="";
		query+="ticketid="+goods_id+"&";
		query+="userid="+myApp.getLoginKey();
		url+=query;
		
		
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 获得响应
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				JSONObject obj;
				try {
					obj = new JSONObject(result);
					String jsonStr = obj.optString("jsonString");
					if(jsonStr.equals("1")){
						Toast.makeText(GoodsDetailsActivity.this, "购票成功", Toast.LENGTH_SHORT).show();;
					}else{
						
						Toast.makeText(GoodsDetailsActivity.this, "购票失败", Toast.LENGTH_SHORT).show();;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			Toast.makeText(GoodsDetailsActivity.this, result, Toast.LENGTH_SHORT).show();;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			Toast.makeText(GoodsDetailsActivity.this, result, Toast.LENGTH_SHORT).show();;
		}

	}
	
	
	public boolean isLogin() {
	    String key = myApp.getLoginKey();
	    if(key!=null && key!="") {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public void goToLogin() {
	    Intent intent = new Intent(GoodsDetailsActivity.this, LoginActivity.class);
	    startActivity(intent);
	}
	

	
	
	
	public void loadingGoodsDetailData(String goods_id){
		String url = HttpUtil.URL_BIODETAIL+goods_id;

		HttpUtils utils = new HttpUtils();
		utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				try {
					JSONObject obj = new JSONObject(result);
					String jsonStr = obj.optString("jsonString");
//					JSONObject obj = new JSONObject(json);
					System.out.println(jsonStr);
					JSONObject goods = new JSONObject(jsonStr);
//					textGoodsDetailsMarketPrice.setText("￥"+goods.optDouble("ticketoprice"));
					textGoodsDetailsPrice.setText("标题:"+goods.optString("title"));
					textGoodsDetailsName.setText(goods.optString("content"));
					textauthor.setText("作者:"+goods.optString("author"));
					texttype.setText("类型:"+goods.optString("type"));
					jingdianzuobiao = goods.optString("zuobiao");
					textGoodsPrice.setText("价格:"+jingdianzuobiao+"元");
					type = goods.optString("type");
					String imageurl_ = goods.optString("image_url").split("\\\\")[1];
					String imageurl = HttpUtil.URL_BASEUPLOAD+imageurl_;
					String good_image= imageurl+","+imageurl;
					adapter.setGoods_image(good_image.split(","));
					adapter.notifyDataSetChanged();
					textGoodsDetailsMarketPrice.setText("发布日期："+ goods.optString("pubdate"));

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

	
}

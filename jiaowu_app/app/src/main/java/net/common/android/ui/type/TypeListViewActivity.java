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
import net.common.android.adapter.TypeListViewAdapter;
import net.common.android.common.HttpUtil;
import net.common.android.model.TypeList2;

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
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
 * 通知查看的界面
 */
public class TypeListViewActivity extends Activity implements OnScrollListener {

	private ListView goodsListView;
	private static final String TAG = "TypeListViewActivity";
	private TypeListViewAdapter adapter;
	private String keyword;
	private ArrayList<TypeList2> lists;
	private TextView title;
	private int b;
//	private EditText textHomeSearch;
//	private ImageButton imageSearch; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.type_list2);
		goodsListView = (ListView) findViewById(R.id.goodsListView);
		title = (TextView) findViewById(R.id.title);
//		textHomeSearch = (EditText) findViewById(R.id.textHomeSearch);
//		imageSearch = (ImageButton) findViewById(R.id.imageSearch);
		lists= new ArrayList<TypeList2>();
		adapter =  new TypeListViewAdapter(TypeListViewActivity.this);
		goodsListView.setAdapter(adapter);
		goodsListView.setSelection(0);
		Bundle bundle = getIntent().getExtras();
		if(bundle !=null){
			b = bundle.getInt("type");
		}else{
			b=6;
		}
		
		
		loadingGoodsListData(b);
		
		
		goodsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				TypeList2 bean = (TypeList2) goodsListView.getItemAtPosition(arg2);
				if(bean != null){
					Intent intent =new Intent(TypeListViewActivity.this,GoodsDetailsActivity.class);
					intent.putExtra("bioid", bean.getId());
					TypeListViewActivity.this.startActivity(intent);
				}
			}
		});
	}
	public void loadingGoodsListData(int b){
		
	
		String url = HttpUtil.URL_TYPELIST;
		
		if(b==0){
			url = HttpUtil.URL_TYPELIST0;
			title.setText("通知查看");
		}else if(b==1){
			url = HttpUtil.URL_TYPELIST1;
			title.setText("二手信息");
		}

		Log.i(TAG, "loadingGoodsListData: url是："+url);

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

                        ArrayList<TypeList2> goods_list=TypeList2.newInstanceList(arrlist);
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
				Toast.makeText(TypeListViewActivity.this, "错误信息："+msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}

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
import net.common.android.common.Constants;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyApp;
import net.common.android.model.Kecheng;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
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

import static net.common.android.common.Constants.TAG;

/**
 * 取消选课的界面
 */
public class KechengListViewActivity2 extends Activity implements
        OnScrollListener {

    private MyAdapter adapter;
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
        adapter = new MyAdapter();
        goodsListView.setAdapter(adapter);
        Kecheng kecheng = (Kecheng) getIntent().getSerializableExtra("bean");
        if (kecheng != null) {
            lists.add(lists.size(),kecheng);
            adapter.notifyDataSetChanged();
        }
        goodsListView.setSelection(0);
        loadingGoodsListData(type, null);

        //长时间单击事件
        goodsListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(KechengListViewActivity2.this)
                        .setTitle("提示")
                        .setMessage("是否进行退选该课程")
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        tuixuan(lists.get(position).getId() + "", position);


                                    }
                                }).show();
                return false;
            }
        });

    }

    public void tuixuan(String id, int position) {
        Log.i(TAG, "tuixuan: 当前条目是：" + position);
        lists.remove(position);
        adapter.notifyDataSetChanged();

        String url = HttpUtil.URL_DELXUANKE + "xuanke.kechengid=" + id + "&xuanke.userid=" + myApp.getLoginKey();

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

                        if (arrlist.equals("1")) {
                            Toast.makeText(KechengListViewActivity2.this, "操作成功",
                                    Toast.LENGTH_SHORT).show();
//							finish();
                        } else {
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
                Toast.makeText(myApp, "错误信息：" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadingGoodsListData(int type, String searchtext) {
        String url = HttpUtil.URL_MEETROOMLIST2 + "?userid=" + myApp.getLoginKey();

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i(TAG, "onSuccess: 结果是：" + result);
                try {
                    JSONObject obj = new JSONObject(result);
                    String arrlist = obj.optString("jsonString");
                    if (!arrlist.equals("") && !arrlist.equals("arrlist") && !arrlist.equals("[]")) {

                        ArrayList<Kecheng> goods_list = Kecheng
                                .newInstanceList(arrlist);
                        lists.addAll(goods_list);
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

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Kecheng getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.listivew_type1_item, null);
                holder = new ViewHolder();
                holder.textName = (TextView) convertView
                        .findViewById(R.id.textName);
                holder.textDevice = (TextView) convertView
                        .findViewById(R.id.textDevice);
                holder.textCapability = (TextView) convertView
                        .findViewById(R.id.textCapability);
                holder.textDesc = (TextView) convertView
                        .findViewById(R.id.textDesc);

		/*	holder.imagePic = (ImageView) convertView
					.findViewById(R.id.imagePic);*/

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Kecheng bean = lists.get(position);
            holder.textName.setText("课程名称:" + bean.getName());
            holder.textDevice.setText("课时:" + bean.getDevice());
            holder.textCapability.setText("所属专业:" + bean.getCapability());
            holder.textDesc.setText("课程负责人:" + bean.getDescription());

            return convertView;
        }
    }

    class ViewHolder {
        TextView textName;
        TextView textDevice;
        TextView textCapability;
        TextView textDesc;
    }
}

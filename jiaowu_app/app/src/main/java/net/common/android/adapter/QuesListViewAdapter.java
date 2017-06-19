/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:GoodsListViewAdapter.java
 */
package net.common.android.adapter;

import java.util.ArrayList;

import net.cangshengwang.android.mobile2.R;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyBackAsynaTask;
import net.common.android.model.QuesList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 */
public class QuesListViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<QuesList> goodsLists;
	public QuesListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return goodsLists == null ? 0 : goodsLists.size();
	}

	@Override
	public Object getItem(int position) {
		return goodsLists.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	public ArrayList<QuesList> getGoodsLists() {
		return goodsLists;
	}
	
	public void setGoodsLists(ArrayList<QuesList> goodsLists) {
		this.goodsLists = goodsLists;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		QuesList bean= goodsLists.get(position);
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listivew_ques_item, null);
			holder = new ViewHolder();
			holder.textName = (TextView) convertView.findViewById(R.id.textName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		holder.textName.setText("题目:"+bean.getName());
		
		return convertView;
	}
	class ViewHolder {
		TextView textName;
	}
}

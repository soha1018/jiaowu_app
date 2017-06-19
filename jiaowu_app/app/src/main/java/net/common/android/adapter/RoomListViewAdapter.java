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
import net.common.android.model.RoomList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 教室查询
 */
public class RoomListViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<RoomList> goodsLists;
	public RoomListViewAdapter(Context context) {
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

	
	public ArrayList<RoomList> getGoodsLists() {
		return goodsLists;
	}
	
	public void setGoodsLists(ArrayList<RoomList> goodsLists) {
		this.goodsLists = goodsLists;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		RoomList bean= goodsLists.get(position);
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listivew_event_item, null);
			holder = new ViewHolder();
			holder.textName = (TextView) convertView.findViewById(R.id.textName);
			holder.textTel = (TextView) convertView.findViewById(R.id.textTel);
			holder.textMail = (TextView) convertView.findViewById(R.id.textMail);
			holder.textJob = (TextView) convertView.findViewById(R.id.textJob);
			holder.textDept = (TextView) convertView.findViewById(R.id.textDept);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		holder.textName.setText("名称:"+bean.getName());
		holder.textTel.setText("座位数:"+bean.getCountno());
		holder.textMail.setText("状态:"+bean.getStatus());
		holder.textJob.setText("所属系部:"+bean.getJob());
		holder.textJob.setVisibility(View.GONE);
		holder.textDept.setVisibility(View.GONE);
		holder.textDept.setText("年龄:"+bean.getDept());
		
		return convertView;
	}
	class ViewHolder {
		TextView textName;
		TextView textTel;
		TextView textMail;
		TextView textJob;
		TextView textDept;
	}
}

/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:HomeGridViewAdapter.java
 */
package net.common.android.adapter;

import java.util.ArrayList;

import net.cangshengwang.android.mobile2.R;
import net.common.android.common.HttpUtil;
import net.common.android.common.MyBackAsynaTask;
import net.common.android.model.Kecheng;
import net.common.android.ui.type.KechengListViewActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 */
public class KechengListViewAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Kecheng> typeList;

	public KechengListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return typeList == null ? 0 : typeList.size();
	}

	@Override
	public Object getItem(int position) {
		return typeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public ArrayList<Kecheng> getTypeList() {
		return typeList;
	}

	public void setTypeList(ArrayList<Kecheng> typeList) {
		this.typeList = typeList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listivew_type1_item, null);
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

		Kecheng bean = typeList.get(position);
		holder.textName.setText("课程名称:"+bean.getName());
		holder.textDevice.setText("课时:"+bean.getDevice());
		holder.textCapability.setText("所属专业:"+bean.getCapability());
		holder.textDesc.setText("课程负责人:"+bean.getDescription());

		return convertView;
	}

	class ViewHolder {
		TextView textName;
		TextView textDevice;
		TextView textCapability;
		TextView textDesc;
	}
}

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
import net.common.android.model.TypeList2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通知栏查看的数据适配器
 */
public class TypeListViewAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<TypeList2> typeList;

	public TypeListViewAdapter(Context context) {
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

	public ArrayList<TypeList2> getTypeList() {
		return typeList;
	}

	public void setTypeList(ArrayList<TypeList2> typeList) {
		this.typeList = typeList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listivew_type_item2, null);
			holder = new ViewHolder();
			holder.textName = (TextView) convertView
					.findViewById(R.id.textName);
			holder.textDesc = (TextView) convertView
					.findViewById(R.id.textDesc);
			holder.imagePic = (ImageView) convertView
					.findViewById(R.id.imagePic);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		TypeList2 bean = typeList.get(position);
		holder.textName.setText(bean.getTitle());
		holder.textDesc.setText(bean.getContent());
		String imagename = bean.getImage_url().split("\\\\")[1];
		MyBackAsynaTask asynaTask = new MyBackAsynaTask(HttpUtil.URL_BASEUPLOAD+imagename, holder.imagePic);
		asynaTask.execute();

		return convertView;
	}

	class ViewHolder {
		TextView textName;
		TextView textDesc;
		ImageView imagePic;
	}
}

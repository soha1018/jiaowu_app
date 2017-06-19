package net.common.android.adapter;

import net.cangshengwang.android.mobile2.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 没用
 */
public class ListViewAdapter extends BaseAdapter {
	private String[] names;
	private Context mContext;  
    private LayoutInflater mInflater;
	public ListViewAdapter(Context mContext,String[] names) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		mInflater = LayoutInflater.from(this.mContext);
		this.names = names;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	static class ViewHolder {
		TextView text;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 ViewHolder holder;

		 if (convertView == null) {
			 convertView = mInflater.inflate(R.layout.list_view_item,parent, false);
			 holder = new ViewHolder();
			 holder.text = (TextView) convertView.findViewById(R.id.tv_list_item_title);
			 convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(names[position]);
		holder.text.setTextSize(40);
		return convertView;
	}

}

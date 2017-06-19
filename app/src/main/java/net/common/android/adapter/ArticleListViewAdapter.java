/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:HomeGridViewAdapter.java
 */
package net.common.android.adapter;

import java.util.ArrayList;

import net.cangshengwang.android.mobile2.R;
import net.common.android.model.ArticleList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 学生成绩ListView的适配
 */
public class ArticleListViewAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ArticleList> typeList;

	public ArticleListViewAdapter(Context context) {
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

	public ArrayList<ArticleList> getTypeList() {
		return typeList;
	}

	public void setTypeList(ArrayList<ArticleList> typeList) {
		this.typeList = typeList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listivew_article_item, null);
			holder = new ViewHolder();
			holder.textStudentno = (TextView) convertView
					.findViewById(R.id.textStudentno);
			holder.textLession = (TextView) convertView
					.findViewById(R.id.textLession);
			holder.textScore = (TextView) convertView
					.findViewById(R.id.textScore);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ArticleList bean = typeList.get(position);
		holder.textStudentno.setText("学生学号:"+bean.getStudentno());
		holder.textLession.setText("课程:"+bean.getLession());
		holder.textScore.setText("成绩:"+bean.getScore());

		return convertView;
	}

	class ViewHolder {
		TextView textStudentno;
		TextView textLession;
		TextView textScore;
	}
}

package net.common.android.ui.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.ExerciserBean;
import net.common.android.Utils.MyUtils;
import net.common.android.common.MyApp;

import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 查看习题界面
 * Created by Administrator on 2017/6/13.
 */

public class ExercisesActivity extends Activity {
	private ListView lv_exercises;
	/**
	 * 查看习题
	 */
	private TextView title;
	/**
	 * 当前老师还没有发布习题
	 */
	private TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercises);

		initView();
		initAdapter();
	}

	/**
	 * 初始化数据适配器
	 */
	private void initAdapter() {

		BmobQuery<ExerciserBean> query = new BmobQuery<ExerciserBean>();
		query.findObjects(new FindListener<ExerciserBean>() {
			@Override
			public void done(final List<ExerciserBean> list, BmobException e) {
				if (list != null && list.size() > 0) {
					lv_exercises.setVisibility(View.VISIBLE);
					tv1.setVisibility(View.GONE);

					MyAdapter adapter = new MyAdapter(list);
					lv_exercises.setAdapter(adapter);
				} else {
					tv1.setVisibility(View.VISIBLE);
					lv_exercises.setVisibility(View.GONE);
				}

				lv_exercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
						ExerciserBean exercisesBean = list.get(position);
						intent.putExtra("list", exercisesBean);
						startActivity(intent);
					}
				});
			}
		});


	}

	private void initView() {
		lv_exercises = (ListView) findViewById(R.id.lv_exercises);
		title = (TextView) findViewById(R.id.title);
		tv1 = (TextView) findViewById(R.id.tv1);
	}

	private class MyAdapter extends BaseAdapter {
		private final List<ExerciserBean> list;

		public MyAdapter(List<ExerciserBean> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public ExerciserBean getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = View.inflate(getApplicationContext(), R.layout.list_exercises_item, null);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			ExerciserBean bean = getItem(position);
			viewHolder.text_title.setText("习题的标题：" + bean.title);
			viewHolder.text_content.setText("习题的内容：" + bean.content);
			viewHolder.text_user.setText("发布人：" + bean.name);
			viewHolder.text_time.setText("发布时间："+MyUtils.long2String(bean.time));
			return convertView;
		}
	}

	static class ViewHolder {
		View view;
		TextView text_title;
		TextView text_user;
		TextView text_content;
		TextView text_time;

		ViewHolder(View view) {
			this.view = view;
			this.text_title = (TextView) view.findViewById(R.id.text_title);
			this.text_user = (TextView) view.findViewById(R.id.text_user);
			this.text_content = (TextView) view.findViewById(R.id.text_content);
			this.text_time = (TextView) view.findViewById(R.id.text_time);
		}
	}
}

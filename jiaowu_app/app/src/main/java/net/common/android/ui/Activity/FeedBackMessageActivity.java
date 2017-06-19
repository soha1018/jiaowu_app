package net.common.android.ui.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.FeedBackBean;
import net.common.android.Utils.MyUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 反馈回来的信息界面
 * Created by Administrator on 2017/6/14.
 */

public class FeedBackMessageActivity extends Activity {
	private ListView lv_feed;
	private TextView t_feed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback_message);
		initView();
		initAdapter();

	}

	private void initAdapter() {
		BmobQuery<FeedBackBean> query = new BmobQuery<FeedBackBean>();
		query.findObjects(new FindListener<FeedBackBean>() {
			@Override
			public void done(List<FeedBackBean> list, BmobException e) {
				if (list != null && list.size() > 0) {
					lv_feed.setVisibility(View.VISIBLE);
					t_feed.setVisibility(View.GONE);

					lv_feed.setAdapter(new FeedAdapter(list));
				} else {
					lv_feed.setVisibility(View.GONE);
					t_feed.setVisibility(View.VISIBLE);
				}
			}
		});

	}

	private void initView() {
		lv_feed = (ListView) findViewById(R.id.lv_feed);
		t_feed = (TextView) findViewById(R.id.tv_feed);
	}

	private class FeedAdapter extends BaseAdapter {
		private final List<FeedBackBean> list;

		public FeedAdapter(List<FeedBackBean> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public FeedBackBean getItem(int position) {
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
				convertView = View.inflate(getApplicationContext(), R.layout.list_feed_item, null);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			FeedBackBean bean = getItem(position);
			viewHolder.text_title.setText(bean.title);
			viewHolder.text_content.setText(bean.content);
			viewHolder.text_time.setText(MyUtils.long2String(bean.time));
			return convertView;
		}
	}

	static class ViewHolder {
		View view;
		TextView text_title;
		TextView text_content;
		TextView text_time;

		ViewHolder(View view) {
			this.view = view;
			this.text_title = (TextView) view.findViewById(R.id.text_title);
			this.text_content = (TextView) view.findViewById(R.id.text_content);
			this.text_time = (TextView) view.findViewById(R.id.text_time);
		}
	}
}

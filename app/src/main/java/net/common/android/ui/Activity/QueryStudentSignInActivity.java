package net.common.android.ui.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.cangshengwang.android.mobile2.R;
import net.common.android.Bean.ExerciserBean;
import net.common.android.Bean.SignBean;
import net.common.android.Engine.BarChart;

import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 查询学生签到的界面
 * Created by Administrator on 2017/6/14.
 */

public class QueryStudentSignInActivity extends Activity implements View.OnClickListener {
	private ViewPager vp_sign;
	private LinearLayout ll_point;
	private List<View> viewList;
	/**
	 * 请输入班级
	 */
	private EditText et_grade;
	/**
	 * 查询
	 */
	private Button btn_query;
	/**
	 * 现在还没有学生签到
	 */
	private TextView tv_default;
	private ListView lv_sign;
	private Handler handler = new Handler();
	private Random random = new Random();
	private BarChart chart;

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (chart != null && !TextUtils.isEmpty(grade)) {
				BmobQuery<ExerciserBean> query = new BmobQuery<ExerciserBean>();
				query.addWhereContainedIn("grade", Arrays.asList(Integer.parseInt(grade)));
				query.findObjects(new FindListener<ExerciserBean>() {

					@Override
					public void done(List<ExerciserBean> list, BmobException e) {
						if (list != null && list.size() > 0) {
							chart.upData(list.size());
						}
					}
				});

			}
			handler.postDelayed(runnable, 3000);
		}
	};
	private String grade;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(runnable);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_querystudent_signin);
		initView();
		initAdapter();
		initData();
	}

	private void initData() {

		handler.post(runnable);
	}

	private void initAdapter() {
		viewList = new ArrayList<View>();
		View view = View.inflate(this, R.layout.activity_query_signin, null);
		initView(view);
		viewList.add(view);

		chart = new BarChart(this, "班级人数实时统计");
		GraphicalView view1 = chart.getView(30);
		viewList.add(view1);

		View pointView;
		LinearLayout.LayoutParams layoutParams;
		for (int i = 0; i < viewList.size(); i++) {
			pointView = new View(this);
			pointView.setBackgroundResource(R.drawable.select_point_bg);
			pointView.setEnabled(true);
			layoutParams = new LinearLayout.LayoutParams(22, 22);
			if (i > 0) {
				layoutParams.leftMargin = 20;
			}
			ll_point.addView(pointView, layoutParams);
		}

		vp_sign.setAdapter(new SignAdpter());
		ll_point.getChildAt(0).setEnabled(false);
		ll_point.setEnabled(false);

		vp_sign.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				for (int i = 0; i < ll_point.getChildCount(); i++) {
					if (i == position) {
						ll_point.getChildAt(i).setEnabled(false);
					} else {
						ll_point.getChildAt(i).setEnabled(true);
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		for (int i = 0; i < ll_point.getChildCount(); i++) {
			final int finalI = i;
			ll_point.getChildAt(i).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					vp_sign.setCurrentItem(finalI);
				}
			});
		}

	}

	private void initView(View view) {
		et_grade = (EditText) view.findViewById(R.id.et_grade);
		btn_query = (Button) view.findViewById(R.id.btn_query);
		btn_query.setOnClickListener(this);
		tv_default = (TextView) view.findViewById(R.id.tv_default);
		lv_sign = (ListView) view.findViewById(R.id.lv_sign);
	}

	private void initView() {
		vp_sign = (ViewPager) findViewById(R.id.vp_sign);
		ll_point = (LinearLayout) findViewById(R.id.ll_point);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_query:
				querySign();
				break;
		}
	}

	private void querySign() {
		grade = et_grade.getText().toString().trim();
		if (TextUtils.isEmpty(grade)) {
			Toast.makeText(this, "请输入班级号", Toast.LENGTH_SHORT).show();
			return;
		}
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("查询签到");
		dialog.setMessage("查询中，请稍等。。。");
		dialog.show();
		BmobQuery<SignBean> query = new BmobQuery<SignBean>();
		query.addWhereContainedIn("grade", Arrays.asList(Integer.parseInt(grade)));
		query.findObjects(new FindListener<SignBean>() {
			@Override
			public void done(List<SignBean> list, BmobException e) {
				if (e == null) {
					dialog.dismiss();
					if (list != null && list.size() > 0) {
						lv_sign.setVisibility(View.VISIBLE);
						tv_default.setVisibility(View.GONE);

						lv_sign.setAdapter(new SignAdapter(list));

						AlphaAnimation animation = new AlphaAnimation(0, 1);
						animation.setDuration(300);
						LayoutAnimationController controller = new LayoutAnimationController(animation);
						lv_sign.setLayoutAnimation(controller);
					} else {
						lv_sign.setVisibility(View.GONE);
						tv_default.setVisibility(View.VISIBLE);
					}
				} else {
					dialog.dismiss();
					Toast.makeText(QueryStudentSignInActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private class SignAdapter extends BaseAdapter {
		private List<SignBean> list;

		public SignAdapter(List<SignBean> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public SignBean getItem(int position) {
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
				convertView = View.inflate(getApplicationContext(), R.layout.list_sign_item, null);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			SignBean bean = getItem(position);
			viewHolder.tv_grade.setText(bean.grade + "");
			viewHolder.tv_name.setText(bean.name);
			viewHolder.tv_number.setText(bean.number + "");
			viewHolder.tv_course.setText(bean.course);

			return convertView;
		}
	}


	static class ViewHolder {
		View view;
		TextView tv_course;
		TextView tv_grade;
		TextView tv_name;
		TextView tv_number;

		ViewHolder(View view) {
			this.view = view;
			this.tv_grade = (TextView) view.findViewById(R.id.tv_grade);
			this.tv_name = (TextView) view.findViewById(R.id.tv_name);
			this.tv_number = (TextView) view.findViewById(R.id.tv_number);
			this.tv_course = (TextView) view.findViewById(R.id.tv_course);
		}
	}

	private class SignAdpter extends PagerAdapter {
		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = viewList.get(position);
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}

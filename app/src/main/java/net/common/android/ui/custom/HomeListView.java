package net.common.android.ui.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 没用
 */
public class HomeListView extends ListView {
	
	private GestureDetector mGestureDetector;
	View.OnTouchListener mGestureListener;
	private Context context;
	private ViewPager viewPager;
	
	public HomeListView(Context context,AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}

//	/**
//	 * 设置不滚动
//	 */
//	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//		super.onMeasure(widthMeasureSpec, expandSpec);
//
//	}

	public void setViewPager(ViewPager viewPager) {
		this.viewPager = viewPager;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}

	class YScrollDetector extends SimpleOnGestureListener {

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if (Math.abs(distanceY) >= Math.abs(distanceX)) {
				Log.e("jj", "上下....");
				return true;
			}
			Log.e("jj", "左右....");
			return false;
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Toast.makeText(context, "图" + viewPager.getCurrentItem(), 1).show();
			return super.onSingleTapUp(e);
		}
	}

}
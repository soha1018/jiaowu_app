/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:MyAsynaTask.java
 */
package net.common.android.common;
import net.cangshengwang.android.mobile2.R;
import net.common.android.handler.ImageLoader;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 异步加载图片（Background）
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class MyBackAsynaTask extends AsyncTask<String,Void,String>{
	private String themb;
	private ImageView iv;

	public MyBackAsynaTask(String themb,ImageView iv){
		this.themb=themb;
		this.iv=iv;
	}
	@Override
	protected String doInBackground(String... params) {
		if(themb!=null){
			return themb;
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(result!=null && !"".equals(result)&& !"null".equals(result)){
//			//加载远程图片
			ImageLoader.getInstance().asyncLoadBitmap(result, new ImageLoader.ImageCallback() {
				@Override
				public void imageLoaded(Bitmap bmp, String url) {
					if(bmp!=null){
						//Bitmap bitmap=getBitmap(bmp, bmp.getWidth());
						iv.setBackgroundDrawable(new BitmapDrawable(bmp));
					}else{
						iv.setBackgroundResource(R.drawable.category_icon_123);
					}
				}
			});
		}else{
			iv.setBackgroundResource(R.drawable.category_icon_123);
		}
	}
	/***
	 * 对图片处理
	 * @author zhangjia
	 */
	public Bitmap getBitmap(Bitmap bitmap, int width) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scale = (float) width / w;
		System.out.println("scale-->" + scale);
		// 保证图片不变形.
		matrix.postScale(scale, scale);
		// w,h是原图的属性.
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}
	}
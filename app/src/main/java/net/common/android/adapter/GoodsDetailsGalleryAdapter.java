/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:GoodsListViewAdapter.java
 */
package net.common.android.adapter;

import net.cangshengwang.android.mobile2.R;
import net.common.android.handler.ImageLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 没用
 */
public class GoodsDetailsGalleryAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private String[] goods_image;
	public GoodsDetailsGalleryAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}


	@Override
	public int getCount() {
		return goods_image == null ? 0 : goods_image.length;
	}

	@Override
	public Object getItem(int position) {
		return 0;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	public String[] getGoods_image() {
		return goods_image;
	}


	public void setGoods_image(String[] goods_image) {
		this.goods_image = goods_image;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.gallery_goods_item, null);
			holder = new ViewHolder();
			holder.imagePic = (ImageView) convertView.findViewById(R.id.imagePic);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().asyncLoadBitmap(goods_image[position], new ImageLoader.ImageCallback() {
			@Override
			public void imageLoaded(Bitmap bmp, String url) {
				if(bmp!=null){
					holder.imagePic.setBackgroundDrawable(new BitmapDrawable(bmp));
				}else{
					holder.imagePic.setBackgroundResource(R.drawable.category_icon_123);
				}
			}
		});
		
		return convertView;
	}
	class ViewHolder {
		ImageView imagePic;
	}
}

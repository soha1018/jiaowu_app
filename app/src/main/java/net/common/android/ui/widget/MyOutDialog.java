package net.common.android.ui.widget;

import net.cangshengwang.android.mobile2.R;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * 等待对话框
 * @author HJGANG
 */
public class MyOutDialog extends Dialog {
	public Button btu_on;
	public Button btu_off;
	public TextView text_shopnum_price;
	public ImageButton image_btu_jian;
	public TextView edit_text_count;
	public ImageButton image_btu_jia;
	public MyOutDialog(Context context) {
		super(context, R.style.MyProgressDialog);
		this.setContentView(R.layout.my_out);

		btu_on = (Button) findViewById(R.id.btu_on);
		btu_off = (Button) findViewById(R.id.btu_off);
		text_shopnum_price= (TextView) findViewById(R.id.text_shopnum_price);
		image_btu_jian= (ImageButton) findViewById(R.id.image_btu_jian);
		edit_text_count= (TextView) findViewById(R.id.edit_text_count);
		image_btu_jia= (ImageButton) findViewById(R.id.image_btu_jia);
		
	}
}

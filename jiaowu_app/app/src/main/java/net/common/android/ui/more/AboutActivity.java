/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.ui.more
 * FileNmae:AboutActivity.java
 */
package net.common.android.ui.more;
import net.cangshengwang.android.mobile2.R;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 关于我们的界面
 */
public class AboutActivity extends Activity{
	private ImageView imageBack;
	private TextView txtVersion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_about);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		txtVersion = (TextView) findViewById(R.id.txt_version);
		
		try {
	        // ---get the package info---    
	        PackageManager pm = this.getPackageManager();
	        PackageInfo pi = pm.getPackageInfo(this.getPackageName(), 0);
	        String versionName = pi.versionName;
	        if (versionName != null && versionName.length() > 0) {
	            txtVersion.setText(versionName);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		imageBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AboutActivity.this.finish();
			}
		});
	}
}

package net.common.android.common;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class NetUtils {
	public static final String TAG = "NetUtils";
	
	
	
    /**
     * 网络连接检测
     * @param ctx
     * @return
     */
    public static boolean isNetworkAvailable(Context ctx) {   
    	boolean netstatus = false;
        try {   
            ConnectivityManager cm = (ConnectivityManager) ctx   
                    .getSystemService(Context.CONNECTIVITY_SERVICE);   
            NetworkInfo info = cm.getActiveNetworkInfo();   
            if(info != null && info.isConnected()){
            	netstatus = true;
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
            netstatus = false;   
        }
     //   Log.d(Const.TAG, "NetUtil.isNetworkAvailable|netstatus="+netstatus);
        return netstatus;
    } 
    
    
    public static boolean isWifiConnected(Context context) {  
        if (context != null) {  
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
                    .getSystemService(Context.CONNECTIVITY_SERVICE);  
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager  
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
            if (mWiFiNetworkInfo != null) {  
                return mWiFiNetworkInfo.isAvailable();  
            }  
        }  
        return false;  
    }

	/**
	 * 判断Network是否开启(包括移动网络和Wifi)
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkEnabled(Context context) {
//		return ( isNetEnabled(context)|| isWIFIEnabled(context));
//		if(isNetEnabled(context) || isWIFIEnabled(context)){
//			
//		}else{
//			Log.i(TAG, "移动网络或Wifi没有开启");
//		}
		
		return ( isNetEnabled(context)|| isWIFIEnabled(context));
		
	}

	/**
	 * 判断Network是否连接成功(包括移动网络和Wifi)
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkconnected(Context context) {
		return (isWifiContected(context) || isNetContected(context));
	}

	/**
	 * 判断移动网络是否开启
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetEnabled(Context context) {
		boolean enable = false;
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (telephonyManager != null) {
			if (telephonyManager.getNetworkType() != TelephonyManager.NETWORK_TYPE_UNKNOWN) {
				enable = true;
				Log.i(TAG, "移动网络已开启");
			}
		}
		return enable;
	}

	/**
	 * 判断Wifi是否开启
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWIFIEnabled(Context context) {
		boolean enable = false;
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled()) {
			enable = true;
			Log.i(TAG, "WIFI已开启");
		}
		return enable;
	}

	/**
	 * 判断移动网络连接是否成功
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetContected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobileNetworkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetworkInfo.isConnected()) {

			Log.i(TAG, "移动网络已连接");
			return true;
		}
		Log.i(TAG, "移动网络未连接");
		return false;
	}

	/**
	 * 判断Wifi是否连接成功
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiContected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifiNetworkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetworkInfo.isConnected()) {

			Log.i(TAG, "Wifi网络已连接");
			return true;
		}
		Log.i(TAG, "Wifi网络未连接");
		return false;
	}
}

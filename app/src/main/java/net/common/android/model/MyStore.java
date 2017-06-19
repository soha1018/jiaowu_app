/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:Login.java
 */
package net.common.android.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class MyStore {
		public static class Attr{
			public static final String USERNAME = "user_name";
			public static final String AVATOR = "avator";
			public static final String POINT = "point";
			public static final String PREDEPOIT = "predepoit";
		}
		
		private String username;
		private String avator;
		private String point;
		private String predepoit;
		
		public MyStore() {
		}
		
		public MyStore(String username, String avator, String point,
				String predepoit) {
			super();
			this.username = username;
			this.avator = avator;
			this.point = point;
			this.predepoit = predepoit;
		}

		public static MyStore  newInstanceList(String json){
			MyStore bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				if(obj.length()> 0){
					 String username = obj.optString(Attr.USERNAME);
					 String avator = obj.optString(Attr.AVATOR);
					 String point = obj.optString(Attr.POINT);
					 String predepoit = obj.optString(Attr.PREDEPOIT);
					 bean = new MyStore(username, avator, point, predepoit);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getAvator() {
			return avator;
		}

		public void setAvator(String avator) {
			this.avator = avator;
		}

		public String getPoint() {
			return point;
		}

		public void setPoint(String point) {
			this.point = point;
		}

		public String getPredepoit() {
			return predepoit;
		}

		public void setPredepoit(String predepoit) {
			this.predepoit = predepoit;
		}

		@Override
		public String toString() {
			return "MyStore [username=" + username + ", avator=" + avator
					+ ", point=" + point + ", predepoit=" + predepoit + "]";
		}
}

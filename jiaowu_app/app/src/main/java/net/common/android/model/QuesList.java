/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:GoodsList.java
 */
package net.common.android.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 在线评教的bean类
 */
public class QuesList {
		public static class Attr{
			public static final String GOODS_ID = "id";
			public static final String GOODS_NAME = "name";
		}
		private String id;
		private String name;
		





public QuesList(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}



//		public Attr(String id, String nickname, String phonenum) {
		public static ArrayList<QuesList> newInstanceList(String jsonDatas){
			ArrayList<QuesList> AdvertDatas = new ArrayList<QuesList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.GOODS_ID);
					String name = obj.optString(Attr.GOODS_NAME);
					QuesList bean =new QuesList(id, name);
					//System.out.println("goodlist-->" + bean.toString());
					AdvertDatas.add(bean);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}



		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}










		

}

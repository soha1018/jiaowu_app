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
 * 学院消息通知的Bean类
 */
public class TypeList2 {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_NAME = "title";
			public static final String TYPE_ZUOBIAO = "content";
			public static final String TYPE_IMAGE = "image_url";
			public static final String TYPE_DESC = "updatetime";
		
		}
		private String id;
		private String title;
		private String content;
		private String image_url;
		private String updatetime;
		
		public TypeList2() {
		}
		















		public TypeList2(String id, String title, String content,
				String image_url, String updatetime) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.image_url = image_url;
			this.updatetime = updatetime;
		}
















		public static ArrayList<TypeList2> newInstanceList(String jsonDatas){
			ArrayList<TypeList2> AdvertDatas = new ArrayList<TypeList2>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String type_id = obj.optString(Attr.TYPE_ID);
					String title = obj.optString(Attr.TYPE_NAME);
					String content = obj.optString(Attr.TYPE_ZUOBIAO);
					String type_image = obj.optString(Attr.TYPE_IMAGE);
					String updatetime = obj.optString(Attr.TYPE_DESC);
					TypeList2 bean =new TypeList2(type_id,title,content,type_image,updatetime);
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
















		public String getTitle() {
			return title;
		}
















		public void setTitle(String title) {
			this.title = title;
		}
















		public String getContent() {
			return content;
		}
















		public void setContent(String content) {
			this.content = content;
		}
















		public String getImage_url() {
			return image_url;
		}
















		public void setImage_url(String image_url) {
			this.image_url = image_url;
		}
















		public String getUpdatetime() {
			return updatetime;
		}
















		public void setUpdatetime(String updatetime) {
			this.updatetime = updatetime;
		}


















		

}

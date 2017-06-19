/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:GoodsList.java
 */
package net.common.android.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 返回结果的Bean类
 */
public class ResultObject {
		public static class Attr{
//			public static final String TYPE_ID = "id";
			public static final String TYPE_SUDENTNO = "name_";
			public static final String TYPE_CONTENT = "count_";
		
		}
//		private String id;
		private String content;
		private String studentno;
		
		public ResultObject() {
		}
		







		public ResultObject( String content, String studentno) {
			super();
			this.content = content;
			this.studentno = studentno;
		}








		public static List<Map<String, Object>> newInstanceList(String jsonDatas){
			List<Map<String, Object>> AdvertDatas = new ArrayList<Map<String,Object>>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					Double content = obj.optDouble(Attr.TYPE_CONTENT);
					String studentno = obj.optString(Attr.TYPE_SUDENTNO);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name_", studentno);
					map.put("count_", content);
					AdvertDatas.add(map);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}










		public String getContent() {
			return content;
		}








		public void setContent(String content) {
			this.content = content;
		}








		public String getStudentno() {
			return studentno;
		}








		public void setStudentno(String studentno) {
			this.studentno = studentno;
		}









		

}

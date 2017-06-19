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
 * 选课的Bean类
 */
public class ArticleList {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_SUDENTNO = "studentno";
			public static final String TYPE_LESSION = "lession";
			public static final String TYPE_SCORE = "score";
		
		}
		private String id;
		private String lession;
		private String studentno;
		private String score;
		
		public ArticleList() {
		}
		






		public ArticleList(String id, String studentno,String lession, String score) {
			super();
			this.id = id;
			this.studentno = studentno;
			this.score = score;
			this.lession = lession;
		}


		public static ArrayList<ArticleList> newInstanceList(String jsonDatas){
			ArrayList<ArticleList> AdvertDatas = new ArrayList<ArticleList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.TYPE_ID);
					String lession = obj.optString(Attr.TYPE_LESSION);
					String score = obj.optString(Attr.TYPE_SCORE);
					String studentno = obj.optString(Attr.TYPE_SUDENTNO);
					ArticleList bean =new ArticleList(id,studentno,lession,score);
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







		public String getLession() {
			return lession;
		}







		public void setLession(String lession) {
			this.lession = lession;
		}







		public String getStudentno() {
			return studentno;
		}







		public void setStudentno(String studentno) {
			this.studentno = studentno;
		}







		public String getScore() {
			return score;
		}







		public void setScore(String score) {
			this.score = score;
		}


















		

}

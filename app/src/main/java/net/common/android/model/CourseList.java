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
 * 课程的Bean类
 */
public class CourseList {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_NAME = "name";
			public static final String TYPE_ROOM = "device";//课时
			public static final String TYPE_TEACH = "description";//总教师
			public static final String TYPE_START = "start";
			public static final String TYPE_STEP = "step";
			public static final String TYPE_BANJI = "banji";
			public static final String TYPE_WEEK = "week";
		
		}
		private String id;
		private String name;
		private String room;
		private String teach;
		private int start;
		private int step;
		private String banji;
		private int week;
		
		public CourseList() {
		}
		


		public CourseList(String id, String name, String room, String teach,
				int start, int step, String banji, int week) {
			super();
			this.id = id;
			this.name = name;
			this.room = room;
			this.teach = teach;
			this.start = start;
			this.step = step;
			this.banji = banji;
			this.week = week;
		}




		public static ArrayList<CourseList> newInstanceList(String jsonDatas){
			ArrayList<CourseList> AdvertDatas = new ArrayList<CourseList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.TYPE_ID);
					String banji = obj.optString(Attr.TYPE_BANJI);
					String name = obj.optString(Attr.TYPE_NAME);
					String room = obj.optString(Attr.TYPE_ROOM);
					int start = obj.optInt(Attr.TYPE_START);
					int step = obj.optInt(Attr.TYPE_STEP);
					String teach = obj.optString(Attr.TYPE_TEACH);
					int week = obj.optInt(Attr.TYPE_WEEK);
					CourseList bean =new CourseList(id,name,room,teach,start,step,banji,week);
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



		public String getRoom() {
			return room;
		}



		public void setRoom(String room) {
			this.room = room;
		}



		public String getTeach() {
			return teach;
		}



		public void setTeach(String teach) {
			this.teach = teach;
		}



		public int getStart() {
			return start;
		}



		public void setStart(int start) {
			this.start = start;
		}



		public int getStep() {
			return step;
		}



		public void setStep(int step) {
			this.step = step;
		}



		public String getBanji() {
			return banji;
		}



		public void setBanji(String banji) {
			this.banji = banji;
		}



		public int getWeek() {
			return week;
		}



		public void setWeek(int week) {
			this.week = week;
		}





		

}

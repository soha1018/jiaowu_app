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
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class RoomList {
		public static class Attr{
			public static final String GOODS_ID = "id";
			public static final String GOODS_NAME = "name";
			public static final String GOODS_TEL = "tel";
			public static final String GOODS_MAIL = "mail";
			public static final String GOODS_JOB = "job";
			public static final String GOODS_DEPT = "dept";
		
			public static final String GOODS_COUNT = "countno";
			public static final String GOODS_TYPE = "type";
			public static final String GOODS_STATUS = "status";
		}
		private String id;
		private String name;
		private String tel;
		private String mail;
		private String job;
		private String dept;
		private String countno;
		private String type;
		private String status;
		





public RoomList(String id, String name, String tel, String mail,
				String job, String dept, String countno, String type, String status) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.mail = mail;
			this.job = job;
			this.dept = dept;
			this.countno = countno;
			this.type = type;
			this.status = status;
		}



//		public Attr(String id, String nickname, String phonenum) {
		public static ArrayList<RoomList> newInstanceList(String jsonDatas){
			ArrayList<RoomList> AdvertDatas = new ArrayList<RoomList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.GOODS_ID);
					String name = obj.optString(Attr.GOODS_NAME);
					String tel = obj.optString(Attr.GOODS_TEL);
					String mail = obj.optString(Attr.GOODS_MAIL);
					String job = obj.optString(Attr.GOODS_JOB);
					String dept = obj.optString(Attr.GOODS_DEPT);
					String countno = obj.optString(Attr.GOODS_COUNT);
					String type = obj.optString(Attr.GOODS_TYPE);
					String status = obj.optString(Attr.GOODS_STATUS);
					RoomList bean =new RoomList(id, name, tel,mail,job,dept,countno,type,status);
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



		public String getTel() {
			return tel;
		}



		public void setTel(String tel) {
			this.tel = tel;
		}



		public String getMail() {
			return mail;
		}



		public void setMail(String mail) {
			this.mail = mail;
		}



		public String getJob() {
			return job;
		}



		public void setJob(String job) {
			this.job = job;
		}



		public String getDept() {
			return dept;
		}



		public void setDept(String dept) {
			this.dept = dept;
		}



		public String getCountno() {
			return countno;
		}



		public void setCountno(String countno) {
			this.countno = countno;
		}



		public String getType() {
			return type;
		}



		public void setType(String type) {
			this.type = type;
		}



		public String getStatus() {
			return status;
		}



		public void setStatus(String status) {
			this.status = status;
		}












		

}

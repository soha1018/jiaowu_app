/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:Login.java
 */
package net.common.android.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登陆的Bean类
 */
public class Login {
	public static class Attr {
		public static final String KEY = "key";
		public static final String USERNAME = "username";
		public static final String STATUS = "status";
		public static final String STUDENTNO = "studentno";
		public static final String BANJI = "banji";

	}

	private String key;
	private String username;
	private String status;
	private String studentno;
	private String banji;

	public Login() {
	}


	public Login(String key, String username, String status, String studentno, String banji) {
		super();
		this.key = key;
		this.username = username;
		this.status = status;
		this.studentno = studentno;
		this.banji = banji;

	}


	public static Login newInstanceList(String json) {
		Login bean = null;
		try {
			JSONObject obj = new JSONObject(json);
			String userstr = obj.optString("jsonString");
			JSONObject user = new JSONObject(userstr);


//				System.out.println(obj.optString("username"));
			if (obj.length() > 0) {
				String username = user.optString("username");
				String key = user.optString("id");
				String status = user.optString("status");
				String studentno = user.optString("qqnum");
				String banji = user.optString("banji");
				bean = new Login(key, username, status, studentno, banji);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bean;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getStudentno() {
		return studentno;
	}


	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}


	public String getBanji() {
		return banji;
	}


	public void setBanji(String banji) {
		this.banji = banji;
	}


	@Override
	public String toString() {
		return "Login [key=" + key + ", username=" + username + "]";
	}

}

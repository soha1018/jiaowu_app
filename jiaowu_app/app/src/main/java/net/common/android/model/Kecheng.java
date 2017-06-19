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
public class Kecheng {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_NAME = "name";
			public static final String TYPE_DESC = "description";
			public static final String TYPE_DEVICE = "device";
			public static final String TYPE_CAPB = "capability";
		
		}
		private String id;
		private String name;
		private String description;
		private String device;
		private String capability;
		
		public Kecheng() {
		}
		









public Kecheng(String id, String name, String description,
				String device, String capability) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.device = device;
			this.capability = capability;
		}










/**
 

public MeetRoomList(String id, String name, String description,
				String device, String capability) {
 * @return
 */

		public static ArrayList<Kecheng> newInstanceList(String jsonDatas){
			ArrayList<Kecheng> AdvertDatas = new ArrayList<Kecheng>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.TYPE_ID);
					String name = obj.optString(Attr.TYPE_NAME);
					String description = obj.optString(Attr.TYPE_DESC);
					String device = obj.optString(Attr.TYPE_DEVICE);
					String capability = obj.optString(Attr.TYPE_CAPB);
					Kecheng bean =new Kecheng(id,name,description,device,capability);
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










public String getDescription() {
	return description;
}










public void setDescription(String description) {
	this.description = description;
}










public String getDevice() {
	return device;
}










public void setDevice(String device) {
	this.device = device;
}










public String getCapability() {
	return capability;
}










public void setCapability(String capability) {
	this.capability = capability;
}










		

}

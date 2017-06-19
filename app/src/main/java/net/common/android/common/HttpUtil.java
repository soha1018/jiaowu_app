package net.common.android.common;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 请求Url地址的常量类
 */
public class HttpUtil extends Thread {

	//	public static final String URL_BASE = "http://10.0.2.2:8080/travel/";
//	 public static final String URL_BASE="http://192.168.1.222:8080/travel/";
	public static String URL_BASE = "http://192.168.1.102:8080/jiaowu/";
	public static final String URL_COURSE = URL_BASE
			+ "meetroom_listbanjijson.action?banji=";
	public static final String URL_COURSE2 = URL_BASE
			+ "meetroom_listgerenjson.action?userid=";
	public static final String URL_ROOMDETAIL = URL_BASE
			+ "meetroom_detailjson.action?id=";
	public static final String URL_ROOMBOOKCOMMENTSL = URL_BASE
			+ "comments_listjson.action?bioid=";
	public static final String URL_HISTORYADD = URL_BASE
			+ "history_save.action?";
	public static final String URL_QUESLST = URL_BASE + "question_listjson.action";
	public static final String URL_BIODETAIL = URL_BASE
			+ "biotech_detailjson.action?id=";
	public static final String URL_ADDJINGDIANFOLDER = URL_BASE
			+ "folder_save.action?";
	public static final String URL_PHOTO_ADD = URL_BASE
			+ "photo_saveapp.action?";
	public static final String URL_ANSWERADD = URL_BASE
			+ "question_save_answer.action?";
	public static final String URL_CHENGJIADD = URL_BASE
			+ "student_save.action?";
	public static final String URL_HUOIJANGADD = URL_BASE
			+ "student_savehj.action?";
	public static final String URL_CHENGJILIST = URL_BASE
			+ "student_loadchengji.action?";
	public static final String URL_HUOJIANGLIST = URL_BASE
			+ "student_loadhuojiang.action?";
	public static final String URL_GETDATA = URL_BASE
			+ "student_gettubiaodata.action";
	public static final String URL_MEETROOMLIST = URL_BASE
			+ "meetroom_listbixiu.action";
	public static final String URL_XUANKE = URL_BASE
			+ "meetroom_save_.action?";
	public static final String URL_DELXUANKE = URL_BASE
			+ "meetroom_del_.action?";
	public static final String URL_MEETROOMLIST2 = URL_BASE
			+ "meetroom_listxuanxiu.action";
	public static final String URL_PRODUCTLIST0 = URL_BASE
			+ "product_listjson0.action";
	public static final String URL_PRODUCTLIST1 = URL_BASE
			+ "product_listjson1.action";
	public static final String URL_PRODUCTLIST2 = URL_BASE
			+ "product_listjson2.action";
	public static final String URL_PRODUCTLIST3 = URL_BASE
			+ "product_listjson3.action";
	public static final String URL_PRODUCTLIST4 = URL_BASE
			+ "product_listjson4.action";
	public static final String URL_PRODUCTLIST5 = URL_BASE
			+ "product_listjson5.action";
	public static final String URL_TYPELIST = URL_BASE
			+ "biotech_listjson.action";
	public static final String URL_TYPELIST0 = URL_BASE
			+ "biotech_listjson0.action";
	public static final String URL_TYPELIST1 = URL_BASE
			+ "biotech_listjson1.action";
	public static final String URL_TYPELIST2 = URL_BASE
			+ "biotech_listjson2.action";
	public static final String URL_SHOPLIST = URL_BASE
			+ "ticket_listorderjson.action?";
	public static final String URL_HISTORYLIST = URL_BASE
			+ "history_listjson.action?";
	public static final String URL_TICKETBUY = URL_BASE + "ticket_buy.action?";
	public static final String URL_COMMENTSADD = URL_BASE
			+ "comments_save.action?";
	public static final String URL_PHONEADD = URL_BASE
			+ "phone_save.action?";
	public static final String URL_EVENTADD = URL_BASE
			+ "message_save.action?";
	public static final String URL_MEETBOOKADD = URL_BASE
			+ "meetbook_save.action?";
	public static final String URL_MESSAGEADD = URL_BASE
			+ "message_save.action?";
	public static final String URL_PHONELST = URL_BASE + "phone_listjson.action";
	public static final String URL_EMPPLOYEELST = URL_BASE + "employee_listjson.action";
	public static final String URL_EMPPLOYEELST0 = URL_BASE + "employee_listjson_0.action";
	public static final String URL_EMPPLOYEELST1 = URL_BASE + "employee_listjson_1.action";
	public static final String URL_MEETBOOKLST = URL_BASE + "meetbook_listbyuserjson.action?";
	public static final String URL_MEETBOOKDEL = URL_BASE + "meetbook_delete.action?";
	public static final String URL_MESSAGELST = URL_BASE + "message_listjson.action?";
	// 基础URL
	public static final String URL_ORDERCONFIRM = URL_BASE
			+ "confirmjson.action?";
	public static final String URL_CARTORDER = URL_BASE
			+ "shopcart_order.action?";
	public static final String URL_ORDERLIST = URL_BASE
			+ "order_listjson.action?";
	public static final String URL_CARTORDER2 = URL_BASE + "order_save.action";
	public static final String URL_CARTDEL = URL_BASE + "shopcart_del.action?";
	public static final String URL_REGISTER = URL_BASE + "user_reg.action?";
	public static final String URL_SHOPCART = URL_BASE
			+ "shopcart_list.action?";
	public static final String URL_ADD_CART = URL_BASE
			+ "shopcart_save.action?";
	public static final String URL_LOGIN = URL_BASE + "user_login.action?";
	public static final String URL_GOODSDETAIL = URL_BASE
			+ "detailjson.action?goods_id=";
	public static final String URL_USERDATA = URL_BASE
			+ "user_loaddata.action?";
	public static final String URL_PUBWISH = "http://192.168.9.45:8080/wishbottle/wish_save.action?";
	public static final String URL_USERWISHLST = "http://192.168.9.45:8080/wishbottle/wish_loadlist.action?";

	public static final String URL_BASEUPLOAD = URL_BASE + "upload/";
	public static final String URL_USERWISHREPLYLST = "http://192.168.9.45:8080/wishbottle/wishcomment_loadCommentReply.action?";
	public static final String URL_LOADWISHDETAIL = "http://192.168.9.45:8080/wishbottle/wish_detail.action?";
	public static final String URL_SAVEWISHCOMMENT = "http://192.168.9.45:8080/wishbottle/wishcomment_save.action?";
	public static final String URL_SAVEWISHCOMMENTREPLY = "http://192.168.9.45:8080/wishbottle/wishcomment_addCommentReply.action?";

	// public static final String BASE_URL="http://192.168.0.164:8080/kycheck/";
	// 获得Get请求对象request
	public static HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url);
		return request;
	}

	// 获得Post请求对象request
	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		return request;
	}

/*	// 根据请求获得响应对象response
	public static HttpResponse getHttpResponse(HttpUtil.HttpPost request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}*/

	// 根据请求获得响应对象response
	public static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}

	// 发送Post请求，获得响应查询结果
	public static String queryStringForPost(String url) {
		// 根据url获得HttpPost对象
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}

	// 获得响应查询结果
	public static String queryStringForPost(HttpPost request) {
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}

	// 发送Get请求，获得响应查询结果
/*	public static String queryStringForGet(String url) {
		// 获得HttpGet对象
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}*/
}

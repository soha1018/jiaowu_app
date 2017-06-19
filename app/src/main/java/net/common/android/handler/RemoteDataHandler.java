package net.common.android.handler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.common.android.common.HttpHelper;
import net.common.android.model.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 * 用于发送HTTP请求并处理响应返回的数据的Handler
 * 
 * @author hjgang
 */
public class RemoteDataHandler {
	public static final String TAG = "RemoteDataLoader";
	private static final String _CODE = "code";
	private static final String _DATAS = "datas";
	private static final String _HASMORE = "hasmore";
	private static final String _COUNT = "count";
	private static final String _RESULT = "result";
	private static final String _LOGIN = "login";
	private static final String _URL = "url";
	// 线程池
	// private ExecutorService pool = Executors.newCachedThreadPool();
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6,
			30, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

	private RemoteDataHandler() {
	}

	public interface Callback {
		/**
		 * HTTP响应完成的回调方法
		 * 
		 * @param data
		 *            响应返回的数据对象
		 */
		public void dataLoaded(ResponseData data);
	}

	public interface StringCallback {

		public void dataLoaded(String str);
	}

	/**
	 * 异步GET请求封装2
	 * @param url
	 * @param callback
	 */
	public static void asyncGet2(final String url, final Callback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));

				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean(_HASMORE, false);

				try {
					String json = HttpHelper.get(url);
					if (json != null && !"".equals(json)
							&& !"null".equalsIgnoreCase(json)) {
						// 注意:目前服务器返回的JSON数据串中会有特殊字符（换行、回车）。需要处理一下
						json = json.replaceAll("\\x0a|\\x0d", "");
						JSONObject obj = new JSONObject(json);
						if (null != obj && obj.has(_CODE)) {
							msg.what = Integer.valueOf(obj.getString(_CODE));

							if (obj.has(_DATAS)) {
								JSONObject array = obj.getJSONObject(_DATAS);
								msg.obj = array.toString();
							}
							if (obj.has(_HASMORE)) {
								msg.getData().putBoolean(_HASMORE,
										obj.getBoolean(_HASMORE));
							}
							if (obj.has(_COUNT)) {
								msg.getData().putLong(_COUNT,
										obj.getLong(_COUNT));
							}

							if (obj.has(_RESULT)) {
								msg.getData().putString(_RESULT,
										obj.getString(_RESULT));
							}
						}
					} else {
						msg.what = HttpStatus.SC_BAD_REQUEST;
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}

				handler.sendMessage(msg);
			}
		});
	}

	/**
	 * 异步GET请求封装
	 * @param url
	 * @param callback
	 */
	public static void asyncGet(final String url, final Callback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));

				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean(_HASMORE, false);

				try {
					String json = HttpHelper.get(url);
					if (json != null && !"".equals(json)
							&& !"null".equalsIgnoreCase(json)) {
						// 注意:目前服务器返回的JSON数据串中会有特殊字符（换行、回车）。需要处理一下
						json = json.replaceAll("\\x0a|\\x0d", "");
						JSONObject obj = new JSONObject(json);
						if (null != obj && obj.has(_CODE)) {
							msg.what = Integer.valueOf(obj.getString(_CODE));

							if (obj.has(_DATAS)) {
								JSONArray array = obj.getJSONArray(_DATAS);
								msg.obj = array.toString();
							}
							if (obj.has(_HASMORE)) {
								msg.getData().putBoolean(_HASMORE,
										obj.getBoolean(_HASMORE));
							}
							if (obj.has(_COUNT)) {
								msg.getData().putLong(_COUNT,
										obj.getLong(_COUNT));
							}

							if (obj.has(_RESULT)) {
								msg.getData().putString(_RESULT,
										obj.getString(_RESULT));
							}
						}
					} else {
						msg.what = HttpStatus.SC_BAD_REQUEST;
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}

				handler.sendMessage(msg);
			}
		});
	}

	/**
	 * 异步GET请求封装
	 * @param url
	 * @param callback
	 */
	public static void asyncGet3(final String url, final Callback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));

				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean(_HASMORE, false);

				try {
					String json = HttpHelper.get(url);
					// 注意:目前服务器返回的JSON数据串中会有特殊字符（换行、回车）。需要处理一下
					json = json.replaceAll("\\x0a|\\x0d", "");
					JSONObject obj = new JSONObject(json);
					if (null != obj && obj.has(_CODE)) {
						msg.what = Integer.valueOf(obj.getString(_CODE));

						if (obj.has(_DATAS)) {
							JSONObject array = obj.getJSONObject(_DATAS);
							msg.obj = array.toString();
						}
						if (obj.has(_HASMORE)) {
							msg.getData().putBoolean(_HASMORE,
									obj.getBoolean(_HASMORE));
						}
						if (obj.has(_COUNT)) {
							msg.getData().putLong(_COUNT, obj.getLong(_COUNT));
						}

						if (obj.has(_RESULT)) {
							msg.getData().putString(_RESULT,obj.getString(_RESULT));
						}
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}

				handler.sendMessage(msg);
			}
		});
	}

	/**
	 * 异步GET请求封装
	 * 
	 * @param url
	 * @param callback
	 */
	public static void asyncStringGet(final String url, final Callback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));

				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean(_HASMORE, false);

				try {
					String json = HttpHelper.get(url);

					// 注意:目前服务器返回的JSON数据串中会有特殊字符（换行、回车）。需要处理一下
					if (json != null && !"".equals(json)
							&& !"null".equalsIgnoreCase(json)) {
						json = json.replaceAll("\\x0a|\\x0d", "");
						System.out.println("json in thread-->" + json);
						JSONObject obj = new JSONObject(json);
    					if (null != obj && obj.has(_CODE)) {
    						msg.what = Integer.valueOf(obj.getString(_CODE));
    
    						if (obj.has(_DATAS)) {
    							msg.obj = obj.getString(_DATAS);
    						}
    						 if(obj.has(_HASMORE)){
    						 msg.getData().putBoolean(_HASMORE,
    						 obj.getBoolean(_HASMORE));
    						 }
    						 if(obj.has(_COUNT)){
    						 msg.getData().putLong(_COUNT, obj.getLong(_COUNT));
    						 }
    						
    						 if(obj.has(_RESULT)){
    						 msg.getData().putString(_RESULT,
    						 obj.getString(_RESULT));
    						 }
    					}
					}else {
						msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}

				handler.sendMessage(msg);
			}
		});
	}

	/**
	 * 同步GET请求封装
	 * 
	 * @param url
	 * @param pagesize
	 * @param pageno
	 * @return
	 */
	public static ResponseData get(final String url) {
		ResponseData rd = new ResponseData();

		try {
			String json = HttpHelper.get(url);

			// 注意:目前服务器返回的JSON数据串中会有特殊字符（如换行）。需要处理一下
			json = json.replaceAll("\\x0a|\\x0d", "");

			JSONObject obj = new JSONObject(json);
			if (null != obj && obj.has(_CODE)) {
				rd.setCode(obj.getInt(_CODE));

				if (obj.has(_DATAS)) {
					JSONArray array = obj.getJSONArray(_DATAS);
					rd.setJson(array.toString());
				}
				if (obj.has(_HASMORE)) {
					rd.setHasMore(obj.getBoolean(_HASMORE));
				}

				if (obj.has(_RESULT)) {
					rd.setResult(obj.getString(_RESULT));
				}

				if (obj.has(_COUNT)) {
					rd.setCount(obj.getLong(_COUNT));
				}
			}
		} catch (IOException e) {
			rd.setCode(HttpStatus.SC_REQUEST_TIMEOUT);
			e.printStackTrace();
		} catch (JSONException e) {
			rd.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return rd;
	}

	public static ResponseData post(final String url,
			final HashMap<String, String> params) {
		ResponseData rd = new ResponseData();
		try {
			String json = HttpHelper.post(url, params);

			// 注意:目前服务器返回的JSON数据串中会有特殊字符（如换行）。需要处理一下
			json = json.replaceAll("\\x0a|\\x0d", "");

			JSONObject obj = new JSONObject(json);
			if (null != obj && obj.has(_CODE)) {
				rd.setCode(obj.getInt(_CODE));

				if (obj.has(_DATAS)) {
					JSONArray array = obj.getJSONArray(_DATAS);
					rd.setJson(array.toString());
				}
				if (obj.has(_HASMORE)) {
					rd.setHasMore(obj.getBoolean(_HASMORE));
				}

				if (obj.has(_RESULT)) {
					rd.setResult(obj.getString(_RESULT));
				}

				if (obj.has(_COUNT)) {
					rd.setCount(obj.getLong(_COUNT));
				}
			}
		} catch (IOException e) {
			rd.setCode(HttpStatus.SC_REQUEST_TIMEOUT);
			e.printStackTrace();
		} catch (JSONException e) {
			rd.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return rd;
	}

	/**
	 * 异步的POST请求
	 * 
	 * @param url
	 * @param params
	 * @param callback
	 */
	public static void asyncPost(final String url,
			final HashMap<String, String> params, final Callback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));


				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean("hasMore", false);
				try {
					String json = HttpHelper.post(url, params);

					// 注意:目前服务器返回的JSON数据串中会有特殊字符（如换行）。需要处理一下
					json = json.replaceAll("\\x0a|\\x0d", "");
					JSONObject obj = new JSONObject(json);
					if (null != obj && obj.has(_CODE)) {
						msg.what = Integer.valueOf(obj.getString(_CODE));

						if (obj.has(_DATAS)) {
							JSONArray array = obj.getJSONArray(_DATAS);
							msg.obj = array.toString();
						}

						if (obj.has(_RESULT)) {
							msg.getData().putString(_RESULT,
									obj.getString(_RESULT));
						}
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}

				handler.sendMessage(msg);
			}
		});
	}

	/**
	 * 异步的POST请求
	 * 
	 * @param url
	 * @param params
	 * @param callback
	 */
	public static void asyncPost2(final String url,
			final HashMap<String, String> params, final Callback callback) {
		
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));
				data.setLogin(msg.getData().getInt(_LOGIN));


				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean("hasMore", false);
				try {
					String json = HttpHelper.post(url, params);
					//Log.d("url is: ", url);
					System.out.println("url-->" + url);
					// 注意:目前服务器返回的JSON数据串中会有特殊字符（如换行）。需要处理一下
					json = json.replaceAll("\\x0a|\\x0d", "");
					JSONObject obj = new JSONObject(json);
					if (null != obj && obj.has(_CODE)) {
						msg.what = Integer.valueOf(obj.getString(_CODE));

						if (obj.has(_DATAS)) {
							// JSONArray array = obj.getJSONArray(_DATAS);
							// msg.obj = array.toString();
							msg.obj = obj.getString(_DATAS);
						}
						
						 if(obj.has(_HASMORE)){
							 msg.getData().putBoolean(_HASMORE,
							 obj.getBoolean(_HASMORE));
							 }
						 if(obj.has(_LOGIN)){
							 msg.getData().putInt(_LOGIN,
							 Integer.parseInt(obj.getString(_LOGIN)));
						}else{
							msg.getData().putInt(_LOGIN,-1);
						} 

						if (obj.has(_RESULT)) {
							msg.getData().putString(_RESULT,
									obj.getString(_RESULT));
						}
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}

				System.out.println("thread id in thread pool-->" + Thread.currentThread().getId());
				
				handler.sendMessage(msg);
			}
		});
	}
	
	/**
	 * 异步的多消息体POST请求封装
	 * 
	 * @param url
	 * @param params
	 * @param fileMap
	 * @param callback
	 */
	public static void asyncMultipartPost(final String url,
			final HashMap<String, String> params,
			final HashMap<String, File> fileMap, final Callback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));
				Log.d(TAG, data.toString());

				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean("hasMore", false);
				try {
					String json = HttpHelper
							.multipartPost(url, params, fileMap);

					// 注意:目前服务器返回的JSON数据串中会有特殊字符（如换行）。需要处理一下
					json = json.replaceAll("\\x0a|\\x0d", "");

					JSONObject obj = new JSONObject(json);
					if (null != obj && obj.has(_CODE)) {
						msg.what = Integer.valueOf(obj.getString(_CODE));

						if (obj.has(_DATAS)) {
							JSONArray array = obj.getJSONArray(_DATAS);
							msg.obj = array.toString();
						}

						if (obj.has(_RESULT)) {
							msg.getData().putString(_RESULT,
									obj.getString(_RESULT));
						}
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				}

				handler.sendMessage(msg);
			}
		});
	}

	/**
	 * 异步get分页数据请求封装
	 * 
	 * @param url
	 * @param pagesize
	 * @param pageno
	 * @param callback
	 */
	public static void asyncGet(final String url, final int pagesize,
			final int pageno, final Callback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ResponseData data = new ResponseData();
				data.setCode(msg.what);
				data.setHasMore(msg.getData().getBoolean(_HASMORE));
				data.setJson((String) msg.obj);
				data.setResult(msg.getData().getString(_RESULT));
				data.setCount(msg.getData().getLong(_COUNT));

				callback.dataLoaded(data);
			}
		};
		threadPool.execute(new Runnable() {
			@Override
			public void run() {

				Message msg = handler.obtainMessage(HttpStatus.SC_OK);
				msg.getData().putBoolean(_HASMORE, false);

				String realUrl = url + "&page=" + pagesize + "&size=" + pageno;

				try {
					Thread.sleep(1000);

					String json = HttpHelper.get(realUrl);
					// 注意:目前服务器返回的JSON数据串中会有特殊字符（如换行）。需要处理一下
					json = json.replaceAll("\\x0a|\\x0d", "");

					JSONObject obj = new JSONObject(json);
					if (null != obj && obj.has(_CODE)) {
						msg.what = Integer.valueOf(obj.getString(_CODE));

						if (obj.has(_DATAS)) {
							JSONArray array = obj.getJSONArray(_DATAS);
							msg.obj = array.toString();

							if (pagesize == array.length()) {
								msg.getData().putBoolean(_HASMORE, true);
							}
						}
						if (obj.has(_COUNT)) {
							msg.getData().putLong(_COUNT,
									Long.valueOf(obj.getString(_COUNT)));
						}

						if (obj.has(_RESULT)) {
							msg.getData().putString(_RESULT,
									obj.getString(_RESULT));
						}
					}
				} catch (IOException e) {
					msg.what = HttpStatus.SC_REQUEST_TIMEOUT;
					e.printStackTrace();
				} catch (JSONException e) {
					msg.what = HttpStatus.SC_INTERNAL_SERVER_ERROR;
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				handler.sendMessage(msg);
			}
		});
	}
}

package net.common.android.common;

import android.os.Environment;

/**
 * 常量类
 * @author hjgang
 */
public final class Constants {
	/** 系统初始化配置文件名 */
	public static final String SYSTEM_INIT_FILE_NAME = "sysini";
	/** 本地缓存目录 */
	public static final String CACHE_DIR;
	
	public static final int PAGESIZE=10;
	/** 表情缓存目录 */
	public static final String CACHE_DIR_SMILEY;
	/** 图片缓存目录 */
	public static final String CACHE_DIR_IMAGE;
	/** 待上传图片缓存目录 */
	public static final String CACHE_DIR_UPLOADING_IMG;
	
	
	
	
	//sdcard dir
	public static final String SD_DIR = "cangshengwangsystemapk";
	
	public static final String SD_DIR_TMP = "cangshengwang/tmp";
    
	//dialog
	public static final int DIALOG_YES_NO_MESSAGE = 1;
	public static final int DIALOG_APK_UPDATE = 2;
	public static final int PROGRESSBAR_WAIT = 3;
	public static final int IMAGE_UPLOAD = 8;
	public static final int VIDEO_UPLOAD = 10;
	public static final int IMAGE_LOAD = 9;
	public static final int CHOOSE_FX = 4;
	public static final int URL_WAIT = 5;
	public static final int DIALOG_IR_UPDATE = 6;
	
	public static final int TIMEOUT_10 = 10000;  
	public static final int TIMEOUT_15 = 15000; 
	
	public static final String APP_BORADCASTRECEIVER="www.cangshengwang.net";
	public static final String APP_BORADCASTRECEIVER2="www.cangshengwang.net2";

	static {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CangShengWang/";
		} else {
			CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/CangShengWang/";
		}
		
		CACHE_DIR_SMILEY = CACHE_DIR + "/smiley";
		CACHE_DIR_IMAGE = CACHE_DIR + "/pic";
		CACHE_DIR_UPLOADING_IMG = CACHE_DIR + "/uploading_img";
	}
	private Constants(){}
	
	/**************************************************************************/
	/** 数据库版本号 */
	public static final int DB_VERSION = 2;
	
	//log tag
	public static final String TAG = "System.out";
	
	public static final int BYTE_SIZE_INT = 1024;
	/** 数据库名 */
	public static final String DB_NAME = "cangshengwang_shop.db";
	
	/** 与服务器端连接的协议名 */
	public static final String PROTOCOL = "http://";
	/** 服务器IP */
	public static final String HOST = "192.168.9.45";//www.multibuy.cn//www.shopnctest.com/b2b2c/2014/test1/mobile/
	/** 服务器端口号 */
	public static final String PORT = ":8080";
	/** 应用上下文名 */
	public static final String APP = "/wishbottle/";//
	public static final String APP_NAME = "WishBottleApp.apk";
	/** 应用上下文完整路径 */
	public static final String URL_CONTEXTPATH = "http://www.cangshengwang.com/mobile/index.php?";
	public static final String URL_CONTEXTPATH_WISHBOTTLE = PROTOCOL + HOST +PORT+ APP ;
	public static final String URL_NEW_VERSION = PROTOCOL + HOST + "/app/" +APP_NAME;
	
	/**
	 * 首页请求地址
	 * 
	 * */
	public static final String URL_HOME=URL_CONTEXTPATH+"act=index";
	/**
	 * 一级分类请求地址
	 * 
	 * */
	public static final String URL_GOODSCLASS=URL_CONTEXTPATH+"act=goods_class";
	
	/**
	 * 商品列表请求地址
	 * 
	 * */
	public static final String URL_GOODSLIST=URL_CONTEXTPATH+"act=goods&op=goods_list";
	
	/**
	 * IC卡充值API
	 * 
	 * */
	public static final String URL_ICCARDCHARGE=URL_CONTEXTPATH+"act=app_server&op=iccard_add";
	/**
	 * 加载首页帖子
	 * 
	 * */
	public static final String URL_TOP_THEME=URL_CONTEXTPATH+"act=circle&op=top_theme";
	/*
	 * 朋友圈主题列表
	 */
	public static final String URL_CIRCLE_THEMES_LIST=URL_CONTEXTPATH+"act=circle";
	/*
	 * 
	 * 点赞
	 */
	
	public static final String URL_CIRCLE_THEMES_LIKE_CLICK=URL_CONTEXTPATH+"act=circle&op=theme_likeyes";
	
	/*
	 * 
	 * 
	 */
	public static final String URL_CIRCLE_CHECK_MEMBER=URL_CONTEXTPATH+"act=circle&op=check_member";
	/*
	 *加载帖子详情
	 */
	public static final String URL_CIRCLE_THEMES_DETAIL=URL_CONTEXTPATH+"act=circle&op=theme_detail";
	/*
	 *加载通知详情
	 */
	public static final String URL_NOTIFICATION_DETAIL=URL_CONTEXTPATH+"act=circle&op=article_detail";
	
	/*
	 * 帖子回复
	 */
	public static final String URL_CIRCLE_THEMES_REPLY=URL_CONTEXTPATH+"act=circle&op=reply";
	
	/*
	 * 获取版本号
	 */
	
	//http://www.cangshengwang.com/mobile/index.php?act=app_server&op=iccard_add
	//http://www.cangshengwang.com/mobile/index.php?act=app_server&op=get_version
	public static final String URL_NEW_VERSION_NO = URL_CONTEXTPATH+"act=app_server&op=get_version";
	/**
	 * 找回密码 
	 */
	public static final String URL_FIND_PWD = URL_CONTEXTPATH+"act=app_server&op=find_password";
	/**
	 * 订单评论地址
	 * */
	//http://www.cangshengwang.com/mobile/index.php?act=member_evaluate&op=add_evaluate
	public static final String URL_ORDER_COMMENT_SUBMIT=URL_CONTEXTPATH+"act=member_evaluate&op=add_evaluate";
	
	/**
	 * 商品详情请求地址
	 * */
	
	
	public static final String URL_GOODSDETAILS=URL_CONTEXTPATH+"act=goods&op=goods_detail";
	
	
	/**
	 * 商品详情评论请求地址
	 * 
	http://cangshengwang.com/mobile/index.php?act=goods&op=comments&goods_id=4343

	 * */
	public static final String URL_GOODSDETAIL_COMMENTS=URL_CONTEXTPATH+"act=goods&op=comments";
	/**
	 * 登录请求地址
	 * */
	public static final String URL_LOGIN=URL_CONTEXTPATH+"act=login";
	/**
	 * 我的商城请求地址
	 * */
	public static final String URL_MYSTOIRE=URL_CONTEXTPATH+"act=member_index";
	/*
	 * 签到地址
	 */
	public static final String URL_SIGN=URL_CONTEXTPATH+"act=login&op=sign";
	
	/**
	 * 我的商城请求地址
	 * */
	public static final String URL_GOODS_DETAILS_WEB=URL_CONTEXTPATH+"act=goods&op=goods_body";
	/**
	 * 添加收藏请求地址
	 * */
	public static final String URL_ADD_FAVORITES=URL_CONTEXTPATH+"act=member_favorites&op=favorites_add";
	/**
	 * 收藏列表请求地址
	 * */
	public static final String URL_FAVORITES_LIST=URL_CONTEXTPATH+"act=member_favorites&op=favorites_list";
	/**
	 * 删除收藏请求地址
	 * */
	public static final String URL_FAVORITES_DELETE=URL_CONTEXTPATH+"act=member_favorites&op=favorites_del";
	/**
	 * 地址列表请求地址
	 * */
	public static final String URL_ADDRESS_LIST=URL_CONTEXTPATH+"act=member_address&op=address_list";
	/**
	 * 订单列表请求地址
	 * */
	public static final String URL_ORDER_LIST=URL_CONTEXTPATH+"act=member_order&op=order_list";
	/**
	 * 订单请求地址
	 * */
	//http://www.cangshengwang.com/mobile/index.php?act=member_evaluate&op=add&order_id=
	//http://www.cangshengwang.com/mobile/index.php?act=member_evaluate&op=show_evaluate
	public static final String URL_ORDER=URL_CONTEXTPATH+"act=member_evaluate&op=show_evaluate";
	/**
	 * 添加购物车请求地址
	 * */
	public static final String URL_ADD_CART=URL_CONTEXTPATH+"act=member_cart&op=cart_add";
	/**
	 * 获取登陆用户积分
	 */
	
	public static final String URL_MEMBER_POINTS=URL_CONTEXTPATH+"act=member_cart&op=get_member_points";
	/**
	 * 购物车列表请求地址
	 * */
	public static final String URL_CART_LIST=URL_CONTEXTPATH+"act=member_cart&op=cart_list";
	/**
	 * 购物车删除请求地址
	 * */
	public static final String URL_CART_DETELE=URL_CONTEXTPATH+"act=member_cart&op=cart_del";
	/**
	 * 注销登出请求地址
	 * */
	public static final String URL_LOGIN_OUT=URL_CONTEXTPATH+"act=logout";
	/**
	 * 地址详细信息请求地址
	 * */
	public static final String URL_ADDRESS_DETAILS=URL_CONTEXTPATH+"act=member_address&op=address_info";
	/**
	 * 地区列表请求地址
	 * */
	public static final String URL_GET_CITY=URL_CONTEXTPATH+"act=member_address&op=area_list";
	/**
	 * 地址编辑请求地址
	 * */
	public static final String URL_ADDRESS_EDIT=URL_CONTEXTPATH+"act=member_address&op=address_edit";
	/**
	 * 地址删除请求地址
	 * */
	public static final String URL_ADDRESS_DETELE=URL_CONTEXTPATH+"act=member_address&op=address_del";
	/**
	 * 地址添加请求地址
	 * */
	public static final String URL_ADDRESS_ADD=URL_CONTEXTPATH+"act=member_address&op=address_add";
	/**
	 * 在线帮助请求地址
	 * */
	public static final String URL_HELP=PROTOCOL + HOST + APP+"/help.html";
	/**
	 * 购买步骤1请求地址
	 * */
	public static final String URL_BUY_STEP1=URL_CONTEXTPATH+"act=member_buy&op=buy_step1";
	/**
	 * 购买步骤2请求地址
	 * */
	public static final String URL_BUY_STEP2=URL_CONTEXTPATH+"act=member_buy&op=buy_step2";
	/**
	 * 发票列表请求地址
	 * */
	public static final String URL_INVOICE_LIST=URL_CONTEXTPATH+"act=member_invoice&op=invoice_list";
	/**
	 * 发票内容列表请求地址
	 * */
	public static final String URL_INVOICE_CONTEXT_LIST=URL_CONTEXTPATH+"act=member_invoice&op=invoice_content_list";
	/**
	 * 添加发票请求地址
	 * */
	public static final String URL_INVOICE_ADD=URL_CONTEXTPATH+"act=member_invoice&op=invoice_add";
	/**
	 * 更换收货地址请求地址
	 * */
	public static final String URL_UPDATE_ADDRESS=URL_CONTEXTPATH+"act=member_buy&op=change_address";
	/**
	 * 验证密码请求地址
	 * */
	public static final String URL_CHECK_PASSWORD=URL_CONTEXTPATH+"act=member_buy&op=check_password";
	/**
	 * 订单取消(未付款)请求地址
	 * */
	public static final String URL_ORDER_CANCEL=URL_CONTEXTPATH+"act=member_order&op=order_cancel";
	/**
	 * 订单确认收货请求地址
	 * */
	public static final String URL_ORDER_RECEIVE=URL_CONTEXTPATH+"act=member_order&op=order_receive";
	/**
	 * 订单付款请求地址
	 * */
	public static final String URL_ORDER_PAYMENT=URL_CONTEXTPATH+"act=member_payment&op=pay";
	/**
	 *注册
	 * */
	public static final String URL_REGISTER=URL_CONTEXTPATH_WISHBOTTLE+"user_reguser.action";
	
	/*
	 * 
	 * 申请加入圈子
	 */
	public static final String URL_CIRCLE_APPLY=URL_CONTEXTPATH+"act=circle&op=apply";
	/**
	 *手机注册
	 * */
	public static final String URL_PHONE_REGISTER=URL_CONTEXTPATH+"act=app_server&op=register";
	/**
	 *发送验证码
	 * */
	public static final String URL_PHONE_REG_SEND_VALIDCODE=URL_CONTEXTPATH+"act=app_server&op=send_msg_code";
}

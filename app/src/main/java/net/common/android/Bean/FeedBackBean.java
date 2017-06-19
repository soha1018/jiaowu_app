package net.common.android.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 反馈信息的Bean类
 * Created by Administrator on 2017/6/13.
 */

public class FeedBackBean extends BmobObject implements Serializable{
	public String name;
	public String title;
	public String content;
	public long time;
}

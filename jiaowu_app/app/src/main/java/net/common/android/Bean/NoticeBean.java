package net.common.android.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 通知的Bean类
 * Created by Administrator on 2017/6/13.
 */

public class NoticeBean extends BmobObject implements Serializable{
	public String name;
	public Integer grade;
	public Integer id;
	public Integer status;
	public String title;
	public String content;
	public Long time;
}

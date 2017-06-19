package net.common.android.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 签到的Ben类
 * Created by Administrator on 2017/6/13.
 */

public class ExerciserBean extends BmobObject implements Serializable{
	public String name;
	public String title;
	public String content;
	public Integer grade;
	public Long time;
}

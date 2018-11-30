package com.bootdo.app.push;

import java.util.Date;

/**
 * @author haozw
 * @describe:
 * @modified by:
 * @modified date:
 * @since
 */

public abstract class PushBase {
    //此参数为测试参数，正式需要屏蔽
    protected static final String APPID = "";
    protected static final String APPKEY = "";
    protected static final String MASTERSECRET = "";
    protected static final String AppSecret = "";
    protected static final String API = "http://sdk.open.api.igexin.com/apiex.htm";     //OpenService接口地址
    protected static final String CLIENTID = "";
    protected static String getDate(){
        Date date = new Date();
        return date.toLocaleString();
    }
}

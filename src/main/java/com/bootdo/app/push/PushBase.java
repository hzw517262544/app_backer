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
    protected static final String APPID = "5DkSOFXXyt8Tv0W4J66vs8";
    protected static final String APPKEY = "g9JjTm2NqM7ntygAxgHXI7";
    protected static final String MASTERSECRET = "5P5YCPESzC8dleVuxcruX7";
    protected static final String AppSecret = "Q5Z7yQEYBu8ECSyaHy8rL6";
    protected static final String API = "http://sdk.open.api.igexin.com/apiex.htm";     //OpenService接口地址
    protected static final String CLIENTID = "";
    protected static String getDate(){
        Date date = new Date();
        return date.toLocaleString();
    }
}

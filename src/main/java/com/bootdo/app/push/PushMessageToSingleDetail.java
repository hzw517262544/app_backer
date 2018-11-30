package com.bootdo.app.push;

/**
 * @author haozw
 * @describe:
 * @modified by:
 * @modified date:
 * @since
 */

public class PushMessageToSingleDetail  extends PushBase{
    public static void main(String[] args) {
        // 推送主类
        /*IIGtPush push = new IGtPush(API, APPKEY, MASTERSECRET);

        try {

            // 单推消息类型
            SingleMessage message = new SingleMessage();

            //通知栏弹框下载模版
            //在通知栏显示一条含图标、标题等的通知，用户点击后弹出框，用户可以选择直接下载应用或者取消下载应用。
            NotyPopLoadTemplate template = new NotyPopLoadTemplate();
            // 是否激活
            template.setActived(true);
            // 安卓标识
            template.setAndroidMark("android_mark");
            template.setAppId(APPID);
            template.setAppkey(APPKEY);
            // 是否自动安装
            template.setAutoInstall(true);
            // 是否响铃
            template.setBelled(true);
            // 通知是否可清除
            template.setCleared(true);
            // 苹果标识
            template.setIphoneMark("iphone_mark");
            // 下载图标
            template.setLoadIcon("");
            // 下载标题
            template.setLoadTitle("LoadTitle");
            // 下载地址
            template.setLoadUrl("http://dizhensubao.igexin.com/dl/com.ceic.apk");
            // 通知栏内容
            template.setNotyContent("NotyContent");
            // 通知栏图标
            template.setNotyIcon("");
            // 通知栏标题
            template.setNotyTitle("NotyTitle");
            // 左侧按钮名称
            template.setPopButton1("下载");
            // 右侧按钮名称
            template.setPopButton2("取消");
            // 弹框内容
            template.setPopContent("popContent");
            // 弹框图标
            template.setPopImage("");
            // 弹框标题
            template.setPopTitle("PopTitle");
            // 塞班标识
            template.setSymbianMark("symbian_mark");
            // 是否震动
            template.setVibrationed(true);
            message.setData(template);
            message.setOffline(true);
            message.setOfflineExpireTime(72 * 3600 * 1000);
            // 设置优先级
            message.setPriority(1);

            Target target1 = new Target();
            target1.setAppId(APPID);
            target1.setClientId(CLIENTID);
            // 单推
            IPushResult ret = push.pushMessageToSingle(message, target1);
            System.out.println(ret.getResponse().toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}

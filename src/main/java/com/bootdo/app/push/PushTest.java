package com.bootdo.app.push;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haozw
 * @describe:
 * @modified by:
 * @modified date:
 * @since
 */

public class PushTest {
    //定义常量量, appId、appKey、masterSecret 采⽤用本⽂文档 "第⼆二步 获取访问凭证 "中获得的应⽤用配置
    private static String appId = "5DkSOFXXyt8Tv0W4J66vs8";
    private static String appKey = "g9JjTm2NqM7ntygAxgHXI7";
    private static String masterSecret = "5P5YCPESzC8dleVuxcruX7";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    public static void main(String[] args) throws IOException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("请填写通知标题");
        template.setText("请填写通知内容");
        template.setUrl("http://getui.com");
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的⽬目标App列列表、是否⽀支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
}

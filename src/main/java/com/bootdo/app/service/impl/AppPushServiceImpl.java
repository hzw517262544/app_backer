package com.bootdo.app.service.impl;

import com.bootdo.app.config.AppConfig;
import com.bootdo.app.service.AppPushService;
import com.bootdo.common.utils.R;
import com.gexin.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haozw
 * @describe:
 * @modified by:
 * @modified date:
 * @since
 */
@Service
public class AppPushServiceImpl implements AppPushService {
    private Logger logger = LoggerFactory.getLogger(AppPushServiceImpl.class);

    @Autowired
    private AppConfig appConfig;
    /**
     * 有透传内容 有标题 点击打开应用
     *
     * @return
     */
    @Override
    public R pushMessage(String clientId,String msgTitle,String msgContent,String msgUrl){
        {
            IGtPush push = new IGtPush(appConfig.getUrl(), appConfig.getAppKey(), appConfig.getMasterSecret());
            // 新建消息类型 单独推送给用户采用SingleMessage
            SingleMessage singleMessage = new SingleMessage();

            NotificationTemplate template = new NotificationTemplate();
            // 设置APPID与APPKEY
            template.setAppId(appConfig.getAppId());
            template.setAppkey(appConfig.getAppKey());
            // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
            template.setTransmissionType(1);

            JSONObject jo = new JSONObject();
            jo.put("AAA", "aba");
            jo.put("BBB", "bab");
            jo.put("CCC", "ccc");
            //template.setTransmissionContent(jo.toJSONString());
            template.setTransmissionContent(jo.toString());
            // 设置定时展示时间
            // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

            Style0 style = new Style0();
            // 设置通知栏标题与内容
            style.setTitle(msgTitle);
            style.setText(msgContent);
            // 配置通知栏图标
            style.setLogo("icon.png");
            // 配置通知栏网络图标
            style.setLogoUrl("");
            // 设置通知是否响铃，震动，或者可清除
            style.setRing(true);
            style.setVibrate(true);
            style.setClearable(true);
            template.setStyle(style);
            singleMessage.setData(template);

            // 新建一个推送目标，填入appid和clientId
            // 单推情况下只能设置一个推送目标，toList群推时，可以设置多个目标，目前建议一批设置50个左右。
            Target target = new Target();
            target.setAppId(appConfig.getAppId());
            target.setClientId(clientId);
            IPushResult result = push.pushMessageToSingle(singleMessage, target);
            String response = result.getResponse().toString();
            logger.info("发送消息到客户端：" + response);
        }
        return R.ok();
    }
}

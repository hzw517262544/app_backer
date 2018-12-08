package com.bootdo.app.service;

import com.bootdo.common.utils.R;

/**
 * @author haozw
 * @describe:
 * @modified by:
 * @modified date:
 * @since
 */

public interface AppPushService{
    /**
     * 有透传内容 有标题 点击打开应用
     *
     * @return
     */
    R pushMessage(String clientId,String msgTitle,String msgContent,String msgUrl);
}

package com.bootdo.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author haozw
 * @describe:
 * @modified by:
 * @modified date:
 * @since
 */
@Component
@ConfigurationProperties(prefix="app")
public class AppConfig {
    public static String appId;
    public static String appKey;
    public static String appSecret;
    public static String masterSecret;

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        AppConfig.appId = appId;
    }

    public static String getAppKey() {
        return appKey;
    }

    public static void setAppKey(String appKey) {
        AppConfig.appKey = appKey;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        AppConfig.appSecret = appSecret;
    }

    public static String getMasterSecret() {
        return masterSecret;
    }

    public static void setMasterSecret(String masterSecret) {
        AppConfig.masterSecret = masterSecret;
    }
}

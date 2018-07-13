package com.amateur.wanbei.webapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chenhaitao on 2018/6/7.
 */
@Data
@ConfigurationProperties(prefix = "wx")
public class WxProperties {
    private String appid;
    private String appsecret;
    private String token;
}

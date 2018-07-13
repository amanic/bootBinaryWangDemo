package com.amateur.wanbei.webapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chenhaitao on 2018/6/7.
 */
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {

    private String host;
    private int port;
    private String password;
    private int defaultdb;
    private int timeout;
    private int maxTotal;
    private int maxIdle;
    private int maxWait;
}
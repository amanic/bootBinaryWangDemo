package com.amateur.wanbei.webapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chenhaitao on 2018/6/5.
 */
@Data
@ConfigurationProperties(prefix = "jdbc.druid")
public class DBProperties {
    private String url;
    private String user;
    private String password;
    private String filters;
    private int maxActive;
    private int initialSize;
    private int maxWait;
    private int minIdle;
    private Long timeBetweenEvictionRunsMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean  poolPreparedStatements;
    private int maxOpenPreparedStatements;
}
package com.amateur.wanbei.webapp.config;

import com.amateur.wanbei.webapp.Handler.SubscribeHandler;
import com.amateur.wanbei.webapp.Handler.UnsubscribeHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInRedisConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenhaitao on 2018/6/7.
 */

@Configuration
@EnableConfigurationProperties(WxProperties.class)
public class WxConfig {

    @Autowired
    private WxProperties wxProperties;

    @Autowired
    private SubscribeHandler subscribeHandler;

    @Autowired
    private UnsubscribeHandler unsubscribeHandler;

    @Bean
    public WxMpService wxMpService(){
        WxMpInRedisConfigStorage config = new WxMpInRedisConfigStorage();
        config.setAppId(wxProperties.getAppid()); // 设置微信公众号的appid
        config.setSecret(wxProperties.getAppsecret()); // 设置微信公众号的app corpSecret
        config.setToken(wxProperties.getToken()); // 设置微信公众号的token
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        System.out.println("微信配置初始化完成");
        return wxMpService;
    }

    @Bean
    public WxMpMessageRouter router(WxMpService wxMpService){
        final WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        // 记录所有事件的日志 （异步执行）
        // 关注事件
        // todo: 后续加入用户操作日志 wx_operate_log　
        router.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_SUBSCRIBE).handler(subscribeHandler)
                .end();

        // 取消关注事件
        // todo: 后续加入用户操作日志 wx_operate_log　
        router.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_UNSUBSCRIBE)
                .handler(unsubscribeHandler).end();




        return router;
    }
}

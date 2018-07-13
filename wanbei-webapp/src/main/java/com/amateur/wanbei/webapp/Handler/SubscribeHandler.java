package com.amateur.wanbei.webapp.Handler;


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Component
public class SubscribeHandler implements WxMpMessageHandler {


    @Override
    @Transactional
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService, WxSessionManager sessionManager) {

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser());
            String eventKey = "";
            if (StringUtils.isNotBlank(wxMessage.getEventKey()) && StringUtils.startsWith(wxMessage.getEventKey(), "qrscene")) {
                eventKey = StringUtils.substring(wxMessage.getEventKey(), "qrscene_&quot;".length(), wxMessage.getEventKey().length() - "&quot;".length());
            }
            log.info("当前关注用户详细信息：{}", userWxInfo);
        } catch (WxErrorException wxE) {
            log.error("获取微信用户详细信息失败：{}", wxE.getMessage());
        } catch (Exception e) {
            log.error("处理微信用户关注消息异常：{}", e.getMessage(), e);
        }

        // 同步发送消息给关注用户
        try {
            return WxMpXmlOutMessage.TEXT().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).content("感谢您的关注").build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}

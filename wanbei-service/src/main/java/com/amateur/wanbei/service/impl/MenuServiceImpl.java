package com.amateur.wanbei.service.impl;

import com.amateur.wanbei.service.MenuService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceApacheHttpClientImpl;
import org.springframework.stereotype.Service;

/**
 * Created by chenhaitao on 2018/6/7.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Override
    public String getWxWccessToken() {
        WxMpService wxMpService = new WxMpServiceApacheHttpClientImpl();
        try {
            return wxMpService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}

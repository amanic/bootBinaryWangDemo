package com.amateur.wanbei.webapp.controller;

import com.amateur.wanbei.service.entity.WechatResp;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * Created by chenhaitao on 2018/6/19.
 */
@Slf4j
@RestController
@RequestMapping("/wechat/menu")
public class MenuController {

    @Autowired
    private WxMpService wxMpService;

    @PostMapping("create")
    public WechatResp<String> createMenu(@RequestBody WxMenu menu) {
        try {
            wxMpService.getMenuService().menuCreate(menu);
            return WechatResp.succeed("创建微信菜单成功");
        } catch (WxErrorException e) {
            log.error("创建微信自定义菜单失败：{}", e.getMessage(), e);
            return WechatResp.fail("创建微信菜单失败：" + e.getMessage());
        }
    }

    @GetMapping("get")
    public WechatResp<WxMpMenu> getMenu(){
        try {
            return WechatResp.suceedAndReturnData(wxMpService.getMenuService().menuGet());
        } catch (WxErrorException e) {
            log.error("获取微信菜单失败：{}", e.getMessage(), e);
            return WechatResp.fail("获取微信菜单失败：" + e.getMessage());
        }

    }

    @GetMapping("deleteAll")
    public WechatResp<String> deleteAll(){
        try {
            wxMpService.getMenuService().menuDelete();
            return WechatResp.succeed();
        } catch (Exception e) {
            log.error("删除微信自定义菜单失败：{}", e.getMessage(), e);
            return WechatResp.fail("删除微信菜单失败：" + e.getMessage());
        }
    }

    @GetMapping("delete")
    public WechatResp<String> delete(@RequestParam(value = "menuId") String menuId){
        try {
            wxMpService.getMenuService().menuDelete(menuId);
            return WechatResp.succeed();
        } catch (Exception e) {
            log.error("创建微信自定义菜单失败：{}", e.getMessage(), e);
            return WechatResp.fail("删除微信菜单失败：" + e.getMessage());
        }
    }
}
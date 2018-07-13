package com.amateur.wanbei.service.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by chenhaitao on 2018/6/19.
 */
@Data
public class WechatResp<T> implements Serializable {

    //成功返回码
    public static final String SUCCESS = "0";

    //失败返回码
    public static final String FAILURE = "-1";

    private static final long serialVersionUID = 3879627202014506537L;

    private T data;

    private String code;

    private String message;

    public WechatResp() {
    }

    public WechatResp(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    private static WechatResp build(String code, String message){
        WechatResp resp = new WechatResp();
        resp.setCode(code);
        resp.setMessage(message);

        return resp;
    }

    public static WechatResp fail(String reason){
        return WechatResp.build(WechatResp.FAILURE, reason);
    }

    public static WechatResp fail(String code, String reason){
        return WechatResp.build(code, reason);
    }

    public static WechatResp succeed(String message){
        return WechatResp.build(WechatResp.SUCCESS, message);
    }

    public static WechatResp succeed(){
        return WechatResp.build(WechatResp.SUCCESS, "请求成功");
    }

    public static WechatResp<String> succeedAndReturnString(String data){
        WechatResp<String> result = new WechatResp<>();
        result.setCode(WechatResp.SUCCESS);
        result.setMessage("请求成功");
        result.setData(data);

        return result;
    }

    public static <T> WechatResp<T> suceedAndReturnData(T data){
        WechatResp<T> result = new WechatResp<>();
        result.setCode(WechatResp.SUCCESS);
        result.setMessage("请求成功");
        result.setData(data);

        return result;
    }

    public boolean isSuccessful(){
        return this.code == null ? false : this.code.equals(SUCCESS);
    }

    public WechatResp<T> failed(String reason){
        this.code = FAILURE;
        this.message = reason;

        return this;
    }

}

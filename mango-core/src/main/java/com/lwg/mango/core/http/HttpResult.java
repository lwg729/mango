package com.lwg.mango.core.http;


/**
 * http结果封装
 */

public class HttpResult {

    private int code=200;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * 错误异常处理
     * @return
     */
    //
    public static HttpResult error(){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员");
    }

    //自定义异常处理
    public static HttpResult error(String msg){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,msg);
    }
    private static HttpResult error(int code, String msg) {
        HttpResult httpResult=new HttpResult();
        httpResult.setCode(code);
        httpResult.setMsg(msg);
        return httpResult;
    }

    /**
     * 正确处理
     */
    public static HttpResult ok(String msg){
        HttpResult httpResult=new HttpResult();
        httpResult.setMsg(msg);
        return httpResult;
    }

    public static HttpResult ok(Object data){
        HttpResult httpResult=new HttpResult();
        httpResult.setData(data);
        return httpResult;
    }

    public static HttpResult ok(){
        return new HttpResult();
    }

}

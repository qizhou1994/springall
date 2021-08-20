package com.zq.spring.zuul.model;
import com.zq.spring.zuul.model.ResponseEnums.*;

/**
 * @Description: bean
 * @author: zq
 * @date: 2021.08.20
 */
public class ResponseVo<T> {


    /**
     * 响应码
     */
    private Integer responseCode = ResponseEnums.RESPONSE_CODE.SUCCESS.getCode();

    /**
     * 提示信息：成功（默认）
     */
    private String message = ResponseEnums.RESPONSE_CODE.SUCCESS.getName();


    /**
     * 数据
     */
    private T content;

    /*************构造方法**************/

    /**
     * 访问成功，不需要返回数据
     *
     * @return
     */
    public ResponseVo() {
    }

    /**
     * 访问成功，需要返回数据
     *
     * @return
     */
    public ResponseVo(T content) {
        this.content = content;
    }

    /**************常用方法*************/

    /**
     * 修改提示信息
     *
     * @return
     */
    public ResponseVo<T> overrideMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 异常返回体封装
     *
     * @param errorCode 错误码
     * @param message   错误信息
     * @return
     */
    public ResponseVo<T> error(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
        return this;
    }


    /**
     * 未知异常返回体封装
     *
     * @param message 错误信息
     * @return
     */
    public ResponseVo<T> error(String message) {
        return this.error(RESPONSE_CODE.UNKNOWN_WARNING.getCode(), message);
    }

    /**
     * 未知异常返回体封装
     *
     * @param message 错误信息
     * @return
     */
    public ResponseVo<T> paraError(String message) {
        return this.error(RESPONSE_CODE.PARAM_WARNING.getCode(), message);
    }

    /*********getter&setter*********/


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }


}

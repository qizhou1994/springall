package com.zq.spring.zuul.model;

/**
 * @Description: 枚举常量
 * @author: zq
 * @date: 2021.08.20
 */
public class ResponseEnums {

    public enum RESPONSE_CODE {
        SUCCESS(0, "成功"),
        PARAM_WARNING(1, "参数错误"),
        DB_WARNING(2, "数据库异常"),
        CODE_WARNING(3, "代码运行异常"),
        UNKNOWN_WARNING(4, "未知异常");

        private int code;
        private String name;

        RESPONSE_CODE(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}

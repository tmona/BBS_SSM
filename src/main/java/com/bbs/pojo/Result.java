package com.bbs.pojo;

public class Result {

        private int code; // 状态码
        private String msg; // 提示信息
        private Object data; // 响应数据

        public Result(int code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        // getter和setter方法

        public static Result success(Object data) {
            return new Result(200, "OK", data);
        }

        public static Result success() {
            return success(null);
        }

        public static Result error(int code, String msg) {
            return new Result(code, msg, null);
        }

}

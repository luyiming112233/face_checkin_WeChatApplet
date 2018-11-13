package cn.edu.zjut.qiandao.utils;

public class Results {

    public static Result success(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    public static Result error(int code, String message) {
        return error(code, message, null);
    }

    public static Result error(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}

package com.sunwuo.electronic_mall.util;

public class ResultObject {
    //请求成功
    public static final int RESULT_SUCCESS = 1000;
    //请求失败
    public static final int RESULT_ERROR = 1001;
    //用户登录失败
    public static final int USER_LOGIN_ERROR = 1001;

    private Integer code;
    private String result;
    private Object body;

    public ResultObject() {
        super();
        this.code = 0;
        this.result = "一切都怪碧池炎";
        this.body = "传输数据有空";
    }

    public static ResultObject returnResultObject(int result,Object object){
        switch (result){
            case -1 : return new ResultObject();
            case 0 : return new ResultObject(RESULT_ERROR,"我没有做任何数据库操作哦");
            default : return new ResultObject(RESULT_SUCCESS,"成功处理了了"+result+"条数据",object);
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object o) {
        this.body = o;
    }

    public ResultObject(Integer code, String result, Object o) {
        super();
        this.code = code;
        this.result = result;
        this.body = o;
    }

    public ResultObject reset(Integer code, String result, Object o) {
        this.code = code;
        this.result = result;
        this.body = o;
        return this;
    }

    public ResultObject(Integer code, String result) {
        this.code = code;
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "code=" + code +
                ", result='" + result + '\'' +
                ", body=" + body +
                '}';
    }

}

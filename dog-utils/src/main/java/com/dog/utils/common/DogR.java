package com.dog.utils.common;

/**
 * 自定义输出
 * @author guoxg
 * @param <T>
 */
public class DogR<T> implements java.io.Serializable {

    private static final int OK = 0;
    private static final int FAIL = 1;
    private static final int UNAUTHORIZED = 2;

    private T data; //服务端数据
    private int status = OK; //状态码
    private String msg = ""; //描述信息

    //DogR APIS
    public static DogR isOk() {
        return new DogR();
    }

    public static DogR isFail() {
        return new DogR().status(FAIL);
    }

    public static DogR isFail(Throwable e) {
        return isFail().msg(e);
    }

    public DogR msg(Throwable e) {
        this.setMsg(e.toString());
        return this;
    }

    public DogR data(T data) {
        this.setData(data);
        return this;
    }

    public DogR status(int status) {
        this.setStatus(status);
        return this;
    }

    //Constructors
    public DogR() {

    }
    //Getter&Setters
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}

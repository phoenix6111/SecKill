package com.wanghaisheng.dto;

/**
 * Created by sheng on 2016/11/26.
 * 所有的json返回对象
 */
public class SeckillResult<T> {

    //状态，是否成功
    private boolean success;

    //状态信息
    private String error;

    private T data;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeckillResult{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}

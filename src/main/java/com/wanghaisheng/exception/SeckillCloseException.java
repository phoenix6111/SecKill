package com.wanghaisheng.exception;

/**
 * Created by sheng on 2016/11/26.
 * 秒杀关闭异常：如还没开启秒杀，秒杀时间已过
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}

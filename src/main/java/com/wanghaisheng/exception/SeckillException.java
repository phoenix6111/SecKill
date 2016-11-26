package com.wanghaisheng.exception;

/**
 * Created by sheng on 2016/11/26.
 * Seckill 异常，用于处理秒杀异常
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}

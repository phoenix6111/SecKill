package com.wanghaisheng.exception;

/**
 * Created by sheng on 2016/11/26.
 * 重复秒杀异常
 */
public class RepeatSeckillException extends SeckillException {
    public RepeatSeckillException(String message) {
        super(message);
    }

    public RepeatSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}

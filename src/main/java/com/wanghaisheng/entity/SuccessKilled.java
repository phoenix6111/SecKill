package com.wanghaisheng.entity;

import java.util.Date;

/**
 * Created by sheng on 2016/11/25.
 * 秒杀成功明细 model
 */
public class SuccessKilled {

    //秒杀商品的id
    private long seckillId;
    //秒杀用户的电话号码
    private long userPhone;
    //秒杀的状态：-1：失败，0：成功，1：物流中。。。
    private int state;
    //秒杀时间
    private Date createDate;
    //秒杀的商品，多对一
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createDate=" + createDate +
                ", seckill=" + seckill +
                '}';
    }
}

package com.wanghaisheng.entity;

import java.util.Date;

/**
 * Created by sheng on 2016/11/25.
 * 商品秒杀 model
 */
public class Seckill {
    //商品id
    private long seckillId;
    //商品名称
    private String name;
    private int invNum;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //创建时间
    private Date createTime;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        long minusTime = startTime.getTime() - 8*60*60*1000;
        this.startTime = new Date(minusTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        long minusTime = endTime.getTime() - 8*60*60*1000;
        this.endTime = new Date(minusTime);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        long minusTime = createTime.getTime() - 8*60*60*1000;
        this.createTime = new Date(minusTime);
    }

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}

package com.wanghaisheng.dto;

/**
 * Created by sheng on 2016/11/26.
 * 秒杀输出信息
 */
public class Exposer {

    //是否开始秒杀
    private boolean exposed;

    //秒杀商品ID
    private long seckillId;

    //一种加密措施，防止用户窜改数据
    private String md5;

    //秒杀开启时间
    private long start;

    //秒杀结束时间
    private long end;

    //服务器当前时间
    private long now;

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, String md5) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.md5 = md5;
    }

    public Exposer(boolean exposed, long seckillId, long start, long end, long now) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.start = start;
        this.end = end;
        this.now = now;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", now=" + now +
                '}';
    }
}

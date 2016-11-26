package com.wanghaisheng.enums;

/**
 * Created by sheng on 2016/11/26.
 */
public enum SeckillStateEnum {

    SUCCESS(0,"秒杀成功"),
    END(1,"秒杀结束"),
    DATA_REWRITE(-1,"数据窜改"),
    REPEAT_KILL(-2,"重复秒杀"),
    INNER_ERROR(-3,"系统异常");

    //状态代码
    private int state;

    //状态信息
    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public static SeckillStateEnum stateOf(int index) {
        for(SeckillStateEnum state : values()) {
            if(index == state.getState()) {
                return state;
            }
        }

        return  null;
    }

}

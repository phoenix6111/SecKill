package com.wanghaisheng.service;

import com.wanghaisheng.dto.Exposer;
import com.wanghaisheng.dto.SeckillExcution;
import com.wanghaisheng.entity.Seckill;
import com.wanghaisheng.exception.RepeatSeckillException;
import com.wanghaisheng.exception.SeckillCloseException;
import com.wanghaisheng.exception.SeckillException;

import java.util.List;

/**
 * Created by sheng on 2016/11/26.
 */
public interface SeckillService {

    /**
     * 根据ID查询一个Seckill
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 查询所有Seckill
     * @return
     */
    List<Seckill> queryAllSeckill();

    /**
     * 暴露秒杀信息：
     *  如果还未开始秒杀则显示开始时间和结束时间
     *  如果已经开始秒杀则输出秒杀地址
     * @param seckillId 秒杀商品ID
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     * @param seckillId 秒杀商品ID
     * @param userPhone 秒杀用户phone
     * @param md5 秒杀加密字段md5
     * @return 秒杀结果信息
     */
    SeckillExcution executeSeckill(long seckillId,long userPhone,String md5)
            throws SeckillException,RepeatSeckillException,SeckillCloseException;
}

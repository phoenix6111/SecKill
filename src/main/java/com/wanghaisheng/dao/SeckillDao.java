package com.wanghaisheng.dao;

import com.wanghaisheng.entity.Seckill;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by sheng on 2016/11/25.
 * 秒杀商品DAO
 */
public interface SeckillDao {

    /**
     * 秒杀商品，减库存
     * @param seckillId 秒杀商品id
     * @param killTime 秒杀时间
     * @return 返回影响记录条数，返回值若<=0，则秒杀不成功
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据id查询 Seckill
     * @param seckillId
     * @return 返回Seckill对象
     */
    Seckill queryById(long seckillId);

    /**
     * 分布查询所有的Seckill
     * @param offset 偏移值
     * @param limit 每页显示的值
     * @return 返回SecKill的List
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

}

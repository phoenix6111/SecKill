package com.wanghaisheng.dao;

import com.wanghaisheng.entity.SuccessKilled;

import org.apache.ibatis.annotations.Param;

/**
 * Created by sheng on 2016/11/25.
 */
public interface SucessKilledDao {

    /**
     * 插入秒杀详情记录
     * @param seckillId 秒杀的商品id
     * @param userPhone 用户phone
     * @return 插入数据影响的行数，如果返回值 <= 0，则秒杀不成功。因为successkilled表是seckill_id和user_phone
     * 为联合主键，则一个用户只能秒杀同一个一次
     */
    int insertSucessKilled(@Param("seckillId")long seckillId,@Param("userPhone") long userPhone,@Param("state") int state);

    /**
     * 根据seckillId和userPhone查询一个秒杀详情记录
     * @param seckillId 商品id
     * @param userPhone 用户电话号码
     * @return 返回SucessedKilled和其中包含的SecKill对象
     */
    SuccessKilled queryByIdWithSecKill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

}

package com.wanghaisheng.service;

import com.wanghaisheng.dao.SeckillDao;
import com.wanghaisheng.dao.SucessKilledDao;
import com.wanghaisheng.dto.Exposer;
import com.wanghaisheng.dto.SeckillExcution;
import com.wanghaisheng.entity.Seckill;
import com.wanghaisheng.entity.SuccessKilled;
import com.wanghaisheng.enums.SeckillStateEnum;
import com.wanghaisheng.exception.RepeatSeckillException;
import com.wanghaisheng.exception.SeckillCloseException;
import com.wanghaisheng.exception.SeckillException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sheng on 2016/11/26.
 */

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger mLogger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao mSeckillDao;

    @Autowired
    private SucessKilledDao mSucessKilledDao;

    //秒杀加密盐值
    private static final String mSalt = "sdfsdf2w3423423@@#$($*#))_#$@#(*&@_)+_$*(#@(*";

    public Seckill queryById(long seckillId) {
        return mSeckillDao.queryById(seckillId);
    }

    public List<Seckill> queryAllSeckill() {
        return mSeckillDao.queryAll(0,4);
    }

    /**
     * 输出秒杀地址信息
     * @param seckillId 秒杀商品ID
     * @return
     */
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = mSeckillDao.queryById(seckillId);
        //如果查询不到指定id的秒杀商品，则不能秒杀
        if(seckill == null) {
            return new Exposer(false,seckillId);
        }

        //如果系统当前时间在秒杀开始时间之前，或当前时间在秒杀结束时间之后，则不能秒杀
        long startTime = seckill.getStartTime().getTime();
        long endTime = seckill.getEndTime().getTime();
        long nowTime = (new Date()).getTime();
        if((nowTime < startTime) || (nowTime > endTime)) {
            return new Exposer(false,seckillId,startTime,endTime,nowTime);
        }

        //如果都符合上述条件，则输出秒杀地址
        String md5 = getMD5(seckillId);
        return new Exposer(true,seckillId,md5);

    }

    /**
     * 生成md5加密的字符串
     * @param seckillId 秒杀商品ID
     * @return
     */
    private String getMD5(long seckillId) {
        String temp = mSalt +"/"+ seckillId;
        return DigestUtils.md5DigestAsHex(temp.getBytes());
    }

    /**
     * 执行秒杀
     * @param seckillId 秒杀商品ID
     * @param userPhone 秒杀用户phone
     * @param md5 秒杀加密字段md5
     * @return 秒杀结果信息
     * @throws SeckillException
     * @throws RepeatSeckillException
     * @throws SeckillCloseException
     */
    @Transactional
    /**
     * 使用注解控制事务方法的优点：
     *  1）：开发团队达成一致约定，明确标注事务方法的编程风格
     *  2）：保证事务方法的执行时间尽可能短，不要穿插其它的网络操作，RPC/HTTP请求或者剥离到事务方法外
     *  3）：不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务
     */
    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatSeckillException, SeckillCloseException {

        //如果md5值为空或md5值与原md5值不相等，则可能用户窜改了秒杀信息，秒杀不成功
        if((md5 == null) || (!md5.equals(getMD5(seckillId)))) {
            return new SeckillExcution(seckillId,SeckillStateEnum.DATA_REWRITE);
        }

        try {
            //执行秒杀，减库存，写入秒杀结果信息
            Date nowDate = new Date();

            //减库存
            int updateCount = mSeckillDao.reduceNumber(seckillId,nowDate);
            //如果秒杀商品库存为零，或秒杀时间已过，或其它原因导致秒杀失败，返回值小于或等于0，则说明秒杀已关闭
            if(updateCount <= 0) {
                throw new SeckillCloseException("seckill closed!");
            } else {
                //写入秒杀纪录
                int insertCount = mSucessKilledDao.insertSucessKilled(seckillId,userPhone);
                //如果写入秒杀纪录失败，则说明用户重复秒杀
                if(insertCount <= 0) {
                    throw new RepeatSeckillException("repeat seckill!");
                } else {
                    SuccessKilled successKilled = mSucessKilledDao.queryByIdWithSecKill(seckillId,userPhone);
                    return new SeckillExcution(seckillId,SeckillStateEnum.SUCCESS,successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatSeckillException e2) {
            throw e2;
        } catch (Exception e) {
            mLogger.error(e.getMessage(),e);
            //所有编译期异常，转化为运行期异常
            throw new SeckillException("seckill inner error"+e.getMessage());
        }

    }
}

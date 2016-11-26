package com.wanghaisheng.service;

import com.wanghaisheng.dto.Exposer;
import com.wanghaisheng.dto.SeckillExcution;
import com.wanghaisheng.entity.Seckill;
import com.wanghaisheng.exception.RepeatSeckillException;
import com.wanghaisheng.exception.SeckillCloseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sheng on 2016/11/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    Logger mLogger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService mSeckillService;

    @Test
    public void queryById() throws Exception {
        long id = 1000L;
        Seckill seckill = mSeckillService.queryById(id);
        mLogger.info("seckill= {}",seckill);
//        System.out.println(seckill);
    }

    @Test
    public void queryAllSeckill() throws Exception {

        List<Seckill> list = mSeckillService.queryAllSeckill();
        mLogger.info("list= {}",list);

    }

    @Rollback
    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1002L;
        Exposer exposer = mSeckillService.exportSeckillUrl(id);
        if(exposer.isExposed()) {
            mLogger.info("exposer = {}",exposer);
            long userPhone = 18680316975L;
            String md5 = exposer.getMd5();

            try {
                SeckillExcution seckillExcution = mSeckillService.executeSeckill(id,userPhone,exposer.getMd5());
                mLogger.info("seckillExcution = {}",seckillExcution);
            } catch (SeckillCloseException e) {
                mLogger.error(e.getMessage());
            } catch (RepeatSeckillException e1) {
                mLogger.error(e1.getMessage());
            }

        } else {
            //秒杀未开启
            mLogger.warn("exposer = {}",exposer);
        }


    }



}
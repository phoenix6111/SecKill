package com.wanghaisheng.dao;

import com.wanghaisheng.entity.SuccessKilled;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sheng on 2016/11/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SucessKilledDaoTest {
    @Resource
    private SucessKilledDao mSucessKilledDao;

    @Rollback
    @Test
    public void insertSucessKilled() throws Exception {
        long id = 1000L;
        long phone = 18680316976L;
        int state = 0;
        int insertCount = mSucessKilledDao.insertSucessKilled(id,phone);
        System.out.println("insertCount  "+insertCount);
    }

    @Test
    public void queryByIdWithSecKill() throws Exception {
        long seckillId = 1000L;
        long phone = 18680316976L;
        SuccessKilled successKilled = mSucessKilledDao.queryByIdWithSecKill(seckillId,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}
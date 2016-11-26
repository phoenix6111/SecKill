package com.wanghaisheng.dao;

import com.wanghaisheng.entity.Seckill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created by sheng on 2016/11/26.
 * 配置Junit和Spring整合，为了Junit启动时加载Spring IOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit Spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入SeckillDao实现
    @Resource
    private SeckillDao mSeckillDao;

    @Rollback
    @Test
    public void reduceNumber() throws Exception {
        Date nowTime = new Date();
        int updateCount = mSeckillDao.reduceNumber(1000L,nowTime);
        System.out.println("updateCount  "+updateCount);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000L;
        Seckill seckill = mSeckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = mSeckillDao.queryAll(0,100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

}
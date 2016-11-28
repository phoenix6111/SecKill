package com.wanghaisheng.web;

import com.wanghaisheng.dto.Exposer;
import com.wanghaisheng.dto.SeckillExcution;
import com.wanghaisheng.dto.SeckillResult;
import com.wanghaisheng.entity.Seckill;
import com.wanghaisheng.enums.SeckillStateEnum;
import com.wanghaisheng.exception.RepeatSeckillException;
import com.wanghaisheng.exception.SeckillCloseException;
import com.wanghaisheng.service.SeckillService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by sheng on 2016/11/26.
 */
@Controller
@RequestMapping("/seckills")
public class SeckillController {
    private Logger mLogger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService mSeckillService;

    /**
     * 所有的Seckill列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model) {
        //获取列表页数据
        List<Seckill> seckills = mSeckillService.queryAllSeckill();
        mLogger.debug("seckills = {}",seckills);
        model.addAttribute("seckills",seckills);

        //list.jsp+model = ModelAndView
        return "list";
    }

    /**
     * 根据seckillId显示详情
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId,Model model) {
        if(seckillId == null) {
            return "redirect:/seckills/";
        }
        Seckill seckill = mSeckillService.queryById(seckillId);
        if(seckill == null) {
            return "forward:/seckills/";
        }

        model.addAttribute("seckill",seckill);

        return "detail";
    }

    /**
     * ajax方式，暴露详情，返回json
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/{seckillId}/expose",method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<Exposer> expose(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> seckillResult = null;

        try {
            Exposer exposer = mSeckillService.exportSeckillUrl(seckillId);
            seckillResult = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e) {
            mLogger.error(e.getMessage());
            return new SeckillResult<Exposer>(false,e.getMessage());
        }

        return seckillResult;
    }

    /**
     * 执行秒杀
     * @param seckillId 秒杀商品id
     * @param md5 秒杀md5加密值
     * @param killPhone 秒杀用户phone
     * @return 秒杀结果json
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<SeckillExcution> executeKill(@PathVariable("seckillId") Long seckillId,
              @PathVariable("md5") String md5, @CookieValue(value = "killPhone",required = false) Long killPhone){

        if(killPhone == null) {
            return new SeckillResult<SeckillExcution>(false,"用户未登陆");
        }

        try {
            SeckillExcution seckillExcution = mSeckillService.executeSeckill(seckillId, killPhone,md5);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        } catch (SeckillCloseException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.END);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        } catch (RepeatSeckillException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        } catch (Exception e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        }

    }

    /**
     * 获取当前系统时间
     * @return
     */
    @RequestMapping(value = "/time/now",method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();

        return new SeckillResult<Long>(true,now.getTime());
    }

}

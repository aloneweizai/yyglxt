package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.LotteryActivityipRq;
import com.abc.soa.response.consumer.LotteryActivityipRs;
import com.abc.soa.response.consumer.LotteryRs;
import com.abc.soa.response.consumer.bo.LotteryActivityBO;
import com.abc.soa.response.consumer.bo.LotteryActivityipBO;
import com.abc.soa.response.system.bo.DictListBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/consumerManager/lottery")
public class LotteryActivityIpController {

    protected static Logger log = LoggerFactory.getLogger(LotteryActivityIpController.class);

    @RequestMapping("/lotteryActivityip.php")
    public String lotteryActivityipList(LotteryActivityipRq lotteryActivityipRq, HttpServletRequest request, Model model) {
        // lotteryLogRq.setSize(0);
        LotteryActivityipRs lotteryActivityipRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITYIP_LIST, lotteryActivityipRq, LotteryActivityipRs.class);

        model.addAttribute("listRs", lotteryActivityipRs.getDataList());
        lotteryActivityipRq.setTotalItems(lotteryActivityipRs.getTotal());
        lotteryActivityipRq.calculate();
        model.addAttribute("pagination", lotteryActivityipRq);
        model.addAttribute("activityId",lotteryActivityipRq.getActivityId());
        return "consumer/lottery/LotteryActivityip_list";
    }
    

    @RequestMapping("/lotteryActivityipEdit.php")
    public String lotteryActivityipEdit( @RequestParam(required = false) String id,@RequestParam(required = false) String activityId, HttpServletRequest request, Model model) {
        if (StringUtils.isEmpty(id)) {
//添加商品的时候 判断活动 是否 停用
        }else{
            LotteryActivityipRs lotteryActivityipRs = SoaConnectionFactory.getRestful(request, ConstantsUri.LOTTERYACTIVITYIP_INFO, null, LotteryActivityipRs.class, id);
            model.addAttribute("obj", lotteryActivityipRs.getData());

        }




        model.addAttribute("activityId",activityId);
        return "consumer/lottery/LotteryActivityip_edit";
    }  

    @PostMapping("/lotteryActivityipSave.php")
    public @ResponseBody   BaseResponse lotteryActivityipSave(@RequestBody LotteryActivityipBO lotteryActivityipBO, HttpServletRequest request) {
        String id = lotteryActivityipBO.getId();
        BaseResponse returnObj = null;
        if(id==null || id.isEmpty()){
            //新增
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.LOTTERYACTIVITYIP_LIST , lotteryActivityipBO, LotteryActivityipRs.class);

        }else{
              returnObj = SoaConnectionFactory.put(request, ConstantsUri.LOTTERYACTIVITYIP_INFO, lotteryActivityipBO, LotteryActivityipRs.class, id);

        }

        return returnObj;

    }
      
    /**
     * 删除
     *
     */
    @RequestMapping("/lotteryActivityipDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(@RequestParam(required = true ) String id,@RequestParam(required = false) String activityId, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.LOTTERYACTIVITYIP_INFO, null, BaseResponse.class, id);
    }
   
}

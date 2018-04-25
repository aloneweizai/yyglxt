package com.abc.controller.consumerManager;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.tpl.TemplateBo;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.tpl.TplListResponse;
import com.abc.soa.response.consumer.LotteryTemplateRs;
import com.abc.soa.response.consumer.bo.LotteryTemplateExRs;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.abc.soa.request.consumer.LotteryActivityRq;
import com.abc.soa.response.consumer.LotteryActivityRs;
import com.abc.soa.response.consumer.bo.LotteryActivityBO;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/consumerManager/lottery")
public class LotteryActivityController {

    protected static Logger log = LoggerFactory.getLogger(LotteryActivityController.class);

    /**
     * 抽奖活动列表
     * @param lotteryActivityRq
     * @param request
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/lotteryActivity.php")
    public String lotteryActivityList(LotteryActivityRq lotteryActivityRq, HttpServletRequest request, Model model,HttpSession session) {
        if(lotteryActivityRq.getName()== null){
            LotteryActivityRq oldRq =(LotteryActivityRq)session.getAttribute(lotteryActivityRq.getClass().getSimpleName());
            if(oldRq != null){
                lotteryActivityRq.setName(oldRq.getName());
            }
        }
        session.setAttribute(lotteryActivityRq.getClass().getSimpleName(),lotteryActivityRq);

        lotteryActivityRq.setSize(0);
        LotteryActivityRs lotteryActivityRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITY_LIST, lotteryActivityRq, LotteryActivityRs.class);

        //这里要替换一下 模版名称
        TplListResponse lotteryTemplateRs = LotteryTemplateController.getTemplates(request,null);
        if (lotteryTemplateRs != null) {

            List<TemplateBo> list = lotteryTemplateRs.getDataList();
            List<LotteryActivityBO> lotteryActivityBOList = lotteryActivityRs.getDataList();
            if (list != null && lotteryActivityBOList != null) {
                for (LotteryActivityBO lab : lotteryActivityBOList) {
                    for (TemplateBo obj : list) {
                        if (lab.getTemplateId() != null && lab.getTemplateId().equals(obj.getTemplateId())) {
                            lab.setTemplateName(obj.getTemplateName());
                            break;
                        }
                    }
                }

            }

        }
        model.addAttribute("listRs", lotteryActivityRs.getDataList());
        lotteryActivityRq.setTotalItems(lotteryActivityRs.getTotal());
        lotteryActivityRq.calculate();
        model.addAttribute("pagination", lotteryActivityRq);
        return "consumer/lottery/LotteryActivity_list";
    }
//            LotteryActivityBO  lotteryActivityBO = LotteryActivityController.getLotteryActivity(request,id);
    public static LotteryActivityBO getLotteryActivity(HttpServletRequest request, String id) {
        LotteryActivityRs lotteryActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.LOTTERYACTIVITY_INFO, null, LotteryActivityRs.class, id);
        if (lotteryActivityRs != null) return lotteryActivityRs.getData();
        return null;
    }
    /**判断是不是当天*/
    private boolean dateIsDay(Date date ) {
        if (date == null) {
            return false;
        }
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(now);
        String dateDate = sdf.format(date);
        return nowDate.equals(dateDate);
    }
    @RequestMapping("/lotteryActivityEdit.php")
    public String lotteryActivityEdit(@RequestParam(required = false) String id,@RequestParam(required = false) String chakan,  HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            LotteryActivityRs lotteryActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.LOTTERYACTIVITY_INFO, null, LotteryActivityRs.class, id);
            LotteryActivityBO  lotteryActivityBO = LotteryActivityController.getLotteryActivity(request,id);
            LotteryActivityBO obj = LotteryActivityController.getLotteryActivity(request,id);

           if (obj !=null && !dateIsDay(obj.getTimeDay())){
               obj.setTimeCount(0);
           }
            model.addAttribute("obj",obj);

        }
        TplListResponse lotteryTemplateRs = LotteryTemplateController.getTemplates(request,null);

        if (lotteryTemplateRs != null) {
            model.addAttribute("lotteryTemplateRs", lotteryTemplateRs.getDataList());

        }
      if(chakan != null){
          model.addAttribute("chakan", chakan);
      }

        List<String> userLevelRs = new ArrayList<String>();
        for (int i = 1;i<=30;i++){
            userLevelRs.add(String.valueOf(i));
        }
        model.addAttribute("userLevelRs", userLevelRs);

        if(chakan !=null){
            return "consumer/lottery/LotteryActivity_detail";
        }else{
            return "consumer/lottery/LotteryActivity_edit";
        }
    }

    @PostMapping("/lotteryActivitySave.php")
    public @ResponseBody
    BaseResponse lotteryActivitySave(@RequestBody LotteryActivityBO lotteryActivityBO, HttpServletRequest request) {
        if (lotteryActivityBO.getTemplateId() != null && lotteryActivityBO.getTemplateId().isEmpty()){
            lotteryActivityBO.setTemplateId(null);
        }
        if(lotteryActivityBO.getTimeCount() != null && lotteryActivityBO.getTimeStock() != null){
            if(lotteryActivityBO.getTimeStock() <lotteryActivityBO.getTimeCount()){

                return new BaseResponse("999","错误：派奖总数小于已派奖数");
            }
        }
        if(lotteryActivityBO.getStartTime() != null && lotteryActivityBO.getEndTime() != null){
            if(lotteryActivityBO.getStartTime().getTime() > lotteryActivityBO.getEndTime().getTime()){

                return new BaseResponse("999","时间设置错误");
            }
        }
        String id = lotteryActivityBO.getId();
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            //新增
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.LOTTERYACTIVITY_LIST, lotteryActivityBO, LotteryActivityRs.class);

        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.LOTTERYACTIVITY_INFO, lotteryActivityBO, LotteryActivityRs.class, id);

        }

        return returnObj;

    }

    /**
     * 删除
     */
    @RequestMapping("/lotteryActivityDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(@RequestParam(required = true) String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.LOTTERYACTIVITY_INFO, null, BaseResponse.class, id);
    }

    @PostMapping("/lotteryActivity/enableApi.php")
    public @ResponseBody
    BaseResponse enableApi(String id, boolean status, HttpServletRequest request) {
        LotteryActivityBO obj = new LotteryActivityBO();
        obj.setId(id);
        obj.setStatus(status);
        return this.lotteryActivitySave(obj, request);

    }
}

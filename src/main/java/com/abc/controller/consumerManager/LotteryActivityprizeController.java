package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.consumer.LotteryRs;
import com.abc.soa.response.consumer.bo.LotteryActivityBO;
import com.abc.soa.response.system.bo.DictListBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import com.abc.soa.request.consumer.LotteryActivityprizeRq;
import com.abc.soa.response.consumer.LotteryActivityprizeRs;
import com.abc.soa.response.consumer.bo.LotteryActivityprizeBO;

import java.util.List;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/consumerManager/lottery")
public class LotteryActivityprizeController {

    protected static Logger log = LoggerFactory.getLogger(LotteryActivityprizeController.class);

    public static List<LotteryActivityprizeBO> getActivityPrize(HttpServletRequest request, String activityId) {
        LotteryActivityprizeRq lotteryActivityprizeRq = new LotteryActivityprizeRq();
        lotteryActivityprizeRq.setSize(0);
        lotteryActivityprizeRq.setActivityId(activityId);
        LotteryActivityprizeRs lotteryActivityprizeRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITYPRIZE_LIST, lotteryActivityprizeRq, LotteryActivityprizeRs.class);
        if (lotteryActivityprizeRs == null) {
            return null;
        }
        return lotteryActivityprizeRs.getDataList();
    }

    /**
     * 抽奖活动奖品列表
     * @param lotteryActivityprizeRq
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/lotteryActivityprize.php")
    public String lotteryActivityprizeList(LotteryActivityprizeRq lotteryActivityprizeRq, HttpServletRequest request, Model model) {
        lotteryActivityprizeRq.setSize(0);
        LotteryActivityprizeRs lotteryActivityprizeRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITYPRIZE_LIST, lotteryActivityprizeRq, LotteryActivityprizeRs.class);

        for (int i = 0; i < lotteryActivityprizeRs.getDataList().size(); i++) {
            String url = lotteryActivityprizeRs.getDataList().get(i).getLotteryImage();
            if (url != null && !"".equals(url)) {
                String imgPath = SpringCtxHolder.getProperty("abc.soa-upload-url");
                lotteryActivityprizeRs.getDataList().get(i).setLotteryImage(imgPath + url);
            } else {
                lotteryActivityprizeRs.getDataList().get(i).setLotteryImage("");
            }
        }
        model.addAttribute("listRs", lotteryActivityprizeRs.getDataList());
        lotteryActivityprizeRq.setTotalItems(lotteryActivityprizeRs.getTotal());
        lotteryActivityprizeRq.calculate();
        model.addAttribute("pagination", lotteryActivityprizeRq);
        model.addAttribute("activityId", lotteryActivityprizeRq.getActivityId());
        return "consumer/lottery/LotteryActivityprize_list";
    }

    /**
     * 抽奖活动-奖品管理
     * @param id
     * @param activityId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/lotteryActivityprizeEdit.php")
    public String lotteryActivityprizeEdit(@RequestParam(required = false) String id, @RequestParam(required = false) String activityId, HttpServletRequest request, Model model) {
        if (StringUtils.isEmpty(id)) {
           //添加商品的时候 判断活动 是否 停用
        } else {
            LotteryActivityprizeRs lotteryActivityprizeRs = SoaConnectionFactory.getRestful(request, ConstantsUri.LOTTERYACTIVITYPRIZE_INFO, null, LotteryActivityprizeRs.class, id);

            String url = lotteryActivityprizeRs.getData().getLotteryImage();
            if (url != null && !"".equals(url)) {
                String imgPath = SpringCtxHolder.getProperty("abc.soa-upload-url");
                lotteryActivityprizeRs.getData().setLotteryImage(imgPath + url);
            } else {
                lotteryActivityprizeRs.getData().setLotteryImage("");
            }
            model.addAttribute("obj", lotteryActivityprizeRs.getData());

        }
        //去数据字典获取
        DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "lottery_level");
        if (dicts != null && dicts.getDataList() != null && dicts.getDataList().size() > 0) {
            model.addAttribute("lottery_level", dicts.getDataList());
        }
        String imgPath = SpringCtxHolder.getProperty("abc.soa-upload-url");
        LotteryRs lotteryRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERY_LIST, null, LotteryRs.class);
        for (int i = 0; i < lotteryRs.getDataList().size(); i++) {
            String url = lotteryRs.getDataList().get(i).getImage();
            if (url != null && !"".equals(url)) {
                lotteryRs.getDataList().get(i).setImage(imgPath + url);
            } else {
                lotteryRs.getDataList().get(i).setImage("");
            }
        }

        model.addAttribute("lotteryRs", lotteryRs.getDataList());

        model.addAttribute("activityId", activityId);
        return "consumer/lottery/LotteryActivityprize_edit";
    }

    @PostMapping("/lotteryActivityprizeSave.php")
    public @ResponseBody
    BaseResponse lotteryActivityprizeSave(@RequestBody LotteryActivityprizeBO lotteryActivityprizeBO, HttpServletRequest request) {
        String id = lotteryActivityprizeBO.getId();
        BaseResponse returnObj = null;
        //这里要验证 中奖概率 别大于 100
        if (lotteryActivityprizeBO.getLuck() != null && lotteryActivityprizeBO.getLuck() > 0) {
            Double luckCount = lotteryActivityprizeBO.getLuck();
            List<LotteryActivityprizeBO> prizeList = getActivityPrize(request, lotteryActivityprizeBO.getActivityId());
            if (prizeList != null && prizeList.size() > 0) {
                for (LotteryActivityprizeBO forobj : prizeList) {
                    if (lotteryActivityprizeBO.getId() != null && !lotteryActivityprizeBO.getId().isEmpty() && lotteryActivityprizeBO.getId().equals(forobj.getId())) {

                    } else {
                        if(forobj.getNoluck()==null || forobj.getNoluck()){

                        }else{
                            luckCount += forobj.getLuck();
                        }

                    }
                }
            }
            if(luckCount > 100){
                return new BaseResponse("999","所有奖品的概率总和大于百分之百");
            }
        }

        if (id == null || id.isEmpty()) {
            //新增
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.LOTTERYACTIVITYPRIZE_LIST, lotteryActivityprizeBO, LotteryActivityprizeRs.class);

        } else {


            returnObj = SoaConnectionFactory.put(request, ConstantsUri.LOTTERYACTIVITYPRIZE_INFO, lotteryActivityprizeBO, LotteryActivityprizeRs.class, id);

        }

        return returnObj;

    }

    /**
     * 删除
     */
    @RequestMapping("/lotteryActivityprizeDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(@RequestParam(required = true) String id, @RequestParam(required = false) String activityId, HttpServletRequest request) {
        LotteryActivityBO lotteryActivityBO = LotteryActivityController.getLotteryActivity(request, activityId);
        if (lotteryActivityBO.getStatus() != null && lotteryActivityBO.getStatus()) {
            //启用

            return new BaseResponse(false, "活动已启用，请先禁用活动");
        }
        return SoaConnectionFactory.delete(request, ConstantsUri.LOTTERYACTIVITYPRIZE_INFO, null, BaseResponse.class, id);
    }

}

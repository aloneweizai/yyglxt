package com.abc.controller.consumerManager;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.FileOperateUtil;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.AddressListRq;
import com.abc.soa.request.consumer.LotteryLogRq;
import com.abc.soa.request.consumer.LotteryRq;
import com.abc.soa.request.consumer.LotteryTimeRq;
import com.abc.soa.response.consumer.*;
import com.abc.soa.response.consumer.bo.LotteryBO;
import com.abc.soa.response.consumer.bo.LotteryLogBO;
import com.abc.soa.response.consumer.bo.LotteryTimeBO;
import com.abc.soa.response.system.Friendlink;
import com.abc.soa.response.system.bo.DictListBO;
import com.abc.soa.response.system.bo.UserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by relic5 on 2017/6/19.
 * 抽奖记录
 */
@Controller
@RequestMapping(value = "/consumerManager/lottery")
public class UpointsLotteryController {

    protected static Logger _log = LoggerFactory.getLogger(UpointsLotteryController.class);


    @PostMapping("/lotteryTimeSave.php")
    public @ResponseBody
    BaseResponse lotteryTimeSave(@RequestBody LotteryTimeBO lotteryTimeBO, HttpServletRequest request) {
        String id = lotteryTimeBO.getId();
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            //新增
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.LOTTERYTime_LIST, lotteryTimeBO, LotteryTimeRs.class);

        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.LOTTERYTime_INFO, lotteryTimeBO, LotteryTimeRs.class, id);

        }

        return returnObj;

    }

    @PostMapping("/lotterylogSave.php")
    public @ResponseBody
    BaseResponse saveLogPointrule(@RequestBody LotteryLogBO lotteryLogBO, HttpServletRequest request) {
        String id = lotteryLogBO.getId();
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("id 错误");
        }
        LotteryLogBO obj = new LotteryLogBO();
        obj.setId(id);

        obj.setKuaididanhao(lotteryLogBO.getKuaididanhao());
        obj.setKuaidiGS(lotteryLogBO.getKuaidiGS());
        obj.setSendRemake(lotteryLogBO.getSendRemake());
        obj.setState(lotteryLogBO.getState());
        BaseResponse returnObj = SoaConnectionFactory.put(request, ConstantsUri.LOTTERYLOG_INFO, obj, LotteryLogRs.class, id);
        return returnObj;

    }

    /**
     * 删除
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/lotteryTimeDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(@RequestParam(required = true) String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.LOTTERYTime_INFO, null, BaseResponse.class, id);
    }

    @RequestMapping("/lotterytime.php")
    public String lotteryTimeList(LotteryTimeRq lotteryTimeRq, HttpServletRequest request, Model model) {
        LotteryTimeRs lotteryTimeRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYTime_LIST, lotteryTimeRq, LotteryTimeRs.class);


        model.addAttribute("lotteryTimeRs", lotteryTimeRs.getDataList());
        lotteryTimeRq.setTotalItems(lotteryTimeRs.getTotal());
        lotteryTimeRq.calculate();
        model.addAttribute("pagination", lotteryTimeRq);
        model.addAttribute("activityId", lotteryTimeRq.getActivityId());
        return "consumer/lottery/Timelist";
    }

    @RequestMapping("/lotterylog.php")
    public String lotteryLogList(LotteryLogRq lotteryLogRq, HttpServletRequest request, Model model) {
        if (true) {                //去数据字典获取 奖品领取状态
            DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "lottery_fhzt");
            if (dicts != null && dicts.getDataList() != null && dicts.getDataList().size() > 0) {
                model.addAttribute("lottery_fhzt", dicts.getDataList());
            }
        }
        LotteryLogRs lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_LIST, lotteryLogRq, LotteryLogRs.class);


        model.addAttribute("lotteryLogRs", lotteryLogRs.getDataList());
        lotteryLogRq.setTotalItems(lotteryLogRs.getTotal());
        lotteryLogRq.calculate();
        model.addAttribute("pagination", lotteryLogRq);
        LotteryActivityRs lotteryActivityRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITY_LIST, null, LotteryActivityRs.class);
        model.addAttribute("lotteryActivityRs", lotteryActivityRs.getDataList());

        return "consumer/lottery/Loglist";
    }

    @RequestMapping("/lotteryTimeEdit.php")
    public String lotteryTimeEdit(@RequestParam(required = false) String id, @RequestParam(required = false) String activityId, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            LotteryTimeRs lotteryTimeRs = SoaConnectionFactory.getRestful(request, ConstantsUri.LOTTERYTime_INFO, null, LotteryTimeRs.class, id);

            model.addAttribute("lotteryTime", lotteryTimeRs.getData());

        }
        model.addAttribute("activityId", activityId);
        return "consumer/lottery/lotteryTime_edit";
    }

    /**
     * 日志修改
     *
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/lotteryLogEdit.php")
    public String editLogPointrule(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            LotteryLogRs lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_INFO, null, LotteryLogRs.class, id);
            LotteryLogBO lotteryLogBo = lotteryLogRs.getData();
            model.addAttribute("lotteryLog", lotteryLogBo);

            model.addAttribute("referer", request.getHeader("Referer"));

           /* if(lotteryLogBo.getAddressId() != null){
                AddressListRq  addressListRq = new AddressListRq();
                addressListRq.setAddressId(lotteryLogBo.getAddressId());
                AddressListRes addressListRes=SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS_LIST,addressListRq, AddressListRes.class,lotteryLogBo.getUserId());
                if (addressListRes != null && addressListRes.getDataList()!= null){
                    List<AddressBo> addressBos  = addressListRes.getDataList();
                    for(AddressBo addressBo : addressBos){
                        if(addressBo.getId().equals(lotteryLogBo.getAddressId())){
                            model.addAttribute("dizhi", addressBo);
                        }
                    }
                }
            }*/
            if (true) { //去数据字典获取 快递公司
                DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "kdgs");
                if (dicts != null && dicts.getDataList() != null && dicts.getDataList().size() > 0) {
                    model.addAttribute("kdgs", dicts.getDataList());
                }
            }
            if (true) { //去数据字典获取 发货状态
                DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "lottery_fhzt");
                if (dicts != null && dicts.getDataList() != null && dicts.getDataList().size() > 0) {
                    model.addAttribute("lottery_fhzt", dicts.getDataList());
                }
            }
            return "consumer/lottery/lotteryLog_edit";
        } else {
            return "404";
        }
    }


    @RequestMapping("/lotteryLogDetail.php")
    public String LogDetail(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            LotteryLogRs lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_INFO, null, LotteryLogRs.class, id);
            LotteryLogBO lotteryLogBo = lotteryLogRs.getData();
            model.addAttribute("lotteryLog", lotteryLogBo);
            model.addAttribute("referer", request.getHeader("Referer"));

            if (true) { //去数据字典获取 快递公司
                DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "kdgs");
                if (dicts != null && dicts.getDataList() != null && dicts.getDataList().size() > 0) {
                    model.addAttribute("kdgs", dicts.getDataList());
                }
            }
            if (true) { //去数据字典获取 发货状态
                DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "lottery_fhzt");
                if (dicts != null && dicts.getDataList() != null && dicts.getDataList().size() > 0) {
                    model.addAttribute("lottery_fhzt", dicts.getDataList());
                }
            }
            return "consumer/lottery/lotteryLog_detail";
        } else {
            return "404";
        }
    }
}

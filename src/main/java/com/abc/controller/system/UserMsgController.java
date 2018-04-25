package com.abc.controller.system;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BusinessMessageRq;
import com.abc.soa.request.system.UserMessageRq;
import com.abc.soa.response.system.BusinessMessageRs;
import com.abc.soa.response.system.UserMessageRs;
import com.abc.soa.response.system.bo.BusinessMessageBO;
import com.abc.soa.response.system.bo.UserMessageBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuy on 2017/6/29.
 */
@Controller
@RequestMapping(value = "/system/usermsg")
public class UserMsgController extends BaseController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserMsgController.class);

    /**
     * 跳转至关键词自动回复页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/usermsg_yw.php")
    public String usermsgyw(BusinessMessageRq rq, HttpServletRequest request, Model model) {
        if(!StringUtils.isEmpty(rq.getStartDate())){
            rq.setDateStr(rq.getStartDate().substring(0,4)+rq.getStartDate().substring(5,7));
        }
        if(!StringUtils.isEmpty(rq.getEndDate())){
            rq.setDateStr(rq.getEndDate().substring(0,4)+rq.getEndDate().substring(5, 7));
        }
        if(!StringUtils.isEmpty(rq.getStartDate())&&StringUtils.isEmpty(rq.getEndDate())){
            rq.setEndDate(rq.getStartDate().substring(0, 4) + "-" + rq.getStartDate().substring(5, 7) + "-" + DateUtil.getLastMonthDay(rq.getStartDate().substring(0, 4), rq.getStartDate().substring(5, 7)));
        }
        if(!StringUtils.isEmpty(rq.getEndDate())&&StringUtils.isEmpty(rq.getStartDate())){
            rq.setStartDate(rq.getEndDate().substring(0,4)+"-"+rq.getEndDate().substring(5, 7)+"-01");
        }
        BusinessMessageRs businessMessageRs = SoaConnectionFactory.get(request, ConstantsUri.USERMSG_YW_LIST, rq, BusinessMessageRs.class);
        List<BusinessMessageBO> dataList = businessMessageRs.getDataList();
        List<BusinessMessageBO> list = new ArrayList<>();
        if(dataList!=null&&dataList.size()>0){
            for(int i =0;i<dataList.size();i++){
                BusinessMessageBO bo = dataList.get(i);
                if (!StringUtils.isEmpty(bo.getContent())) {
                    if (bo.getContent().length() > 100) {
                        bo.setContentstr(bo.getContent().substring(0, 100) + "...");
                    } else {
                        bo.setContentstr(bo.getContent());
                    }
                }
                if(!StringUtils.isEmpty(bo.getUserPicturePath())) {
                    bo.setUserPicturePath(SpringCtxHolder.getProperty("picdomain") + bo.getUserPicturePath());
                }
                list.add(bo);
            }
        }
        model.addAttribute("businessMessageRs", list);
        rq.setTotalItems(businessMessageRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("BaseRq", rq);
        model.addAttribute("busiType", getDictBOList(request,"busiType"));
        return "system/usermsg/usermsg_yw";
    }

    /**
     * 跳转至关键词自动回复页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/usermsg_sx.php")
    public String usermsgsx(UserMessageRq rq, HttpServletRequest request, Model model) {
        UserMessageRs userMessageRs = SoaConnectionFactory.get(request, ConstantsUri.USERMSG_SX_LIST, rq, UserMessageRs.class);
        List<UserMessageBO> dataList = userMessageRs.getDataList();
        List<UserMessageBO> list = new ArrayList<>();
        if(dataList!=null&&dataList.size()>0){
            for(int i =0;i<dataList.size();i++){
                UserMessageBO bo = dataList.get(i);
                if (!StringUtils.isEmpty(bo.getContent())) {
                    if (bo.getContent().length() > 40) {
                        bo.setContentstr(bo.getContent().substring(0, 40) + "...");
                    } else {
                        bo.setContentstr(bo.getContent());
                    }
                }
                if(!StringUtils.isEmpty(bo.getFromUserPic())){
                        bo.setFromUserPic(SpringCtxHolder.getProperty("picdomain") + bo.getFromUserPic());
                 }
                if(!StringUtils.isEmpty(bo.getToUserPic())) {
                    bo.setToUserPic(SpringCtxHolder.getProperty("picdomain") + bo.getToUserPic());
                }
                list.add(bo);
            }
        }
        model.addAttribute("userMessageRs", userMessageRs.getDataList());
        rq.setTotalItems(userMessageRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("BaseRq", rq);

        return "system/usermsg/usermsg_sx";
    }

}

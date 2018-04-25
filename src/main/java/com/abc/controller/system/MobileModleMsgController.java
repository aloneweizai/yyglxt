package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.MobileModleMsgRq;
import com.abc.soa.response.system.MobileModleMsgRs;
import com.abc.soa.response.system.bo.MobileModleMsgBO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/mobile/msg/modle")
public class MobileModleMsgController extends BaseController {

    protected static Logger LOGGER = LoggerFactory.getLogger(MobileModleMsgController.class);

    /**
     * 微信模版消息列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("soa:mobilemodle")
    @RequestMapping("/list.php")
    public String selectList(MobileModleMsgRq rq, HttpServletRequest request, Model model) {
        MobileModleMsgRs mobileModleMsgRs = SoaConnectionFactory.get(request, ConstantsUri.MOBILEMODLEMSG_LIST, rq, MobileModleMsgRs.class);
        model.addAttribute("BaseRq",rq);
        List<MobileModleMsgBO> dataList = mobileModleMsgRs.getDataList();
        List<MobileModleMsgBO> list = new ArrayList<>();
        if(dataList.size()>0){
            for(int i =0;i<dataList.size();i++){
                MobileModleMsgBO bo = dataList.get(i);
                if (!StringUtils.isEmpty(bo.getContent())) {
                    if (bo.getContent().length() > 40) {
                        bo.setContentstr(bo.getContent().substring(0, 40) + "...");
                    } else {
                        bo.setContentstr(bo.getContent());
                    }
                }
                if(!StringUtils.isEmpty(bo.getCreateTime())) {
                    bo.setCreateTime(bo.getCreateTime().substring(0, 10) + " " + bo.getCreateTime().substring(11, 19));
                }
                list.add(bo);
            }
        }
        model.addAttribute("mobileModleMsgRs", list);
        rq.setTotalItems(mobileModleMsgRs.getTotal());
        rq.calculate();
        model.addAttribute("pagination", rq);
        model.addAttribute("templateStatus", getDictBOList(request,"templateStatus"));
        return "system/mobilemodlemsg/list";
    }

    //同步微信用户到本地数据库
    @RequiresPermissions("soa:mobilemodle")
    @RequestMapping("/synchro.php")
    public
    @ResponseBody
    BaseResponse synchro(HttpServletRequest request) {
        return SoaConnectionFactory.post(request, ConstantsUri.MOBILEMODLEMSG_SYNC, null, MobileModleMsgRs.class);
    }

}

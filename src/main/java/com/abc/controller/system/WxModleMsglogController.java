package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.WxModleMsglogRq;
import com.abc.soa.response.system.WxModleMsglogRs;
import com.abc.soa.response.system.bo.WxModleMsglogBO;
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
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/system/wxmodlemsglog")
public class WxModleMsglogController extends BaseController {

    protected static Logger LOGGER = LoggerFactory.getLogger(WxModleMsglogController.class);

    /**
     * 微信模版消息日志列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequestMapping("/list.php")
    public String selectList(WxModleMsglogRq rq, HttpServletRequest request, Model model) {
        WxModleMsglogRs wxModleMsglogRs = SoaConnectionFactory.get(request, ConstantsUri.WXMODLEMSGLOG_LIST, rq, WxModleMsglogRs.class);
        model.addAttribute("BaseRq",rq);
        List<WxModleMsglogBO> dataList = wxModleMsglogRs.getDataList();
        List<WxModleMsglogBO> list = new ArrayList<>();
        if(!StringUtils.isEmpty(dataList)){
            if(dataList.size()>0){
                for(int i =0;i<dataList.size();i++){
                    WxModleMsglogBO bo = dataList.get(i);
                    if (!StringUtils.isEmpty(bo.getTemplateval())) {
                        if (bo.getTemplateval().length() > 100) {
                            bo.setTemplatevalstr(bo.getTemplateval().substring(0, 100) + "...");
                        } else {
                            bo.setTemplatevalstr(bo.getTemplateval());
                        }
                    }
                    list.add(bo);
                }
            }
        }
        model.addAttribute("wxModleMsglogRs", list);
        rq.setTotalItems(wxModleMsglogRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("BaseRq", rq);
        model.addAttribute("templateStatus", getDictBOList(request,"templateStatus"));
        return "system/wxmodlemsglog/list";
    }

}

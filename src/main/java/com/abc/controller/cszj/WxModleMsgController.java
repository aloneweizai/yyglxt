package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.WxTemplateRq;
import com.abc.soa.response.cszj.WxTemplateRs;
import com.abc.soa.response.cszj.bo.TemplateBO;
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
@RequestMapping(value = "/cszjs/wx/msg")
public class WxModleMsgController extends BaseController {

    protected static Logger LOGGER = LoggerFactory.getLogger(WxModleMsgController.class);

    /**
     * 微信模版消息列表查询
     *
     * @param wxTemplateRq
     * @param request
     * @return
     */
    @RequestMapping("/list.php")
    public String selectList(WxTemplateRq wxTemplateRq, HttpServletRequest request, Model model) {
        WxTemplateRs wxTemplateRs = SoaConnectionFactory.get(request, ConstantsUri.WXMODLEMSG_LIST, wxTemplateRq, WxTemplateRs.class);
        model.addAttribute("BaseRq",wxTemplateRq);
        List<TemplateBO> dataList = wxTemplateRs.getDataList();
        List<TemplateBO> list = new ArrayList<>();
        if(dataList.size()>0){
            for(int i =0;i<dataList.size();i++){
                TemplateBO bo = dataList.get(i);
                if (!StringUtils.isEmpty(bo.getContent())) {
                    if (bo.getContent().length() > 40) {
                        bo.setContentstr(bo.getContent().substring(0, 40) + "...");
                    } else {
                        bo.setContentstr(bo.getContent());
                    }
                }
                if (!StringUtils.isEmpty(bo.getExample())){
                if(bo.getExample().length()>100){
                    bo.setExamplestr(bo.getExample().substring(0, 100) + "...");
                }
                else {
                    bo.setExamplestr(bo.getExample());
                }
            }
                list.add(bo);
            }
        }
        model.addAttribute("wxTemplateRs", list);
        wxTemplateRq.setTotalItems(wxTemplateRs.getTotal());
        wxTemplateRq.calculate();
        model.addAttribute("pagination", wxTemplateRq);
        return "cszj/wxtemplate/list";
    }

    //同步微信用户到本地数据库
    @RequestMapping("/synchro.php")
    public
    @ResponseBody
    BaseResponse synchro(HttpServletRequest request) {
        return SoaConnectionFactory.get(request, ConstantsUri.WXMODLEMSG_SYNC, null, WxTemplateRs.class);
    }

}

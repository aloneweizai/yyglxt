package com.abc.controller.financed;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.InvoicekjlogRq;
import com.abc.soa.response.financed.InvoicekjlogRs;
import com.abc.soa.response.financed.bo.InvoicekjlogBO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/financed/invoicekjlog")
public class InvoicekjlogController extends BaseController {

    protected static Logger LOGGER = LoggerFactory.getLogger(InvoicekjlogController.class);

    /**
     * 微信模版消息列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:invoicekjlog")
    @RequestMapping("/list.php")
    public String selectList(InvoicekjlogRq rq, HttpServletRequest request, Model model) {
        InvoicekjlogRs invoicekjlogRs = SoaConnectionFactory.get(request, ConstantsUri.INVOICEKJLOG_LIST, rq, InvoicekjlogRs.class);
        model.addAttribute("BaseRq",rq);
        List<InvoicekjlogBO> dataList = invoicekjlogRs.getDataList();
        List<InvoicekjlogBO> list = new ArrayList<>();
        if(dataList.size()>0){
            for(int i =0;i<dataList.size();i++){
                InvoicekjlogBO bo = dataList.get(i);
                if(bo.getKPRQ().length()==14){
                    String kprq = bo.getKPRQ();
                    bo.setKPRQ(kprq.substring(0, 4) + "-" + kprq.substring(4, 6) + "-" + kprq.substring(6, 8)+" "+kprq.substring(8, 10)+":"+kprq.substring(10, 12)+":"+kprq.substring(12, 14));
                }
                list.add(bo);
            }
        }
        model.addAttribute("invoicekjlogRs", list);
        rq.setTotalItems(invoicekjlogRs.getTotal());
        rq.calculate();
        model.addAttribute("pagination", rq);
        model.addAttribute("templateStatus", getDictBOList(request,"templateStatus"));
        return "/financed/invoicekjlog/list";
    }

    //同步微信用户到本地数据库
    @RequiresPermissions("financed:invoicekjlog")
    @RequestMapping("/check.php")
    public
    @ResponseBody
    BaseResponse synchro(HttpServletRequest request) {
        return SoaConnectionFactory.post(request, ConstantsUri.INVOICEKJLOG_SYNC, null, InvoicekjlogRs.class);
    }

}

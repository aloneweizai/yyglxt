package com.abc.controller.consumerManager;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.CustomerServiceOrderRq;
import com.abc.soa.response.consumer.QuestionAcceptedListRs;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * @Author liuQi
 * @Date 2017/10/10 17:11
 * 客户服务单查询
 */
@Controller
public class CustomerServiceOrderController extends BaseController{

    /* 查询客户服务单 */
    @RequiresPermissions("consumerManager:customerServiceOrder")
    @GetMapping("/consumerManager/customerServiceOrder/list.php")
    public String list(PagerSpec pagerSpec, HttpServletRequest request,Model model, CustomerServiceOrderRq param){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        model.addAttribute("year",year);
        param.setPage(pagerSpec.getCurrentPage());
        param.setSize(pagerSpec.getPerPageNum());
        if(CommonUtils.nullOrBlank(param.getDate())){
            param.setDate(String.valueOf(year));
        }
        QuestionAcceptedListRs questionAccepts = SoaConnectionFactory.get(request, ConstantsUri.CUSTOMER_SERVICE_ORDER, param, QuestionAcceptedListRs.class);
        model.addAttribute("serviceOrders",questionAccepts.getDataList());
        pagerSpec.setTotalItems(questionAccepts.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "consumer/customerServiceOrder/list";
    }









}

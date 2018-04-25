package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.MytgmRq;
import com.abc.soa.response.cms.Long2ShortServiceRs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单管理
 *
 * @author zhushuai 2017-6-29
 */
@Controller
public class Long2ShortServiceController extends BaseController {
    /**
     * 微信模版消息日志列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequestMapping("/cms/long2short/list.php")
    public String dowloadList(MytgmRq rq, HttpServletRequest request, Model model) {
        model.addAttribute("BaseRq", rq);
        return "/cms/long2short/list";
    }

    /**
     * 微信模版消息日志列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequestMapping("/cms/long2short.php")
    public @ResponseBody Long2ShortServiceRs downloadproduct(MytgmRq rq,@RequestParam(value = "type", required = false) String type, HttpServletRequest request, Model model) {
        Long2ShortServiceRs rs = new Long2ShortServiceRs();
        if("1".equals(type)){
             rs = SoaConnectionFactory.get(request, ConstantsUri.LONG2SHORT_TINY, rq, Long2ShortServiceRs.class);
        }
        else if("2".equals(type)){
             rs = SoaConnectionFactory.get(request, ConstantsUri.LONG2SHORT_WX, rq, Long2ShortServiceRs.class);
        }
        return rs;
    }
}

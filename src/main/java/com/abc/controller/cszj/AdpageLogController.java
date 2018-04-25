package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.AdpageLogRq;
import com.abc.soa.response.cszj.AdpageLogRs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;


/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/adpagelog")
public class AdpageLogController {

    protected static Logger logger = LoggerFactory.getLogger(AdpageLogController.class);

    @RequestMapping("/list.php")
    public String adpageList(AdpageLogRq adpageLogRq, HttpServletRequest request, Model model) {
        AdpageLogRs adpageRs = SoaConnectionFactory.get(request, ConstantsUri.ADPAGELOG_LIST, adpageLogRq, AdpageLogRs.class);
        logger.info("--------{}",adpageLogRq);
        model.addAttribute("adpagelogRs", adpageRs.getDataList());
        adpageLogRq.setTotalItems(adpageRs.getTotal());
        adpageLogRq.calculate();
        model.addAttribute("pagination", adpageLogRq);
        return "cszj/adpage/list_log";
    }

}

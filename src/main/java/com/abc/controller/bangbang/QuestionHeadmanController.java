package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.dto.bangbang.HeadmanStatus;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.bangbang.QuestionHeadman;
import com.abc.soa.response.bangbang.QuestionHeadmanBoRs;
import com.abc.soa.response.bangbang.QuestionHeadmanListRs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/16 11:14
 * 掌门人管理
 */
@Controller
public class QuestionHeadmanController {

    @GetMapping("/bangbang/questionHeadman/list.php")
    public String list(HttpServletRequest request, PagerSpec pagerSpec, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("page", String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size", String.valueOf(pagerSpec.getPerPageNum()));
        QuestionHeadmanListRs headmanList = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_HEADMAN_LIST, map, QuestionHeadmanListRs.class);
        model.addAttribute("headmanList", headmanList.getDataList());
        pagerSpec.setTotalItems(headmanList.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        model.addAttribute("headmanStatus", HeadmanStatus.values());
        return "bangbang/questionHeadman/list";
    }

    @GetMapping("/bangbang/questionHeadman/view/{id}.php")
    public String view(@PathVariable("id") String id,  HttpServletRequest request, Model model){
        QuestionHeadmanBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_HEADMAN_VIEW, null, QuestionHeadmanBoRs.class, id);
        model.addAttribute("headman",rs.getData());
        return "bangbang/questionHeadman/form";
    }

    @GetMapping("/bangbang/questionHeadman/modifyStatus/{id}.php")
    public String modifyStatusView(@PathVariable("id") String id, HttpServletRequest request, Model model){
        QuestionHeadmanBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_HEADMAN_VIEW, null, QuestionHeadmanBoRs.class, id);
        model.addAttribute("headman",rs.getData());
        model.addAttribute("isChangeStatus",true);
        return "bangbang/questionHeadman/form";
    }

    /* 掌门人 审核 */
    @PostMapping(path = "/bangbang/questionHeadman/modify.php")
    public @ResponseBody BaseResponse modifyStatus(@RequestBody QuestionHeadman questionheadman, HttpServletRequest request) {
        return SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_QUESTION_HEADMAN_MODIFYSTATUS, questionheadman, BaseResponse.class);
    }

}

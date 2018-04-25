package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.dto.cms.WebsiteType;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.feedback.FeedbackCriteria;
import com.abc.soa.response.cms.feedback.FeedbackListRs;
import com.abc.soa.response.system.bo.DictListBO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/12 11:30
 * 意见反馈 管理
 */
@Controller
public class FeedbackController extends BaseController{

    private final static String FEEDBACK_TYPE = "bb_feedback_feedbackType";

    /* 意见反馈列表查询 */
    @RequiresPermissions("consumerManager:feedback")
    @GetMapping("/cms/feedback/list.php")
    public String list(HttpServletRequest request,Model model, PagerSpec pagerSpec,FeedbackCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        FeedbackListRs rs = SoaConnectionFactory.get(request, ConstantsUri.FEEDBACK_LIST, criteria, FeedbackListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());

        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("feedbacks",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        model.addAttribute("sourceTypes", WebsiteType.values());
        //去数据字典获取校验规则
        DictListBO feedbackTypes = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, FEEDBACK_TYPE);
        model.addAttribute("feedbackTypes",feedbackTypes.getDataList());
        return "cms/feedback/list";
    }


    /* 删除 */
    @RequiresPermissions("consumerManager:feedback")
    @DeleteMapping("/cms/feedback/delete/{id}.php")
    public @ResponseBody
    BaseResponse delete(HttpServletRequest request, @PathVariable String id){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.FEEDBACK_DELETE, null, BaseResponse.class, id);
    }

    /* 批量删除 */
    @RequiresPermissions("consumerManager:feedback")
    @DeleteMapping("/cms/feedback/delete.php")
    public @ResponseBody BaseResponse batchDelete(HttpServletRequest request, @RequestBody List<String> ids){
        return SoaConnectionFactory.delete(request, ConstantsUri.FEEDBACK_DELETE_LIST, ids, BaseResponse.class);
    }

}

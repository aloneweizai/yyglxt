package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.dto.bangbang.ContentAuditStatus;
import com.abc.dto.bangbang.SystemAuditLinkType;
import com.abc.dto.bangbang.SystemAuditType;
import com.abc.dto.bangbang.TipOffAuditStatus;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.bangbang.ContentAuditCriteria;
import com.abc.soa.response.bangbang.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/17 13:17
 * 内容审核
 */
@Controller
public class ContentAuditController {

    /* 初始化列表 */
    @GetMapping("/bangbang/contentAudit/list.php")
    public String contentAudit(HttpServletRequest request, Model model, PagerSpec pagerSpec,
                               @RequestParam(value = "status", required = false)String status,
                               @RequestParam(value = "initModule", defaultValue = "system")String initModule){
        model.addAttribute("status", status);
        model.addAttribute("initModule", initModule);
        return "bangbang/contentAudit/list";
    }

    /* 系统过滤的内容列表 */
    @GetMapping("/bangbang/contentAudit/system/list.php")
    public String systemAudit(HttpServletRequest request, Model model, PagerSpec pagerSpec, ContentAuditCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        QuestionSysBlockBoListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_SYSBLOCK_LIST, criteria, QuestionSysBlockBoListRs.class);
        model.addAttribute("list", rs.getDataList());
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        model.addAttribute("contentAuditStatus", ContentAuditStatus.values());
        model.addAttribute("systemAuditType", SystemAuditType.values());
        model.addAttribute("systemAuditLinkType", SystemAuditLinkType.values());
        model.addAttribute("SNSURL", BaseObject.getConfig("snsdomain"));
        return "bangbang/contentAudit/system_audit";
    }
    /*系统过滤的内容详情*/
    @GetMapping("/bangbang/contentAudit/system/view/{type}/{id}.php")
    public String view(@PathVariable(value = "type") String type, @PathVariable(value = "id") String id,
                       HttpServletRequest request,Model model){
        model.addAttribute("type",type);
        if(type.equals("question")){
            QuestionRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_VIEW, null, QuestionRs.class, id);
            model.addAttribute("data", rs.getData());
        }else if("cheats".equals(type)){
            CheatsRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_CHEAT_VIEW, null, CheatsRs.class, id);
            model.addAttribute("data", rs.getData());
        }else if("answer".equals(type)){
            QuestionAnswerBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_ANSWER_VIEW, null, QuestionAnswerBoRs.class, id);
            QuestionAnswerBo data = rs.getData();
            QuestionRs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_VIEW, null, QuestionRs.class, data.getQuestionId());
            data.setQuestionTitle(questionRs.getData().getTitle());
            model.addAttribute("data", data);
        }else if("comment".equals(type)){
            QuestionCommentBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_COMMENT_VIEW, null, QuestionCommentBoRs.class, id);
            QuestionCommentBo data = rs.getData();
            QuestionRs questionRs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_VIEW, null, QuestionRs.class, data.getQuestionId());
            data.setQuestionTitle(questionRs.getData().getTitle());
            model.addAttribute("data", data);
        }else if("cheats_comment".equals(type)){
            CheatsCommentBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_CHEAT_COMMENT_VIEW, null, CheatsCommentBoRs.class, id);
            CheatsCommentBo data = rs.getData();
            CheatsRs cheatsRs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_CHEAT_VIEW, null, CheatsRs.class, data.getCheatsId());
            data.setCheatsTitle(cheatsRs.getData().getTitle());
            model.addAttribute("data", data);
        }
        return "bangbang/contentAudit/system_audit_form";
    }

    @RequestMapping("/bangbang/contentAudit/tipOff/getview.php")
    public @ResponseBody Map validateInvoice(HttpServletRequest request,@RequestParam(value = "id", required = false) String id,
                                                 @RequestParam(value = "type", required = false) String type){
        Map map = new HashMap<>();
        if("answer".equals(type)){
            QuestionAnswerBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_ANSWER_VIEW, null, QuestionAnswerBoRs.class, id);
            QuestionAnswerBo data = rs.getData();
            map.put("id",data.getQuestionId());
            return map;
        }else if("comment".equals(type)){
            QuestionCommentBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_COMMENT_VIEW, null, QuestionCommentBoRs.class, id);
            QuestionCommentBo data = rs.getData();
            map.put("id",data.getQuestionId());
            return map;
        }else{
            CheatsCommentBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_CHEAT_COMMENT_VIEW, null, CheatsCommentBoRs.class, id);
            CheatsCommentBo data = rs.getData();
            map.put("id",data.getCheatsId());
            return map;
        }
    }

    /* 过滤内容 修改状态 */
    @PostMapping("/bangbang/contentAudit/system/{id}/{status}.php")
    public @ResponseBody BaseResponse enable(@PathVariable(value = "id") String id, @PathVariable(value = "status") String status,
                        HttpServletRequest request){
        return SoaConnectionFactory.putRestful(request, ConstantsUri.BANGBANG_QUESTION_SYSBLOCK_STATUS, null, BaseResponse.class, id, status);
    }



    /* 被举报的内容列表 */
    @GetMapping("/bangbang/contentAudit/tipOff/list.php")
    public String tipOffAudit(HttpServletRequest request, Model model, PagerSpec pagerSpec,
                              @RequestParam(value = "status", required = false)String status){
        Map map = new HashMap<>();
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        if(!StringUtils.isEmpty(status)){
            map.put("status", status);
        }
        QuestionTipOffBoListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_TIPOFF_LIST, map, QuestionTipOffBoListRs.class);
        model.addAttribute("list", rs.getDataList());
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        model.addAttribute("tipOffAuditStatus", TipOffAuditStatus.values());
        model.addAttribute("systemAuditLinkType", SystemAuditLinkType.values());
        model.addAttribute("SNSURL", BaseObject.getConfig("snsdomain"));
        return "bangbang/contentAudit/tipoff_audit";
    }
    /*举报详情*/
    @GetMapping("/bangbang/contentAudit/tipOff/view/{id}.php")
    public String tipOffAuditView(@PathVariable String id ,HttpServletRequest request, Model model){
        QuestionTipOffBoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_TIPOFF_VIEW, null, QuestionTipOffBoRs.class, id);
        model.addAttribute("data", rs.getData());
        return "bangbang/contentAudit/tipoff_audit_form";
    }
    /* 举报的内容 修改状态 */
    @PostMapping("/bangbang/contentAudit/tipOff/modifyStatus.php")
    public @ResponseBody BaseResponse tipOffStatus(@RequestBody QuestionTipOffBo questionTipOffBo, HttpServletRequest request){
        return SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_QUESTION_TIPOFF_STATUS, questionTipOffBo, BaseResponse.class);
    }



    /* 用户合规审查 列表 */
    @GetMapping("/bangbang/contentAudit/user/list.php")
    public String userAudit(HttpServletRequest request, Model model, PagerSpec pagerSpec){
        Map map = new HashMap<>();
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        QuestionDisableUserBoListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_DISABLEUSER_LIST, map, QuestionDisableUserBoListRs.class);
        model.addAttribute("list", rs.getDataList());
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        return "bangbang/contentAudit/user_audit";
    }
    /* 用户合规审查 撤销禁言  */
    @PostMapping("/bangbang/contentAudit/user/enable/{userId}.php")
    public @ResponseBody BaseResponse userAuditEnable(@PathVariable(value = "userId") String userId, HttpServletRequest request){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_QUESTION_DISABLEUSER_ENABLE, null, BaseResponse.class, userId);
    }
    /* 用户合规审查 禁言  */
    @PostMapping("/bangbang/contentAudit/user/disable.php")
    public @ResponseBody BaseResponse userAuditDisable(@RequestBody QuestionDisableUser record, HttpServletRequest request){
        return SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_QUESTION_DISABLEUSER_DISABLE, record, BaseResponse.class);
    }



    /* IP审查 列表 */
    @GetMapping("/bangbang/contentAudit/ip/list.php")
    public String ipAudit(HttpServletRequest request, Model model, PagerSpec pagerSpec){
        Map map = new HashMap<>();
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        QuestionDisableIpBoListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_DISABLEIP_LIST, map, QuestionDisableIpBoListRs.class);
        model.addAttribute("list", rs.getDataList());
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        return "bangbang/contentAudit/ip_audit";
    }
    /* IP审查 撤销禁言  */
    @PostMapping("/bangbang/contentAudit/ip/enable/{ip}.php")
    public @ResponseBody BaseResponse ipEnable(@PathVariable(value = "ip") String ip, HttpServletRequest request){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_QUESTION_DISABLEIP_ENABLE, null, BaseResponse.class, ip);
    }
    /* IP审查 禁言  */
    @PostMapping("/bangbang/contentAudit/ip/disable.php")
    public @ResponseBody BaseResponse ipDisable(@RequestBody QuestionDisableIp record, HttpServletRequest request){
        return SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_QUESTION_DISABLEIP_DISABLE, record, BaseResponse.class);
    }

}

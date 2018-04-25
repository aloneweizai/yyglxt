package com.abc.controller.bangbang;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.bangbang.ExpertCriteria;
import com.abc.soa.response.bangbang.QuestionExpert;
import com.abc.soa.response.bangbang.QuestionExpertBoListRs;
import com.abc.soa.response.bangbang.QuestionExpertBoRs;
import com.abc.soa.response.cms.course.CourseRs;
import com.abc.soa.response.consumer.ConsumerInfoRs;
import com.abc.soa.response.consumer.bo.Consumer;
import com.abc.soa.response.system.bo.DictListBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/19 16:49
 * 专家大侠管理
 */
@Controller
public class QuestionExpertController {

    /* 专家大侠列表查询 */
    @GetMapping("/bangbang/questionExpert/list.php")
    public String list(HttpServletRequest request,Model model, PagerSpec pagerSpec, ExpertCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        QuestionExpertBoListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_EXPERT_LIST, criteria, QuestionExpertBoListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("experts",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));
        //去数据字典获取
        DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "bb_question_expert_type");
        if(dicts!= null && dicts.getDataList()!=null && dicts.getDataList().size()>0){
            model.addAttribute("expertTypes",dicts.getDataList());
        }
        return "bangbang/expert/list";
    }

    /* 专家大侠新增，修改 GET页面 */
    @GetMapping("/bangbang/questionExpert/edit.php")
    public String edit(HttpServletRequest request,Model model, @RequestParam(value = "id", required = false) String id){
        if(!StringUtils.isEmpty(id)){
            QuestionExpertBoRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_EXPERT_VIEW, null, QuestionExpertBoRs.class, id);
            model.addAttribute("expert", rs.getData());
        }
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));
        //去数据字典获取
        DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "bb_question_expert_type");
        if(dicts!= null && dicts.getDataList()!=null && dicts.getDataList().size()>0){
            model.addAttribute("expertTypes",dicts.getDataList());
        }
        return "bangbang/expert/form";
    }

    /* 专家大侠新增，修改 POST页面 */
    @PostMapping(value = "/bangbang/questionExpert/save.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody QuestionExpert expert) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(expert.getId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_QUESTION_EXPERT_ADD, expert, CourseRs.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_QUESTION_EXPERT_MODIFY, expert, CourseRs.class);
        }
        /*修改用户头像*/
        String userId = expert.getUserId();
        Map map = new HashMap<>();
        map.put("id", userId);
        map.put("userPicturePath", expert.getUserImage());
        SoaConnectionFactory.put(request, ConstantsUri.CONSUMER_MODIFY, map , BaseResponse.class, userId);
        return rs;
    }

    /* 删除 */
    @DeleteMapping(value = "/bangbang/questionExpert/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id,HttpServletRequest request) throws IOException {
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_QUESTION_EXPERT_DELETE, null, BaseResponse.class, id);
    }




    /* 通过用户名或者手机号查询用户 */
    @GetMapping("/bangbang/questionExpert/validUsername/{username}.php")
    public @ResponseBody
    BaseResponse viewByNameOrPhone(HttpServletRequest request, @PathVariable String username){
        ConsumerInfoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.CONSUMER_INFO_BY_NAME_PHONE, null , ConsumerInfoRs.class, username);
        if(rs.isSuccess()){
            Consumer user = rs.getData();
            ConsumerInfoRs res = SoaConnectionFactory.getRestful(request, ConstantsUri.CONSUMER_INFO, null , ConsumerInfoRs.class, user.getId());
            return res;
        }else{
            return new BaseResponse(false,"系统异常");
        }
    }


}

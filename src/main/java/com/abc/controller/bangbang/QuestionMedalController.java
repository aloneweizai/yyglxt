package com.abc.controller.bangbang;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.course.LecturerCriteria;
import com.abc.soa.response.bangbang.*;
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
 * @Date 2017/10/20 18:37
 * 勋章管理
 */
@Controller
public class QuestionMedalController {

    /* 勋章列表查询 */
    @GetMapping("/bangbang/questionMedal/list.php")
    public String list(HttpServletRequest request,Model model, PagerSpec pagerSpec, @RequestParam(value = "name", defaultValue = "") String name){
        Map map = new HashMap<>();
        map.put("name", name);
        map.put("page", String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size", String.valueOf(pagerSpec.getPerPageNum()));
        QuestionMedalBoListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_MEDAL_LIST, map, QuestionMedalBoListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("medals",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));
        return "bangbang/medal/list";
    }

    /* 勋章新增，修改 GET页面 */
    @GetMapping("/bangbang/questionMedal/edit.php")
    public String edit(HttpServletRequest request,Model model, @RequestParam(value = "id", required = false) String id){
        if(!StringUtils.isEmpty(id)){
            QuestionMedalRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_MEDAL_VIEW, null, QuestionMedalRs.class, id);
            model.addAttribute("medal", rs.getData());
        }
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));
        return "bangbang/medal/form";
    }

    /* 勋章新增，修改 POST页面 */
    @PostMapping(value = "/bangbang/questionMedal/save.php")
    public @ResponseBody
    BaseResponse add(HttpServletRequest request, @RequestBody QuestionMedal medal) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(medal.getId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_QUESTION_MEDAL_ADD, medal, BaseResponse.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_QUESTION_MEDAL_MODIFY, medal, BaseResponse.class);
        }
        return rs;
    }

    /* 删除 */
    @DeleteMapping(value = "/bangbang/questionMedal/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id,HttpServletRequest request) throws IOException {
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_QUESTION_MEDAL_DELETE, null, BaseResponse.class, id);
    }


}

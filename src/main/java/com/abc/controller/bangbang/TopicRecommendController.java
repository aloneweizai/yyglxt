package com.abc.controller.bangbang;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.bangbang.CheatsBo;
import com.abc.soa.request.bangbang.TopicRecommendCriteria;
import com.abc.soa.response.bangbang.CheatsRs;
import com.abc.soa.response.bangbang.QuestionRs;
import com.abc.soa.response.bangbang.RecommendBoListRs;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liuQi
 * @Date 2017/10/11 15:08
 * 话题推荐管理
 */
@Controller
public class TopicRecommendController {

    /*话题查询*/
    @GetMapping("/bangbang/topicRecommend/list.php")
    public String list(PagerSpec pagerSpec, HttpServletRequest request,Model model, TopicRecommendCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage());
        criteria.setSize(pagerSpec.getPerPageNum());
        RecommendBoListRs questions = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_TOPICRECOMMEND_LIST, criteria, RecommendBoListRs.class);
        model.addAttribute("questions",questions.getDataList());
        pagerSpec.setTotalItems(questions.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));
        model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        return "bangbang/topicRecommend/list";
    }

    /*话题详情*/
    @GetMapping("/bangbang/topicRecommend/view/{type}/{id}.php")
    public String view(@PathVariable(value = "type") String type, @PathVariable(value = "id") String id,
                       HttpServletRequest request,Model model, TopicRecommendCriteria criteria){
        if(type.equals("question")){
            QuestionRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_QUESTION_VIEW, null, QuestionRs.class, id);
            model.addAttribute("data", rs.getData());
        }else{
            CheatsRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_CHEAT_VIEW, null, CheatsRs.class, id);
            model.addAttribute("data", rs.getData());
            model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        }
        return "bangbang/topicRecommend/form";
    }

    /*话题推荐*/
    @PostMapping("/bangbang/topicRecommend/recommend/{type}/{id}/{isRecommend}.php")
    public @ResponseBody BaseResponse recommend(@PathVariable(value = "type") String type,@PathVariable(value = "id") String id,
                        @PathVariable(value = "isRecommend") Boolean isRecommend,HttpServletRequest request,
                                                @RequestParam(required = false) String imgUrl) {
        CheatsBo bo = new CheatsBo();
        if (!StringUtils.isEmpty(imgUrl)) {
            bo.setCheatsImage(imgUrl);
        }
        else{
            bo.setCheatsImage("");
        }
        if (type.equals("question")) {
            return SoaConnectionFactory.putRestful(request, ConstantsUri.BANGBANG_TOPICRECOMMEND_RECOMMEND, null, BaseResponse.class, id, isRecommend);
        } else {
            return SoaConnectionFactory.putRestful(request, ConstantsUri.BANGBANG_CHEATS_RECOMMEND, bo, BaseResponse.class, id, isRecommend);
        }
    }

}

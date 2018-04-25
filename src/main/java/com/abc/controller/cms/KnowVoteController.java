package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.Knowledge.KnowledgeVoteCriteria;
import com.abc.soa.response.cms.knowledge.KnowledgeVoteLogBOListRs;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/10 16:08
 * 知识库投票管理
 */
@Controller
public class KnowVoteController extends BaseController{

    /* 投票列表查询 */
    @RequiresPermissions("consumerManager:knowVote")
    @GetMapping("/cms/knowVote/list.php")
    public String list(HttpServletRequest request, Model model){
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/cms/knowVote/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("initPageLink", initPageLink);
        return "cms/knowledge/vote/list";
    }

    /* 投票列表查询 */
    @RequiresPermissions("consumerManager:knowVote")
    @GetMapping("/cms/knowVote/page.php")
    public String page(HttpServletRequest request,Model model, PagerSpec pagerSpec,KnowledgeVoteCriteria criteria){
        String link = pagerSpec.getLink();
        String currLink = new StringBuilder(request.getContextPath()).append("/cms/knowVote/list.php").append(link.substring(link.indexOf("?"))).toString();

        criteria.setPage(pagerSpec.getCurrentPage());
        criteria.setSize(pagerSpec.getPerPageNum());
        KnowledgeVoteLogBOListRs knowVotes = SoaConnectionFactory.get(request, ConstantsUri.KNOW_VOTE_LIST, criteria, KnowledgeVoteLogBOListRs.class);

        pagerSpec.setTotalItems(knowVotes.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("knowVotes",knowVotes);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", currLink.replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "cms/knowledge/vote/list_page";
    }

    /* 投票删除 */
    @RequiresPermissions("consumerManager:knowVote")
    @DeleteMapping("/cms/knowVote/delete/{id}.php")
    public @ResponseBody BaseResponse delete(HttpServletRequest request, @PathVariable String id){
        BaseResponse rs = SoaConnectionFactory.deleteRestful(request, ConstantsUri.KNOW_VOTE_DELETE, null, BaseResponse.class, id);
        return rs;
    }

    /* 投票批量删除 */
    @RequiresPermissions("consumerManager:knowVote")
    @DeleteMapping("/cms/knowVote/delete.php")
    public @ResponseBody BaseResponse batchDelete(HttpServletRequest request, @RequestBody List<String> ids){
        return SoaConnectionFactory.delete(request, ConstantsUri.KNOW_VOTE_BATCH_DELETE, ids, BaseResponse.class);
    }

}

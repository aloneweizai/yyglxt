package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.bangbang.FactionMemberRewardCriteria;
import com.abc.soa.request.bangbang.FactionRewardSettingCriteria;
import com.abc.soa.response.bangbang.QuestionFactionMemberReward;
import com.abc.soa.response.bangbang.QuestionFactionMemberRewardListRs;
import com.abc.soa.response.bangbang.QuestionFactionRewardSetting;
import com.abc.soa.response.bangbang.QuestionFactionRewardSettingBoListRs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/28 16:19
 * 帮派积分奖励分配
 */
@Controller
public class RewardPointSettingController {

    /* 初始化列表 */
    @GetMapping("/bangbang/rewardPointSetting/list.php")
    public String list(){
        return "bangbang/rewardsPointSetting/list";
    }

    /* 列表查询 */
    @GetMapping("/bangbang/rewardPointSetting/faction/list.php")
    public String factionList(HttpServletRequest request,Model model, PagerSpec pagerSpec, FactionRewardSettingCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        QuestionFactionRewardSettingBoListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_FACTIONREWARDSETTING_LIST, criteria, QuestionFactionRewardSettingBoListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("list",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "bangbang/rewardsPointSetting/factionPointSetting_page";
    }

    /* POST页面 */
    @PostMapping(value = "/bangbang/rewardPointSetting/faction.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody QuestionFactionRewardSetting record) throws IOException {
        return SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_FACTIONREWARDSETTING_ADD, record, BaseResponse.class);
    }


    /* 列表查询 */
    @GetMapping("/bangbang/rewardPointSetting/factionMember/list.php")
    public String factionMemberList(HttpServletRequest request,Model model, PagerSpec pagerSpec, FactionMemberRewardCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        QuestionFactionMemberRewardListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_FACTIONMEMBERREWARD_LIST, criteria, QuestionFactionMemberRewardListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("list",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "bangbang/rewardsPointSetting/factionMemberPointSetting_page";
    }

    /* 审核 */
    @PostMapping(value = "/bangbang/rewardPointSetting/factionMember/audit.php")
    public @ResponseBody BaseResponse audit(HttpServletRequest request, @RequestBody List<QuestionFactionMemberReward> records) throws IOException {
        return SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_FACTIONMEMBERREWARD_AUDIT, records, BaseResponse.class);
    }


}

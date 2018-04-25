package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.bangbang.QuestionFactionMemberLevel;
import com.abc.soa.response.bangbang.QuestionFactionMemberLevelListRs;
import com.abc.soa.response.bangbang.QuestionFactionMemberLevelRs;
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
 * @Date 2017/10/25 09:44
 * 帮手等级设置
 */
@Controller
public class FactionMemberLevelController {

    /* 帮手列表查询 */
    @GetMapping("/bangbang/factionMemberLevel/list.php")
    public String list(HttpServletRequest request,Model model, PagerSpec pagerSpec){
        Map map = new HashMap<>();
        map.put("page", String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size", String.valueOf(pagerSpec.getPerPageNum()));
        QuestionFactionMemberLevelListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_FACTIONMEMBERLEVEL_LIST, map, QuestionFactionMemberLevelListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("factionMembers",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "bangbang/factionMemberLevel/list";
    }

    /* 帮手新增，修改 GET页面 */
    @GetMapping("/bangbang/factionMemberLevel/edit.php")
    public String edit(HttpServletRequest request,Model model, @RequestParam(value = "id", required = false) String id){
        if(!StringUtils.isEmpty(id)){
            QuestionFactionMemberLevelRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_FACTIONMEMBERLEVEL_VIEW, null, QuestionFactionMemberLevelRs.class, id);
            model.addAttribute("factionMember", rs.getData());
        }
        return "bangbang/factionMemberLevel/form";
    }

    /* 帮手新增，修改 POST页面 */
    @PostMapping(value = "/bangbang/factionMemberLevel/save.php")
    public @ResponseBody
    BaseResponse add(HttpServletRequest request, @RequestBody QuestionFactionMemberLevel memberLevel) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(memberLevel.getId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_FACTIONMEMBERLEVEL_ADD, memberLevel, BaseResponse.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_FACTIONMEMBERLEVEL_MODIFY, memberLevel, BaseResponse.class);
        }
        return rs;
    }

    /* 删除 */
    @DeleteMapping(value = "/bangbang/factionMemberLevel/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id,HttpServletRequest request) throws IOException {
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_FACTIONMEMBERLEVEL_DELETE, null, BaseResponse.class, id);
    }



}

package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.bangbang.QuestionFactionLevel;
import com.abc.soa.response.bangbang.QuestionFactionLevelListRs;
import com.abc.soa.response.bangbang.QuestionFactionLevelRs;
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
 * 帮派等级设置
 */
@Controller
public class FactionLevelController {

    /* 帮派列表查询 */
    @GetMapping("/bangbang/factionLevel/list.php")
    public String list(HttpServletRequest request,Model model, PagerSpec pagerSpec){
        Map map = new HashMap<>();
        map.put("page", String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size", String.valueOf(pagerSpec.getPerPageNum()));
        QuestionFactionLevelListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_FACTIONLEVEL_LIST, map, QuestionFactionLevelListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("factions",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, false));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "bangbang/factionLevel/list";
    }

    /* 帮派新增，修改 GET页面 */
    @GetMapping("/bangbang/factionLevel/edit.php")
    public String edit(HttpServletRequest request,Model model, @RequestParam(value = "id", required = false) String id){
        if(!StringUtils.isEmpty(id)){
            QuestionFactionLevelRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_FACTIONLEVEL_VIEW, null, QuestionFactionLevelRs.class, id);
            model.addAttribute("faction", rs.getData());
        }
        return "bangbang/factionLevel/form";
    }

    /* 帮派新增，修改 POST页面 */
    @PostMapping(value = "/bangbang/factionLevel/save.php")
    public @ResponseBody
    BaseResponse add(HttpServletRequest request, @RequestBody QuestionFactionLevel factionLevel) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(factionLevel.getId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_FACTIONLEVEL_ADD, factionLevel, BaseResponse.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_FACTIONLEVEL_MODIFY, factionLevel, BaseResponse.class);
        }
        return rs;
    }

    /* 删除 */
    @DeleteMapping(value = "/bangbang/factionLevel/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id,HttpServletRequest request) throws IOException {
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_FACTIONLEVEL_DELETE, null, BaseResponse.class, id);
    }



}

package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.bangbang.SensitiveWords;
import com.abc.soa.response.bangbang.SensitiveWordsListRs;
import com.abc.soa.response.bangbang.SensitiveWordsRs;
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
 * @Date 2017/10/23 10:27
 * 敏感词管理
 */
@Controller
public class SensitiveWordsController {

    /* 敏感词列表查询 */
    @GetMapping("/bangbang/sensitiveWords/list.php")
    public String list(HttpServletRequest request,Model model, PagerSpec pagerSpec, @RequestParam(value = "keywords", defaultValue = "") String keywords){
        Map map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        SensitiveWordsListRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_SENSITIVEWORDS_LIST, map, SensitiveWordsListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("sensitiveWords",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "bangbang/sensitiveWords/list";
    }

    /* 敏感词新增，修改 GET页面 */
    @GetMapping("/bangbang/sensitiveWords/edit.php")
    public String edit(HttpServletRequest request,Model model, @RequestParam(value = "id", required = false) String id){
        if(!StringUtils.isEmpty(id)){
            SensitiveWordsRs rs = SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_SENSITIVEWORDS_VIEW, null, SensitiveWordsRs.class, id);
            model.addAttribute("sensitiveWords", rs.getData());
        }
        return "bangbang/sensitiveWords/form";
    }

    /* 敏感词新增，修改 POST页面 */
    @PostMapping(value = "/bangbang/sensitiveWords/save.php")
    public @ResponseBody
    BaseResponse add(HttpServletRequest request, @RequestBody SensitiveWords sensitiveWords) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(sensitiveWords.getId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_SENSITIVEWORDS_ADD, sensitiveWords, BaseResponse.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_SENSITIVEWORDS_MODIFY, sensitiveWords, BaseResponse.class);
        }
        return rs;
    }

    /* 删除 */
    @DeleteMapping(value = "/bangbang/sensitiveWords/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id,HttpServletRequest request) throws IOException {
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_SENSITIVEWORDS_DELETE, null, BaseResponse.class, id);
    }
}

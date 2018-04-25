package com.abc.controller.bangbang;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.bangbang.*;
import com.abc.soa.response.cms.knowledge.KnowledgeTagListRs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/12 16:56
 * 帮帮分类设置
 */
@Controller
public class QuestionClassifyController {

    @GetMapping("/bangbang/questionClassify/cateTag/list.php")
    public String cateTag(HttpServletRequest request, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType", "xthf_bb");
        KnowledgeTagListRs knowledgeTagList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagListRs.class);
        model.addAttribute("tagList", knowledgeTagList);
        return "bangbang/questionClassify/list";
    }

    /**
     * ajax查询所有分类
     * @param request
     * @return
     */
    @GetMapping("/bangbang/questionClassify/ajaxList.php")
    public @ResponseBody BaseResponse ajaxCateList(HttpServletRequest request){
        return SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_CLASSIFY_LIST, null, QuestionClassifyListRs.class);
    }

    /* 分类新增，修改 */
    @PostMapping("/bangbang/questionClassify/save.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody QuestionClassify category){
        if(CommonUtils.nullOrBlank(category.getClassifyId())){
            return SoaConnectionFactory.post(request, ConstantsUri.BANGBANG_QUESTION_CLASSIFY_ADD, category, QuestionClassifyRs.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_QUESTION_CLASSIFY_MODIFY, category, BaseResponse.class, category.getClassifyId());
        }
    }

    /* 分类删除 */
    @DeleteMapping("/bangbang/questionClassify/delete/{id}.php")
    public @ResponseBody BaseResponse delete(HttpServletRequest request, @PathVariable String id){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BANGBANG_QUESTION_CLASSIFY_DELETE, null, BaseResponse.class, id);
    }

    /**
     * 跳转知识分类构建树
     * @return
     */
    @GetMapping("/bangbang/questionClassify/tree")
    public String ajaxTree(){
        return "cms/common/tree";
    }

    /* 根据分类获取关联标签 */
    @GetMapping("/bangbang/questionClassify/cateTag/ajaxList.php")
    public @ResponseBody BaseResponse ajaxCateTagList(HttpServletRequest request,
                                                      @RequestParam(value = "classifyCode") String classifyCode){
        Map<String, Object> map = new HashMap<>();
        map.put("classifyCode", classifyCode);
        return SoaConnectionFactory.get(request, ConstantsUri.BANGBANG_QUESTION_CLASSIFYTAG, map, QuestionClassifyTagListRs.class);
    }

    /* 修改分类标签关系 */
    @PostMapping("/bangbang/questionClassify/cateTag/modify.php")
    public @ResponseBody BaseResponse modifyCateTag(HttpServletRequest request,
                                                    @RequestBody QuestionClassify questionClassify){
        return SoaConnectionFactory.put(request, ConstantsUri.BANGBANG_QUESTION_CLASSIFYTAG_MODIFY, questionClassify, BaseResponse.class);
    }



}

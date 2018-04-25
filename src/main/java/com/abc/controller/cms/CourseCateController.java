package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.course.*;
import com.abc.soa.response.cms.knowledge.KnowledgeTagListRs;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuqi
 * @Date 2017/8/17 17:13
 * 课程分类管理
 */
@Controller
public class CourseCateController {

    /**
     * ajax查询所有分类
     * @param request
     * @return
     */
    @GetMapping("/cms/course/cate/ajaxList.php")
    public @ResponseBody BaseResponse ajaxCateList(HttpServletRequest request){
        return SoaConnectionFactory.get(request, ConstantsUri.COURSE_CATEGORY, null, CourseCategoryListRs.class);
    }

    /* 分类新增，修改 */
    @PostMapping("/cms/course/cate/save.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody CourseCategory category){
        if(CommonUtils.nullOrBlank(category.getClassifyId())){
            return SoaConnectionFactory.post(request, ConstantsUri.COURSE_CATEGORY_ADD, category, CourseCategoryRs.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.COURSE_CATEGORY_MODIFY, category, BaseResponse.class, category.getClassifyId());
        }
    }

    /* 分类删除 */
    @DeleteMapping("/cms/course/cate/delete/{id}.php")
    public @ResponseBody BaseResponse delete(HttpServletRequest request, @PathVariable String id){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.COURSE_CATEGORY_DELETE, null, BaseResponse.class, id);
    }

    /**
     * 跳转知识分类构建树
     * @return
     */
    @GetMapping("/cms/course/cate/tree")
    public String ajaxTree(){
        return "cms/common/tree";
    }

    @GetMapping("/cms/course/cateTag/list.php")
    public String cateTag(HttpServletRequest request, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("page","0");
        map.put("size","0");
        map.put("status","true");
        map.put("tagType", "xthf_course");
        KnowledgeTagListRs knowledgeTagList = SoaConnectionFactory.get(request, ConstantsUri.KNOW_TAG_LIST, map, KnowledgeTagListRs.class);
        model.addAttribute("tagList", knowledgeTagList);
        return "cms/course/category_tag/list";
    }

    /* 根据分类获取关联标签 */
    @GetMapping("/cms/course/cateTag/ajaxList.php")
    public @ResponseBody BaseResponse ajaxCateTagList(HttpServletRequest request,
                                                      @RequestParam(value = "classifyId") String classifyId,
                                                      @RequestParam(value = "tagName", defaultValue = "") String tagName){
        Map<String, Object> map = new HashMap<>();
        map.put("classifyId", classifyId);
        map.put("tagName", tagName);
        return SoaConnectionFactory.get(request, ConstantsUri.COURSE_CATEGORY_TAG, map, CourseCategoryTagListRs.class);
    }

    /* 根据分类获取关联标签 */
    @GetMapping("/cms/course/cateTag.php")
    public String cateTagList(HttpServletRequest request,@RequestParam(value = "classifyId") String classifyId, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("classifyId", classifyId);
        CourseCategoryTagListRs rs =  SoaConnectionFactory.get(request, ConstantsUri.COURSE_CATEGORY_TAG, map, CourseCategoryTagListRs.class);
        model.addAttribute("tagList", rs);
        return "cms/course/label";
    }

    /* 修改分类标签关系 */
    @PostMapping("/cms/course/cateTag/modify.php")
    public @ResponseBody BaseResponse modifyCateTag(HttpServletRequest request,
                                                      @RequestBody CourseCategory courseCategory){
        return SoaConnectionFactory.putRestful(request, ConstantsUri.COURSE_CATEGORY_TAG_MODIFY, courseCategory, BaseResponse.class, courseCategory.getClassifyId());
    }

    @PostMapping("/cms/course/addTagAndRefClassify.php")
     public @ResponseBody CourseCategoryTagListRs addTagAndRefClassify(HttpServletRequest request, @RequestBody List<CourseCategoryTag> tagList){
        CourseCategoryTagListRs resp = SoaConnectionFactory.put(request, ConstantsUri.COURSE_ADDTAG_AND_REFCLASSIFY, tagList, CourseCategoryTagListRs.class);
        return resp;
    }

}

package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.course.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/8/21 14:29
 * 课时管理
 */
@Controller
public class CoursewareController {

    /* 新增章 */
    @PostMapping(value = "/cms/courseware/chapter/save.php")
    public @ResponseBody BaseResponse chapterAdd(HttpServletRequest request, @RequestBody CourseChapter courseChapter) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(courseChapter.getChapterId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.COURSE_CHAPTER, courseChapter, CourseChapterRs.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.COURSE_CHAPTER_ID, courseChapter, CourseChapterRs.class, courseChapter.getChapterId());
        }
        return rs;
    }

    /* 章删除 */
    @DeleteMapping("/cms/courseware/chapter/delete/{id}.php")
    public @ResponseBody BaseResponse deleteChapter(HttpServletRequest request, @PathVariable(value = "id") String id){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.COURSE_CHAPTER_ID, null, BaseResponse.class, id);
    }

    /* 弹出 章框 */
    @GetMapping(value = "/cms/courseware/chapter/ajaxForm.php")
    public String chapterAjaxForm(HttpServletRequest request, Model model,@RequestParam(required = false, value = "id") String id){
        if(!CommonUtils.nullOrBlank(id)){
            CourseChapterRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_CHAPTER_ID, null, CourseChapterRs.class, id);
            model.addAttribute("chapter", rs.getData());
        }
        return "cms/course/courseware/chapter_form";
    }



    /* 新增课时 */
    @PostMapping(value = "/cms/courseware/save.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody Courseware courseware) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(courseware.getCoursewareId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.COURSE_COURSEWARE, courseware, CoursewareRs.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.COURSE_COURSEWARE_ID, courseware, CoursewareRs.class, courseware.getCoursewareId());
        }
        return rs;
    }

    /* 课时删除 */
    @DeleteMapping("/cms/courseware/delete/{id}.php")
    public @ResponseBody BaseResponse delete(HttpServletRequest request, @PathVariable(value = "id") String id){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.COURSE_COURSEWARE_ID, null, BaseResponse.class, id);
    }

    /* 弹出 课件框 */
    @GetMapping(value = "/cms/courseware/ajaxForm.php")
    public String ajaxForm(HttpServletRequest request, Model model,
                           @RequestParam(required = false, value = "id") String id,
                           @RequestParam(required = false, value = "chapterId") String chapterId){
        if(!CommonUtils.nullOrBlank(id)){
            CoursewareRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_COURSEWARE_ID, null, CoursewareRs.class, id);
            model.addAttribute("courseware", rs.getData());
        }
        return "cms/course/courseware/courseware_form";
    }

    /* 从素材库中查询 */
    @GetMapping("/cms/courseware/ajaxList.php")
    public String list(HttpServletRequest request, Model model, PagerSpec pagerSpec, String fileName){
        Map map = new HashMap<String,String>();
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        map.put("fileName",CommonUtils.nullToString(fileName));
        map.put("type","video");
        map.put("uploadWay","1");
        CoursewareListRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_COURSEWARE_LIST, map, CoursewareListRs.class);
        model.addAttribute("mediaFiles",rs.getDataList());

        pagerSpec.setTotalItems(rs.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));

        return "cms/course/courseware/video_list";
    }

}

package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.dto.cms.course.TeachMethodType;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.course.CourseOrderCriteria;
import com.abc.soa.request.cms.course.CourseStudyCriteria;
import com.abc.soa.response.cms.course.*;
import com.abc.soa.response.system.bo.DictListBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liuQi
 * @Date 2017/9/4 11:30
 */
@Controller
public class CourseStatisticsController {

    /* 课程学习情况 */
    @GetMapping("/cms/course/statistics/{id}.php")
    public String statistics(HttpServletRequest request, Model model, PagerSpec pagerSpec, @PathVariable(value = "id") String id){
        model.addAttribute("curriculumId", id);
        CourseRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_VIEW, null, CourseRs.class, id);
        Course course = rs.getData();
        model.addAttribute("course",course);
        return "cms/course/statistics/list";
    }

    /* 课程学习情况 */
    @GetMapping("/cms/course/statistics/study/{id}.php")
    public String courseStudy(HttpServletRequest request, Model model,PagerSpec pagerSpec,
                              @PathVariable(value = "id") String id, CourseStudyCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum()).setCurriculumId(id);

        CourseStudyListRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_STUDY, criteria, CourseStudyListRs.class);
        model.addAttribute("list", rs.getDataList());
        pagerSpec.setTotalItems(rs.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));

        CourseSituationRs situationRs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_SITUATION, null, CourseSituationRs.class, id);
        model.addAttribute("situation", situationRs.getData());
        model.addAttribute("teachMethodType", TeachMethodType.values());
        return "cms/course/statistics/course_study";
    }

    /* 报名签到情况 */
    @GetMapping("/cms/course/statistics/sign/{id}.php")
    public String courseSign(HttpServletRequest request, Model model,CourseStudyCriteria criteria,
                             PagerSpec pagerSpec, @PathVariable(value = "id") String id){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum()).setCurriculumId(id);

        CourseApplyListRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_SIGN, criteria, CourseApplyListRs.class);
        model.addAttribute("list", rs.getDataList());

        pagerSpec.setTotalItems(rs.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));

        CourseSituationRs situationRs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_SITUATION, null, CourseSituationRs.class, id);
        model.addAttribute("situation", situationRs.getData());
        model.addAttribute("teachMethodType", TeachMethodType.values());
        return "cms/course/statistics/course_sign";
    }

    /* 课程订购情况 */
    @GetMapping("/cms/course/statistics/order/{id}.php")
    public String courseOrder(HttpServletRequest request, Model model,PagerSpec pagerSpec, CourseOrderCriteria criteria,
                              @PathVariable(value = "id") String id){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum()).setGoodsId(id);
        CourseOrderListRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_ORDER, criteria, CourseOrderListRs.class);
        model.addAttribute("list", rs.getDataList());

        pagerSpec.setTotalItems(rs.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        CourseSituationRs situationRs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_SITUATION, null, CourseSituationRs.class, id);
        model.addAttribute("situation", situationRs.getData());
        model.addAttribute("teachMethodType", TeachMethodType.values());

        DictListBO dicts = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "orderStatus");
        model.addAttribute("orderStatus", dicts.getDataList());
        return "cms/course/statistics/course_order";
    }


}

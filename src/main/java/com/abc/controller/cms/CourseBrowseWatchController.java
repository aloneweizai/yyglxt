package com.abc.controller.cms;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.DateUtil;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.course.CourseBrowseWatchCriteria;
import com.abc.soa.request.cms.course.CourseWatchUserListCriteria;
import com.abc.soa.response.cms.course.CourseListBoRs;
import com.abc.soa.response.cms.course.CourseStudyListRs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @Author liuQi
 * @Date 2018/2/1 18:22
 */
@Controller
public class CourseBrowseWatchController {

    /* 课程观看浏览列表 */
    @GetMapping(value = "/cms/courseBrowseWatch/list.php")
    public String list(HttpServletRequest request, Model model,
                       @RequestParam(value = "initModule", defaultValue = "")String initModule,
                       @RequestParam(value = "initPageLink", defaultValue = "/cms/courseBrowseWatch/list_day.php")String initPageLink) throws IOException {
        initPageLink = request.getContextPath()+initPageLink;
        if(initModule.equals("day")){
            initPageLink = initPageLink+ "?date="+ DateUtil.getStrDate();
        }
        if(initModule.equals("month")){
            initPageLink = request.getContextPath()+"/cms/courseBrowseWatch/list_month.php"+ "?month="+ DateUtil.formatDateTime(new Date(),"yyyy-MM");
        }
        model.addAttribute("initPageLink", initPageLink);
        return "cms/course/browser_watch/list";
    }


    /* 课程观看浏览列表 */
    @GetMapping(value = "/cms/courseBrowseWatch/list_day.php")
    public String list_day(HttpServletRequest request, Model model, PagerSpec pagerSpec,CourseBrowseWatchCriteria criteria) throws IOException {

        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        CourseListBoRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_BROWSERWATCH_DAY_LIST, criteria, CourseListBoRs.class);
        model.addAttribute("list", rs.getDataList());
        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("SNS_PATH", SpringCtxHolder.getProperty("SNS_PATH"));
        return "cms/course/browser_watch/list_day";
    }

    /* 课程观看浏览列表 */
    @GetMapping(value = "/cms/courseBrowseWatch/list_month.php")
    public String list_month(HttpServletRequest request, Model model, PagerSpec pagerSpec,CourseBrowseWatchCriteria criteria) throws IOException {
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        CourseListBoRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_BROWSERWATCH_MONTH_LIST, criteria, CourseListBoRs.class);
        model.addAttribute("list", rs.getDataList());

        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("SNS_PATH", SpringCtxHolder.getProperty("SNS_PATH"));
        return "cms/course/browser_watch/list_month";
    }


    /* 课程观看浏览列表 */
    @RequestMapping(value = "/cms/courseBrowseWatch/watch_userlist.php")
    public String watch_userlist(HttpServletRequest request, Model model,CourseWatchUserListCriteria criteria,@RequestParam(value = "initModule", required = false) String initModule) throws IOException {
        if("month".equals(initModule)){
            criteria.setBegintime(criteria.getBegintime()+"-01");
            criteria.setEndtime(criteria.getEndtime()+ "-"+ DateUtil.getLastMonthDay(criteria.getEndtime().substring(0, 4), criteria.getEndtime().substring(5, 7)));
        }
        CourseStudyListRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_STUDY, criteria, CourseStudyListRs.class);
        model.addAttribute("list", rs.getDataList());
        criteria.setTotalItems(rs.getTotal());
        criteria.setTotalPage((int) Math.ceil((double) criteria.getTotalItems() / (double) criteria.getSize()));
        criteria.calculate();
        model.addAttribute("BaseRq", criteria);
        return "cms/course/browser_watch/watch_userlist";
    }
}

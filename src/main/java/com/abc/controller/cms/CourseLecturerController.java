package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.course.LecturerCriteria;
import com.abc.soa.response.cms.course.CourseLecturer;
import com.abc.soa.response.cms.course.CourseLecturerListRs;
import com.abc.soa.response.cms.course.CourseLecturerRs;
import com.abc.soa.response.consumer.ConsumerInfoRs;
import com.abc.soa.response.consumer.bo.Consumer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/8/16 10:18
 * 课程讲师 管理
 */
@Controller
public class CourseLecturerController {

    /* 讲师列表查询 */
    @GetMapping("/cms/course/lecturer/list.php")
    public String list(HttpServletRequest request,Model model, PagerSpec pagerSpec, LecturerCriteria criteria){
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        CourseLecturerListRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_LECTURER_LIST, criteria, CourseLecturerListRs.class);
        pagerSpec.setTotalItems(rs.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("lecturers",rs.getDataList());
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec));
        model.addAttribute("currLink", pagerSpec.getLink().toString().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "cms/course/lecturer/list";
    }

    /* 讲师新增，修改 GET页面 */
    @GetMapping("/cms/course/lecturer/edit.php")
    public String edit(HttpServletRequest request,Model model, @RequestParam(value = "id", required = false) String id){
        if(!StringUtils.isEmpty(id)){
            CourseLecturerRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_LECTURER, null, CourseLecturerRs.class, id);
            model.addAttribute("lecturer",rs.getData());
        }
        return "cms/course/lecturer/form";
    }

    /* 讲师新增，修改 */
    @PostMapping("/cms/course/lecturer/save.php")
    public @ResponseBody
    BaseResponse add(HttpServletRequest request, @RequestBody CourseLecturer lecturer){
        if(StringUtils.isEmpty(lecturer.getLecturerId())){
            return SoaConnectionFactory.post(request, ConstantsUri.COURSE_LECTURER_ADD, lecturer, BaseResponse.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.COURSE_LECTURER, lecturer, BaseResponse.class, lecturer.getLecturerId());
        }
    }

    /* 讲师删除 */
    @DeleteMapping("/cms/course/lecturer/delete/{id}.php")
    public @ResponseBody BaseResponse delete(HttpServletRequest request, @PathVariable String id){
        BaseResponse rs = SoaConnectionFactory.deleteRestful(request, ConstantsUri.COURSE_LECTURER, null, BaseResponse.class, id);
        return rs;
    }

    /* 讲师批量删除 */
    @DeleteMapping("/cms/course/lecturer/delete.php")
    public @ResponseBody BaseResponse batchDelete(HttpServletRequest request, @RequestBody List<String> ids){
        return SoaConnectionFactory.delete(request, ConstantsUri.KNOW_VOTE_BATCH_DELETE, ids, BaseResponse.class);
    }

    /* ajax 讲师信息 */
    @RequestMapping("/cms/course/lecturer/ajaxList.php")
    public @ResponseBody BaseResponse ajaxGoodList(@RequestParam(value = "lecturerName", required = false)String lecturerName,
                                                   HttpServletRequest request) {
        if(StringUtils.isEmpty(lecturerName)){
            return SoaConnectionFactory.get(request,ConstantsUri.COURSE_LECTURER_LIST, null, CourseLecturerListRs.class);
        }else{
            Map map = new HashMap<String,String>();
            map.put("page","0");
            map.put("size","0");
            map.put("lecturerName",lecturerName);
            return SoaConnectionFactory.get(request,ConstantsUri.COURSE_LECTURER_LIST, map, CourseLecturerListRs.class);
        }
    }

    /* 通过用户名或者手机号查询用户 */
    @GetMapping("/cms/course/validUsername/{username}.php")
    public @ResponseBody BaseResponse viewByNameOrPhone(HttpServletRequest request, @PathVariable String username){
        ConsumerInfoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.CONSUMER_INFO_BY_NAME_PHONE, null , ConsumerInfoRs.class, username);
        if(rs.isSuccess()){
            Consumer user = rs.getData();
            return SoaConnectionFactory.getRestful(request, ConstantsUri.CONSUMER_INFO, null , ConsumerInfoRs.class, user.getId());
        }else{
            return new BaseResponse(false,"系统异常");
        }
    }
}

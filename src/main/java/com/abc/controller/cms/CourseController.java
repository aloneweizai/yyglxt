package com.abc.controller.cms;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.dto.cms.course.CourseStatus;
import com.abc.dto.cms.course.TeachMethodType;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.course.CourseCriteria;
import com.abc.soa.request.consumer.VipLevelRq;
import com.abc.soa.request.financed.CouponActivityRq;
import com.abc.soa.request.financed.CouponRq;
import com.abc.soa.response.cms.course.*;
import com.abc.soa.response.consumer.VipLevelRs;
import com.abc.soa.response.financed.CouponActivityRs;
import com.abc.soa.response.financed.CouponRs;
import com.abc.soa.response.financed.ProductRs;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author liuQi
 * @Date 2017/8/17 14:40
 * 课程管理
 */
@Controller
public class CourseController {

    /* 课程列表 */
    @GetMapping(value = "/cms/course/list.php")
    public String list(HttpServletRequest request, Model model) throws IOException {
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/cms/course/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("courseStatuses", CourseStatus.values());
        model.addAttribute("initPageLink", initPageLink);
        return "cms/course/list";
    }
    @GetMapping(value = "/cms/course/page.php")
    public String page(PagerSpec pagerSpec,CourseCriteria criteria, HttpServletRequest request, Model model) throws IOException {
        String link = pagerSpec.getLink();
        String currLink = new StringBuilder(request.getContextPath()).append("/cms/course/list.php").append(link.substring(link.indexOf("?"))).toString();
        criteria.setPage(pagerSpec.getCurrentPage()).setSize(pagerSpec.getPerPageNum());
        CourseListBoRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_LIST, criteria, CourseListBoRs.class);
        model.addAttribute("courses",rs.getDataList());

        pagerSpec.setTotalItems(rs.getTotal());
        model.addAttribute("pagerSpec", PagerUtil.calculatePagerSpec(pagerSpec));
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec, true));
        model.addAttribute("currLink", currLink.replace("[:page]", String.valueOf(pagerSpec.getCurrentPage())));

        model.addAttribute("teachMethodTypes", TeachMethodType.values());
        model.addAttribute("courseStatuses", CourseStatus.values());
        model.addAttribute("SNS_PATH", SpringCtxHolder.getProperty("SNS_PATH"));


        return "cms/course/list_page";
    }

    /* 课程GET */
    @GetMapping(value = "/cms/course/edit.php")
    public String edit(@RequestParam(value = "currLink", defaultValue = "")String currLink,
                       @RequestParam(required = false,value = "id") String id,
                       @RequestParam(value = "formName", defaultValue = "course") String formName,
                       @RequestParam(value = "readonly", defaultValue = "false") Boolean readonly,
                       HttpServletRequest request,  Model model) throws IOException {
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/cms/course/list.php" : currLink;
        model.addAttribute("currLink",currLink);
        model.addAttribute("formName",formName);
        model.addAttribute("readonly",readonly);
        if(!CommonUtils.nullOrBlank(id)){
            CourseRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_VIEW, null, CourseRs.class, id);
            Course course = rs.getData();
            model.addAttribute("course",course);
            //获取商品信息
            if(!CommonUtils.nullOrBlank(course.getGoodsId()) && course.getIsFree() == 0){
                ProductRs productRs = SoaConnectionFactory.getRestful(request,ConstantsUri.GOODS_INFO, null, ProductRs.class,course.getGoodsId());
                model.addAttribute("goods", productRs.getData());
            }
        }
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));

        VipLevelRq levelRq = new VipLevelRq();
        levelRq.setPage(0);
        levelRq.setSize(0);
        levelRq.setStatus(Boolean.TRUE);
        VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        model.addAttribute("vipLevels", levelRs.getDataList());
       /* CouponRq rq = new CouponRq();
        rq.setStatus("2");
        rq.setIsOverdue("1");
        rq.setSize(99999);
        CouponRs couponRs = SoaConnectionFactory.get(request, ConstantsUri.COUPON_LIST, rq, CouponRs.class);
        model.addAttribute("couponRs", couponRs.getDataList());*/
        CouponActivityRq rq = new CouponActivityRq();
        rq.setSize(99999);
        rq.setStatus("2");
        rq.setIsOverdue("1");
        CouponActivityRs couponActivityRs = SoaConnectionFactory.get(request, ConstantsUri.COUPONACTIVITY_LIST, rq, CouponActivityRs.class);
        model.addAttribute("couponActivityRs", couponActivityRs.getDataList());
        return "cms/course/form";
    }

    /* 课程新增 */
    @RequiresPermissions(value = {"cms_course:add","cms_course:edit"},logical = Logical.OR)
    @PostMapping(value = "/cms/course/save.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody Course course) throws IOException {
        BaseResponse rs = null;
//        获取商品信息
//        if(!CommonUtils.nullOrBlank(course.getGoodsId()) && course.getIsFree() == 0){
//            ProductRs productRs = SoaConnectionFactory.getRestful(request,ConstantsUri.CURRICULUM_GOODS_INFO, null, ProductRs.class,course.getGoodsId());
//            if(productRs != null && productRs.getData() != null && productRs.getData().getSellingPrice() != null){
//                course.setOriginalPrice(Double.parseDouble(productRs.getData().getSellingPrice()));
//            }
//        }
        if(CommonUtils.nullOrBlank(course.getCurriculumId())){
            rs = SoaConnectionFactory.post(request, ConstantsUri.COURSE_ADD, course, CourseRs.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.COURSE_EDIT, course, CourseRs.class, course.getCurriculumId());
        }
        return rs;
    }

    /* 课程删除 */
    @RequiresPermissions("cms_course:delete")
    @DeleteMapping("/cms/course/delete/{id}.php")
    public @ResponseBody BaseResponse delete(HttpServletRequest request, @PathVariable(value = "id") String id){
        BaseResponse rs = SoaConnectionFactory.deleteRestful(request, ConstantsUri.COURSE_DELETE, null, BaseResponse.class, id);
        return rs;
    }

    /* 课程修改状态 */
    @RequiresPermissions("cms_course:edit")
    @PutMapping("/cms/course/updateStatus/{id}.php")
    public @ResponseBody BaseResponse updateStatus(HttpServletRequest request, @PathVariable(value = "id") String id, @RequestParam(value = "status") String status){
        BaseResponse rs = SoaConnectionFactory.put(request, ConstantsUri.COURSE_UPDATE_STATUS, status, BaseResponse.class, id);
        return rs;
    }




    /**
     * 课堂跳转视频播放页
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/cms/course/video/{id}/{kejian}",method = RequestMethod.GET)
    public ModelAndView pay(@PathVariable(value = "id") String id,
                            @PathVariable(value = "kejian") String kejian,
                            HttpSession session, HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mav= new ModelAndView("cms/course/video");
//        Object obj = session.getAttribute("currentUser");
//        防止跳过直接访问
//        BaseResponse br = getVideoBool(obj, request, id);
//        if(!"2000".equals(br.getCode())){
//            return null;
//        }

        CurriculumListDetailsBo curriculumListDetailsBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CURRICULUM_DETAILS,null, CurriculumListDetailsBo.class,id);




        //mav.addObject("coursewareId",kejian);
        mav.addObject("path",SpringCtxHolder.getProperty("abc.soa-url"));
        KjBo kjBo=SoaConnectionFactory.get(request,ConstantsUri.CURRICULUM_KJ_ID,null,KjBo.class,kejian);
        mav.addObject("fileSite",kjBo.getData().getFileSite());
        mav.addObject("link",kjBo.getData().getLink());
        mav.addObject("ploadWay",kjBo.getData().getUploadWay());
        mav.addObject("type",kjBo.getData().getType());
        mav.addObject("data",curriculumListDetailsBo.getData());
//        CurriculumStudyBo curriculumStudyBo=new CurriculumStudyBo();
//        curriculumStudyBo.setCurriculumId(id);
//        curriculumStudyBo.setCoursewareId(kejian);
//        UserBo userBo=(UserBo)obj;
//        CollectBo brs=SoaConnectionFactory.get(request,ConstantsUri.CURRICULUM_COLLECT,null,CollectBo.class,id);
//        mav.addObject("scstatus",brs.getData());
//        VipLevelResp vipLevelResp=SoaConnectionFactory.get(request,ConstantsUri.USER_VIP_LEVEL,null,VipLevelResp.class);
//        mav.addObject("vip",vipLevelResp.getDataList());
//        curriculumStudyBo.setUserId(userBo.getId());
//        curriculumStudyBo.setUsername(userBo.getUsername());
//        curriculumStudyBo.setNickname(userBo.getNickname());
//        curriculumStudyBo.setVisitIP(getIp2(request));
//        curriculumStudyBo.setMemberGrade(userBo.getVipLevel());
//        BaseResponse br=SoaConnectionFactory.post(request,ConstantsUri.CURRICULUM_STUDY_ADD,curriculumStudyBo,BaseResponse.class);
        return mav;
    }

}
